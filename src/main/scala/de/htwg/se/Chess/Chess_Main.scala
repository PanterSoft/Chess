package de.htwg.se.Chess

import de.htwg.se.Chess.aview.tui
import de.htwg.se.Chess.aview.gui.GUI
import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.model._

import scala.io.StdIn.readLine

object Chess {
  val field = Board()
  val controller = new Controller(field)
  val tui_main = new tui(controller)
  val gui = GUI(controller)

  def main(args: Array[String]): Unit = {
    /* TUI */
    //var input: String = ""
    //while
      //input = readLine("->")
      //tui_main.process(input)
      //input != "exit"
    //do()
    /* GUI */
    //val guiThread = new Thread(() => System.exit(0))
    //guiThread.setDaemon(true)
    //guiThread.start()

    val guiThread = new Thread(() => {
      gui.main()
      System.exit(0)
    })
    guiThread.setDaemon(true)
    guiThread.start()
  }
}
