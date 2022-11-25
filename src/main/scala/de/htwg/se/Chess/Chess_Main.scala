package de.htwg.se.Chess

import de.htwg.se.Chess.aview.tui
import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.model._

import scala.io.StdIn.readLine

object Chess {
  val field = Board()
  val controller = new Controller(field)
  val tui_main = new tui(controller)

  def main(args: Array[String]): Unit = {
    /* TUI */
    var input: String = ""
    while
      input = readLine("->")
      tui_main.process(input)
      input != "exit"
    do()
  }
}
