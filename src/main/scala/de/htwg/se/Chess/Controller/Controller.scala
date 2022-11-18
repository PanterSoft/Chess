package de.htwg.se.Chess.controller

import de.htwg.se.Chess.util.Observable
import de.htwg.se.Chess.util.UndoManager
import de.htwg.se.Chess.controller.GameStatus._
import scala.collection.immutable.VectorMap
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.model.History

case class Controller(var field: Board, var history: History) extends Observable:

  var gameStatus: GameStatus = IDLE
  private val undoManager = new UndoManager

  def board_to_string_c : String = field.board_to_string()

  def move_c(pos_now : String, pos_new : String) : Unit =
    val old_field = field
    field = field.move(pos_now, pos_new)
    if (old_field != field)
      history = history.add_to_history(pos_now, pos_new)
    notifyObservers

    //Undo to add