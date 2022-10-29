package de.htwg.se.Chess

import scala.compiletime.ops.string


@main def Chess: Unit =

// Create new Game
  val game = new Game
  game.newGame()
  println(game.field())
