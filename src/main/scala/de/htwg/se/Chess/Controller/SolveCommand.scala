package de.htwg.se.Chess.controller
import de.htwg.se.Chess.controller.GameStatus.SOLVED
import de.htwg.se.Chess.controller.GameStatus.NOT_SOLVED
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.model.Solver
import de.htwg.se.Chess.util.Command

class SolveCommand(controller: Controller) extends Command {
  var memento: Board = controller.field

  override def doMove: Unit = {
    memento = controller.field
    val (success, newBoard) = new Solver(controller.field).solve()
    if (success) controller.gameStatus = SOLVED else controller.gameStatus= NOT_SOLVED
    controller.field = newBoard
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
