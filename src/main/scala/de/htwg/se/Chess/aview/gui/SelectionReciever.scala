package de.htwg.se.Chess.aview

trait Receiver {
//def on(): Unit
def off(): Unit
}

class FirstReceiver extends Receiver {
    //def on() =
    //    println("Swithing on green light")
    def off() =
        println("One Button Selected")
}

class ThirdReceiver extends Receiver {
    //def on() =
    //    println("Swithing on red light")
    def off() =
        println("Move Execute")
}

class SecondReceiver extends Receiver {
    //def on() =
    //    println("Swithing on yellow light")
    def off() =
        println("Two Buttons Selected")
}