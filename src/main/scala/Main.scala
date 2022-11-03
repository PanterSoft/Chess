package de.htwg.se.Chess

import scala.compiletime.ops.string
@main def Chess: Unit =
  println(play_field())
  playerSetup()
  gameLoop()