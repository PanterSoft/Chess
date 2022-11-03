package de.htwg.se.Chess

import scala.io.StdIn.readLine

def playerSetup() =
    println("Player One: enter your Name: ")
    val playerOne = readLine()
    println("Player Two: enter your Name: ")
    val playerTwo = readLine()
    println(s"Welcome $playerOne and $playerTwo")

def gameLoop() =
    println(welcomeMessage)
    while(true) {
        print("->")
        val in = readLine()
        commands(in)
    }

def commands(command: String) =
    println("Befehle wie 'K1 auf B5' Bewegt König 1 auf Feld B5.")
    val command_in = new Array[String](4)
    command_in() = command.split(" ");
    command_in(0) match
        case "start" => start()
        case "exit" => exit()
        case "help" => System.out.println(helpString)
        case _ => System.err.println(errorMessage)

def exit() =
    println("Goodbye!")
    System.exit(0)

def helpString: String =
    """
    ------------------------------------
    |            HELP TABLE             |
    |-----------------------------------|
    |   start                           |
    |   help              (Display help)|
    |   exit             (Close process)|
    |                                   |
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

def errorMessage: String = "ERROR! Wrong usage! Try \"help\" !"

def start() =
    playerSetup()