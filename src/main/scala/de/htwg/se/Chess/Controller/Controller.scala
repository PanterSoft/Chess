package de.htwg.se.Chess.controller

import de.htwg.se.Chess.util.Observable
import de.htwg.se.Chess.controller._
import de.htwg.se.Chess.controller.GameStatus._
import scala.collection.immutable.VectorMap
import de.htwg.se.Chess.model._
import javax.swing.undo.UndoManager

case class Controller(var field: Board, var history: History) extends Observable:

  var gameStatus: GameStatus = IDLE
  private val history_manager = new HistoryManager
  val playersystem:PlayerSystem = new PlayerSystem()

  def board_to_string_c : String = field.board_to_string()

  def move_c(pos_now : String, pos_new : String) : Unit =
    //val old_field = field
    field = field.move(pos_now, pos_new)
    //if (old_field != field)
    //  history = history.add_to_history(pos_now, pos_new)
    //notifyObservers

  def domove: Unit = {
    history_manager.doMove(new SolveCommand(this))
  }

  def check_winner: Unit = {
    val success = field.game_finished(field.board)
    if (success == 1) gameStatus = PLAYER1
    else if (success == 2) gameStatus= PLAYER2
      else gameStatus = NO_WINNER
    notifyObservers
  }

  def undo: Unit = {
    history_manager.undoMove
    notifyObservers
  }

  def redo: Unit = {
    history_manager.redoMove
    notifyObservers
  }