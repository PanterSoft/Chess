package de.htwg.se.Chess

import scala.io.StdIn.readLine

def playerSetup($playerOne: String = "Testname1", $playerTwo: String = "Testname2") =
    println("Player One: enter your Name: ")
    val playerOne = readLine()
    println("Player Two: enter your Name: ")
    val playerTwo = readLine()
    println(s"Welcome $playerOne and $playerTwo")

def gameLoop() =
    println("Game Start:")
    while(true) {
        println("->")
        val in = readLine()
        commands(in)
    }

def commands($in: String = "Feldbewegungen") =
    println("Befehle wie 'K1 auf B5' Bewegt König 1 auf Feld B5.")

    /*
     *
    System.out.println(welcomeMessage);

    Scanner sc = new Scanner(System.in);
    while (true) {
        System.out.print("-> ");
        String in = sc.nextLine();
        commands(in);
    }
     *
     */
