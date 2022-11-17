package de.htwg.se.Chess.aview

import scala.io.StdIn.readLine
import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.util.Observer

class tui(controller: Controller) extends Observer{

    controller.add(this)
    var game_quit = false
    //var map = controller.map

    def process(in: String): Unit =
        println(welcomeMessage)
        update
        val commando_array = in.split(" ")
        commands(in) match {
            case None =>
            case Some(s) => println(s)
        }

    def commands(in: String) : Option[String] =
        in.split(" ").toList match {
            case "exit" :: Nil => Some("Goodbye :)")
            case "help" :: Nil=> Some(helpString)
            case "move" :: pos_old :: pos_new :: Nil => controller.move_c(pos_old, pos_new); None
                        //ToDo:
                        //"Next Player"
                        //controller.board_to_string
            case _ => Some(errorMessage)
        }

    def helpString: String =
        """
        ------------------------------------
        |            HELP TABLE             |
        |-----------------------------------|
        |   help              (Display help)|
        |   exit             (Close process)|
        |                                   |
        |   move(x1 y1 x2 y2                |
        |   after start current pos x y     |
        |   then new pos x y {x y x y}      |
        -------------------------------------
        """

    def welcomeMessage: String =
        """
        ------------------------------------
        |       Schach - Chess - Game       |
        |-----------------------------------|
        |      Textbased User Interface     |
        |         HTWG Konstanz 2022        |
        |              v1.0.0               |
        -------------------------------------
        """

    def errorMessage: String =
        "ERROR! Wrong usage! Try \"help\" !"

    override def update: Unit =  println(controller.board_to_string_c)
}