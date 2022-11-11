package de.htwg.se.Chess.aview
<<<<<<< HEAD:src/aview/TUI.scala
package aview
=======
>>>>>>> 39c4d040faf2461cd379e8dc289bd7caf50662a8:src/main/scala/de/htwg/se/Chess/aview/TUI.scala

import de.htwg.se.Chess.model.Game
import scala.io.StdIn.readLine
import de.htwg.se.sudoku.controller.Controller
import de.htwg.se.sudoku.model.{Grid, GridCreator, Solver}
import de.htwg.se.sudoku.util.Observer

class tui(controller: Controller) extends Observer{

    controller.add(this)

    var game_quit = false
    val game = new Game().newGame()

    def gameLoop()=
        println(welcomeMessage)
        while(game_quit == false) {
            val in = readLine("->")
            val commando_array = in.split(" ")
            println(commands(in))

            if (commando_array(0) == "move")
                //ToDo:
                //println("Test print Gameboard empty?")
                //println(controller.board_to_string(game))
                update
            }
        System.exit(0)

    def commands(in: String): String =
        val commando_array = in.split(" ")
        commando_array(0) match
            case "start" => start()
            case "exit" => game_quit = true; "Goodbye :)"
            case "help" => helpString
            //ToDo:
            case "move" => //println("Move und aktualisiere Spielfeld")
                        controller.move(game, commando_array(1), commando_array(2))
            case _ => errorMessage

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
        println("Test print Gameboard and start Game")
        //println(Game().board_to_string(game))
        controller.board_to_string(game)
        update

    override def update: Unit =  println(controller.board_to_string(game))
}