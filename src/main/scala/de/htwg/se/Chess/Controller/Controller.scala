package de.htwg.se.Chess.Controller

import de.htwg.se.Chess.util.Command
import de.htwg.se.Chess.util.util.Observable
import de.htwg.se.Chess.util.util.UndoManager

case class Controller(var field: Field) extends Observable:
  val players = new UndoManager[Field]
  def addPlayer1(doThis: Move => Field, move: Move) =
    field = doThis(move)
    notifyObservers
  def addPlayer2(doThis: => Field) =
    field = doThis
    notifyObservers
