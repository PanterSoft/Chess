package de.htwg.se.Chess.aview

import scala.io.StdIn.readLine
import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.util.Observer

class tui(controller: Controller) extends Observer{

    val eol = sys.props("line.separator")

    controller.add(this)
    println(welcomeMessage)
    update

    def process(in: String): Unit =
        commands(in) match {
            case None =>
            case Some(s) => println(s)
        }

    def commands(in: String) : Option[String] =
        in.split(" ").toList match {
            case "exit" :: Nil => Some("Goodbye :)")
            case "help" :: Nil=> Some(helpString)
            case "move" :: pos_old :: pos_new :: Nil => controller.move_c(pos_old, pos_new); None
            case _ => Some(errorMessage)
        }

    def helpString: String = """  /-----------------------------------\
  |            HELP TABLE             |
  |-----------------------------------|
  |   help              (Display help)|
  |   exit             (Close process)|
  |                                   |
  |   move Pos_now Pos_new (make Move)|
  \-----------------------------------/""" + eol

    def welcomeMessage: String = """  /-----------------------------------\
  |       Schach - Chess - Game       |
  |-----------------------------------|
  |      Textbased User Interface     |
  |         HTWG Konstanz 2022        |
  |              v1.0.0               |
  \-----------------------------------/""" + eol

    def errorMessage: String =
        "ERROR! Wrong usage! Try help !"

    override def update: Unit =  println(controller.board_to_string_c)
}