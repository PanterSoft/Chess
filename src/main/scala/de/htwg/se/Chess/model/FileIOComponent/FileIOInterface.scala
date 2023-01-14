package de.htwg.se.Chess.model.FileIOComponent

import de.htwg.se.Chess.model._
trait FileIOInterface {

  def save(game: BoardInterface) : Unit
  def load(game: BoardInterface) : BoardInterface

}