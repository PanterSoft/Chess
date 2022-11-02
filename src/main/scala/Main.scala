package de.htwg.se.Chess

import scala.compiletime.ops.string


@main def Chess: Unit =

// Create new Game
  val game = new Game
  game.newGame()
  println(game.board())

  game.move(0, 1, 0, 3)
  println(game.board())

  //game.move(0, 1, 0, 2) // Bauer
  //println(game.board())
//
  //game.move(3, 1, 3, 3) // Bauer
  //println(game.board())
//
  //game.move(3, 0, 3, 2) // Queen
  //println(game.board())
//
  //game.move(6, 0, 5, 2)
  //println(game.board()) // knight
//
  //game.move(3, 2, 3, 3) // Queen on Pawn (Invalid Move)
  //println(game.board())