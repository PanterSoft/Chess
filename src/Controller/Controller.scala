package de.htwg.se.Chess
package controller

import model.Field
import model.Move
import model.Stone
import util.Command
import util.Observable
import util.UndoManager

case class Controller(var field: Field) extends Observable:
  val players = new UndoManager[Field]
  def addPlayer1(doThis: Move => Field, move: Move) =
    field = doThis(move)
    notifyObservers
  def addPlayer2(doThis: => Field) =
    field = doThis
    notifyObservers
