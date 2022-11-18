package de.htwg.se.Chess.controller

object GameStatus extends Enumeration{
  type GameStatus = Value
  val IDLE, SOLVED, NOT_SOLVED = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    SOLVED ->"Game successfully solved",
    NOT_SOLVED ->"Game not solved yet")

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }
}