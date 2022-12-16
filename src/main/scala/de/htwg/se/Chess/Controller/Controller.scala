package de.htwg.se.Chess.controller

import de.htwg.se.Chess.util.Observable
import scala.collection.immutable.VectorMap
import de.htwg.se.Chess.controller._
import de.htwg.se.Chess.model._
import de.htwg.se.Chess.controller.GameState._
import de.htwg.se.Chess.model.BoardInterface



case class Controller(var field: BoardInterface) extends ControllerInterface:

  var game_state: GameState = NO_WINNER_YET
  private val history_manager = new HistoryManager
  val playersystem:PlayerSystem = new PlayerSystem()

  def board_to_string_c() : String = field.board_to_string()

  def move_c(pos_now : String, pos_new : String) : Unit =
  if(check_valid(pos_now : String, pos_new : String) == true)
    field = field.move(pos_now, pos_new)
    change_player()
    check_winner()
    notifyObservers
  else
    println("Invalid Move!")

  def domove(): Unit = {
    history_manager.doMove(new SolveCommand(this))
  }

  def get_player_c(pos_now: String): String =
    field.get_player(pos_now)

  def change_player(): Unit =
    playersystem.changeState()

  def last_turn(): String =
    if(playersystem.previousState.isInstanceOf[PlayerOne])
        "2"
    else
        "1"

  def check_winner(): Unit = {
    val success = field.game_finished(field.board_c)
    if (success == 1) game_state = PLAYER1
    else if (success == 2) game_state = PLAYER2
      else game_state = NO_WINNER_YET
  }

  def undo(): Unit = {
    if (history_manager.undoStack != Nil)
      history_manager.undoMove
      change_player()
      check_winner()
      notifyObservers
  }

  def redo(): Unit = {
    if (history_manager.redoStack != Nil)
      history_manager.redoMove
      change_player()
      check_winner()
      notifyObservers
  }

  def check_valid(pos_now : String, pos_new : String): Boolean = {
    if (field.check_move(field.board ,pos_now, pos_new) == true)
      domove()
      return true
    else
      return false
  }