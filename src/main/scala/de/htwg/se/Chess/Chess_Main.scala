package de.htwg.se.Chess

import de.htwg.se.Chess.aview.tui
import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.model.History

import scala.io.StdIn.readLine

object Chess {
  val field = new Board()
  val history = new History()
  val controller = new Controller(field, history)
  val tui_main = new tui(controller)

  def main(args: Array[String]): Unit = {
    var input: String = ""
    while
      input = readLine("->")
      tui_main.process(input)
      input != "exit"
    do()
  }
}
