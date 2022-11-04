package de.htwg.se.Chess

import scala.io.StdIn.readLine

var  Game_quit = false
val game = new Game

def gameLoop()=
    println(welcomeMessage)
    while(Game_quit == false) {
        val in = readLine("->")
        val commando_array = in.split(" ")
        println(commands(in))

        if (commando_array(0) == "move")
            println(game.board())
    }
    System.exit(0)

def commands(in: String): String =
    val commando_array = in.split(" ")
    commando_array(0) match
        case "start" => start()
        case "exit" => Game_quit = true; "Goodbye :)"
        case "help" => helpString
        case "move" => game.move(commando_array(1).toInt, commando_array(2).toInt, commando_array(3).toInt, commando_array(4).toInt)
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
    game.newGame()
    game.board()
