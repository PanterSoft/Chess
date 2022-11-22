package de.htwg.se.Chess.util

class UndoManager {
  private var undoStack: List[Command]= Nil
  private var redoStack: List[Command]= Nil
  def doStep(command: Command) = {
    undoStack = command::undoStack
    command.doMove
  }
  def undoStep  = {
    undoStack match {
      case  Nil =>
      case head::stack => {
        head.undoMove
        undoStack=stack
        redoStack= head::redoStack
      }
    }
  }
  def redoStep = {
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