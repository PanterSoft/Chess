package de.htwg.se.Chess

import de.htwg.se.Chess.aview.tui
import de.htwg.se.Chess.controller.controllerComponent.Controller
import de.htwg.se.Chess.model._

import scala.io.StdIn.readLine
import de.htwg.se.Chess.aview.SwingGUI

object Chess {
  val field = Board()
  val controller = new Controller(field)
  val tui_main = new tui(controller)
  val gui = new SwingGUI(controller)

  def main(args: Array[String]): Unit = {
    /* TUI */
    var input: String = ""
    while
      input = readLine("->")
      tui_main.process(input)
      input != "exit"
    do()
    /* GUI */
  }
}

//Interfaces: trait, Components: private and public notations
//interface nach oben minimal, muss klein und konsequent sein um über lange zeit bearbeitbar sein