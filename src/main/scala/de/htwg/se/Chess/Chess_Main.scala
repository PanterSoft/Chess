package de.htwg.se.Chess

import com.google.inject.Guice
import de.htwg.se.Chess.aview.tui
import de.htwg.se.Chess.controller.ControllerInterface
import de.htwg.se.Chess.model._

import scala.io.StdIn.readLine
import de.htwg.se.Chess.aview.SwingGUI

object Chess extends Thread{
  val injector = Guice.createInjector(new ChessModule)
  val field = Board()
  val controller = injector.getInstance(classOf[ControllerInterface])
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
  }
}