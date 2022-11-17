package de.htwg.se.Chess

import de.htwg.se.Chess.aview.tui
import de.htwg.se.Chess.controller.Controller
import model.Game
import de.htwg.se.Chess.model.Board

@main def Chess: Unit =
  val field = new Board()
  val controller = new Controller(field)
  val tui_main = tui(controller)
  controller.notifyObservers
  tui_main.gameLoop()




//Nico Tests
/**
  // Create new Game
  val game = new Game().newGame()
  //println(Game().top_row())
  println(Game().board_to_string(game))
//
  val test = Game().move(game, "A2", "A4") // Pawn
  println(Game().board_to_string(test))
//
  val test1 = Game().move(test, "B7", "B5") // Pawn
  println(Game().board_to_string(test1))

  val test2 = Game().move(test, "A4", "B5") // Pawn
  println(Game().board_to_string(test2))
//
  //game.move(1, 1, 1, 2) // Bauer
  //println(game.board())
//
  //game.move(2, 0, 1, 1) // Bishop
  //println(game.board())
//
  //game.move(2, 1, 2, 3) // Pawn
  //println(game.board())
//
  //game.move(3, 0, 2, 1) // Queen
  //println(game.board())
//
  //game.move(2, 1, 2, 2) // Queen
  //println(game.board())
//
  //game.move(4, 0, 3, 0) // King
  //println(game.board())
//
  //// New
//
  //game.move(3, 0, 2, 1) // King
  //println(game.board())
//
  //game.move(2, 2, 3, 1) // Queen
  //println(game.board())
//
  //game.move(1, 0, 3, 1) // Knight
  //println(game.board())
//
  //game.move(0, 2, 1, 2) // Rook
  //println(game.board())
//
  //game.move(1, 1, 0, 2) // Bishop
  //println(game.board())
//
  //game.move(4, 1, 4, 4) // Pawn
  //println(game.board())
//
  //game.move(0, 6, 0, 5) // Pawn
  //println(game.board())
//
  //game.move(1, 6, 1, 4) // Pawn
  //println(game.board())
//
  //game.move(0, 3, 1, 4) // Pawn
  //println(game.board())
//
  //game.move(0, 5, 1, 4) // Pawn
  //println(game.board())

  */