package de.htwg.se.Chess.aview

import scala.io.StdIn.readLine
import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.model.Game
import de.htwg.se.Chess.util.Observer

class tui(controller: Controller) extends Observer{

    controller.add(this)
    update
    var game_quit = false
    //var map = controller.map

    def gameLoop()=
        println(welcomeMessage)
        while(game_quit == false) {
            val in = readLine("->")
            val commando_array = in.split(" ")
            println(commands(in))

            //if (commando_array(0) == "move")
            //    println(controller.board_to_string)
            }
        System.exit(0)

    def commands(in: String) : String =
        in.split(" ")(0) match {
            case "start" => start()
            case "exit" => game_quit = true; "Goodbye :)"
            case "help" => helpString
            case "move" => controller.move(in.split(" ")(1), in.split(" ")(2))
                        //ToDo:
                        "Next Player"
                        //controller.board_to_string
            case _ => errorMessage
        }

    def helpString: String =
        """
        ------------------------------------
        |            HELP TABLE             |
        |-----------------------------------|
        |   start                           |
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

    def start(): String =
        controller.board_to_string

    override def update: Unit =  println(controller.board_to_string)
}