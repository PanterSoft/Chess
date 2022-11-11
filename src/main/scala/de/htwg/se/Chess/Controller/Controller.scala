package de.htwg.se.Chess.controller

import de.htwg.se.Chess.util.Observable
import scala.collection.immutable.VectorMap
import de.htwg.se.Chess.model.Game

val game = new Game().new_game_map()
case class Controller(var map: VectorMap[String, String]) extends Observable:

  def board_to_string : String = Game().board_to_string(game)

  def move(posOld : String, posNew : String) : VectorMap[String, String] =
    notifyObservers
    Game().move(game,posOld, posNew)

  /*
  def addPlayer1(doThis: Move => Field, move: Move) =
    map = doThis(move)
    notifyObservers
  def addPlayer2(doThis: => Field) =
    map = doThis
    notifyObservers
*/