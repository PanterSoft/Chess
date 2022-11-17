package de.htwg.se.Chess.aview

import scala.io.StdIn.readLine
import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.util.Observer

class tui(controller: Controller) extends Observer{

    controller.add(this)
    var game_quit = false

    def gameLoop()=
        println(welcomeMessage)
        update
        while(game_quit == false) {
            val in = readLine("->")
            val commando_array = in.split(" ")
            commands(in) match {
                case None =>
                case Some(s) => println(s)
            }

            //if (commando_array(0) == "move")
            //    println(controller.board_to_string)
            }
        System.exit(0)

    def commands(in: String) : Option[String] =
        in.split(" ").toList match {
            case "exit" :: Nil => game_quit = true; Some("Goodbye :)")
            case "help" :: Nil=> Some(helpString)
            case "move" :: pos_old :: pos_new :: Nil => controller.move_c(pos_old, pos_new); None
                        //ToDo:
                        //"Next Player"
                        //controller.board_to_string
            case _ => Some(errorMessage)
        }

    val eol = sys.props("line.separator")

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
        "ERROR! Wrong usage! Try \"help\" !"

    override def update: Unit =  println(controller.board_to_string_c)
}