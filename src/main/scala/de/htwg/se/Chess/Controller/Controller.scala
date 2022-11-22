package de.htwg.se.Chess.controller

import de.htwg.se.Chess.util.Observable
import de.htwg.se.Chess.util.UndoManager
import de.htwg.se.Chess.controller.GameStatus._
import scala.collection.immutable.VectorMap
import de.htwg.se.Chess.model._

case class Controller(var field: Board, var history: History) extends Observable:

  var gameStatus: GameStatus = IDLE
  private val undoManager = new UndoManager
  val playersystem:PlayerSystem = new PlayerSystem()

  def board_to_string_c : String = field.board_to_string()

  def move_c(pos_now : String, pos_new : String) : Unit =
    val old_field = field
    field = field.move(pos_now, pos_new)
    //if (old_field != field)
    //  history = history.add_to_history(pos_now, pos_new)
    //notifyObservers

  def domove: Unit = {
    undoManager.doMove(new SolveCommand(this))
  }

  def solve: Unit = {
    val success = field.game_finished(field.board)
    if (success == 1) gameStatus = SOLVED1
    else if (success == 2) gameStatus= SOLVED2
      else gameStatus = NOT_SOLVED
    notifyObservers
  }

  def undo: Unit = {
    undoManager.undoMove
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoMove
    notifyObservers
  }