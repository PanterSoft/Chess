package de.htwg.se.Chess.controller

import de.htwg.se.Chess.util.Observable
import scala.collection.immutable.VectorMap
import de.htwg.se.Chess.model.Board

case class Controller(var field: Board) extends Observable:

  def board_to_string_c : String = field.board_to_string()

  def move_c(pos_now : String, pos_new : String) : Unit =
    field = field.move(pos_now, pos_new)
    notifyObservers