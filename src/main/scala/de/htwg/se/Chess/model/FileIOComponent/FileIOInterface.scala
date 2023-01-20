package de.htwg.se.Chess.model.FileIOComponent

import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.model.BoardInterface
trait FileIOInterface {

  def save(field: BoardInterface) : Unit
  def load() : Board
}