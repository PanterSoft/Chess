package de.htwg.se.Chess.controller

import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.util.Command

class HistoryManager {
  private var undoStack: List[Command]= Nil
  private var redoStack: List[Command]= Nil

  def doMove(command: Command) = {
    undoStack = command::undoStack
    command.doMove
  }

  def undoMove = {
    undoStack match {
      case Nil =>
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