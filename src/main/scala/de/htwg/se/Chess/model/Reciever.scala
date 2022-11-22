package de.htwg.se.Chess.model

trait Reciever {
def on(): Unit
//def off(): Unit
}

class PlayerOneReciever extends Reciever {
    def on() =
        println("Player One")
    //def off() =
    //    println("")
}

class PlayerTwoReciever extends Reciever {
    def on() =
        println("Player Two")
    //def off() =
    //    println("")
}