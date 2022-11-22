package de.htwg.se.Chess.controller
import de.htwg.se.Chess.controller.GameStatus.SOLVED1
import de.htwg.se.Chess.controller.GameStatus.SOLVED2
import de.htwg.se.Chess.controller.GameStatus.NOT_SOLVED
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.util.Command

class SolveCommand(controller: Controller) extends Command {
  var memento: Board = controller.field

  override def doMove: Unit = {
    memento = controller.field
  }

  override def undoMove: Unit = {
    //println("test undo")
    //println(memento.board_to_string())
    val new_memento = controller.field
    controller.field = memento
    memento = new_memento
  }

  override def redoMove: Unit = {
    println("test redo")
    val new_memento = controller.field
    controller.field = memento
    memento = new_memento
  }
}
