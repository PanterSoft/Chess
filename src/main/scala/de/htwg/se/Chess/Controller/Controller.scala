package de.htwg.se.Chess.Controller

import de.htwg.se.Chess.util.Command
import de.htwg.se.Chess.util.Observer
import scala.collection.immutable.VectorMap
import java.util.Observable

case class Controller(var map: VectorMap[String, String]) extends Observable:
  def addPlayer1(doThis: Move => Field, move: Move) =
    map = doThis(move)
    notifyObservers
  def addPlayer2(doThis: => Field) =
    map = doThis
    notifyObservers
