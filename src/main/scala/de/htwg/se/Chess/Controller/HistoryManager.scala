package de.htwg.se.Chess.controller

import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.model.Board

trait Command {
  def doMove:Unit
  def undoMove:Unit
  def redoMove:Unit
}

object GameStatus extends Enumeration{
  type GameStatus = Value
  val IDLE, PLAYER1, PLAYER2, NO_WINNER = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    PLAYER1 ->"Player 1 wins!!!",
    PLAYER2 ->"Player 2 wins!!!",
    NO_WINNER ->"")

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }
}

/* History Manager */
class HistoryManager {
  private var undoStack: List[Command]= Nil
  private var redoStack: List[Command]= Nil
  def doMove(command: Command) = {
    undoStack = command::undoStack
    command.doMove
  }

  def undoMove = {
    undoStack match {
      case  Nil =>
      case head::stack => {
        head.undoMove
        undoStack=stack
        redoStack= head::redoStack
      }
    }
  }
  def redoMove = {
    redoStack match {
      case Nil =>
      case head::stack => {
        head.redoMove
        redoStack=stack
        undoStack=head::undoStack
      }
    }
  }
}

/* Set Command */

class SetCommand(pos_old:String, pos_new:String, controller: Controller) extends Command {

  override def doMove: Unit =
    controller.field = controller.field.move(pos_old, pos_new)

  override def undoMove: Unit =
    controller.field = controller.field.move(pos_old, pos_new)

  override def redoMove: Unit =
    controller.field = controller.field.move(pos_old, pos_new)

}

/* Solve Command */
class SolveCommand(controller: Controller) extends Command {
  var memento: Board = controller.field

  override def doMove: Unit = {
    memento = controller.field
  }

  override def undoMove: Unit = {
    val new_memento = controller.field
    controller.field = memento
    memento = new_memento
  }

  override def redoMove: Unit = {
    val new_memento = controller.field
    controller.field = memento
    memento = new_memento
  }
}
