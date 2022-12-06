package de.htwg.se.Chess.aview

trait Receiver {
//def on(): Unit
def off(): Unit
}

class GreenReceiver extends Receiver {
    //def on() =
    //    println("Swithing on green light")
    def off() =
        println("One Button Selected")
}

class RedReceiver extends Receiver {
    //def on() =
    //    println("Swithing on red light")
    def off() =
        println("Move Execute")
}

class YellowReceiver extends Receiver {
    //def on() =
    //    println("Swithing on yellow light")
    def off() =
        println("Two Buttons Selected")
}