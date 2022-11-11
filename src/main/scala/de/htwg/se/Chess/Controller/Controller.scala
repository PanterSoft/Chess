package de.htwg.se.Chess.controller

import de.htwg.se.Chess.util.Observable
import scala.collection.immutable.VectorMap
import de.htwg.se.Chess.model.Game

case class Controller(var field: Game) extends Observable:

  def board_to_string_c : String = field.board_to_string(field)

  def move_c(posOld : String, posNew : String) : Unit =
    field = field.move(field, posOld, posNew)
    notifyObservers

  def start_c : Unit =
    var field = field.new_game_map()
    notifyObservers


  /*
  def addPlayer1(doThis: Move => Field, move: Move) =
    map = doThis(move)
    notifyObservers
  def addPlayer2(doThis: => Field) =
    map = doThis
    notifyObservers
*/