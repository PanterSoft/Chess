package de.htwg.se.Chess.Controller

object GameState extends Enumeration {
  type GameState = Value
  val IDLE, PLAYER1, PLAYER2, NO_WINNER = Value

  val map = Map[GameState, String](
    IDLE -> "",
    PLAYER1 ->"Player 1 wins!!!",
    PLAYER2 ->"Player 2 wins!!!",
    NO_WINNER ->"")

  def message(game_state: GameState) =
    map(game_state)
}