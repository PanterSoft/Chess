package de.htwg.se.Chess.controller

import de.htwg.se.Chess.util.Command
import de.htwg.se.Chess.model.BoardInterface

class SolveCommand(controller: Controller) extends Command {
  var memento: BoardInterface = controller.field

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
