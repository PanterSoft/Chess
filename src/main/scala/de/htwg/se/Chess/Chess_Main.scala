package de.htwg.se.Chess

import de.htwg.se.Chess.aview.tui
import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.model.History

@main def Chess: Unit =
  val field = new Board()
  val history = new History()
  val controller = new Controller(field, history)
  val tui_main = tui(controller)
  tui_main.gameLoop()