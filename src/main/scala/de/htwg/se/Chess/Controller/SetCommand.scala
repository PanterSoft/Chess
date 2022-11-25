package de.htwg.se.Chess.Controller

import de.htwg.se.Chess.util.Command
import de.htwg.se.Chess.Controller._
import de.htwg.se.Chess.controller.Controller

class SetCommand(pos_old:String, pos_new:String, controller: Controller) extends Command {

  override def doMove: Unit =
    controller.field = controller.field.move(pos_old, pos_new)

  override def undoMove: Unit =
    controller.field = controller.field.move(pos_old, pos_new)

  override def redoMove: Unit =
    controller.field = controller.field.move(pos_old, pos_new)

}