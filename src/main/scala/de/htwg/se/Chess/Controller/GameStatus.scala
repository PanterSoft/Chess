package de.htwg.se.Chess.controller

object GameStatus extends Enumeration{
  type GameStatus = Value
  val IDLE, SOLVED1, SOLVED2, NOT_SOLVED = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    SOLVED1 ->"Player 1 wins!!!",
    SOLVED2 ->"Player 2 wins!!!",
    NOT_SOLVED ->"")

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }
}