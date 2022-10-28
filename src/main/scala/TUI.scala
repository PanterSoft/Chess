package de.htwg.se.Chess

import scala.io.StdIn.readLine

def playerSetup($playerOne: String = "Testname1", $playerTwo: String = "Testname2") =
    println("Player One: enter your Name: ")
    val playerOne = readLine()
    println("Player Two: enter your Name: ")
    val playerTwo = readLine()
    println(s"Welcome $playerOne and $playerTwo")