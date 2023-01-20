package de.htwg.se.Chess.aview

trait Receiver {
def off(): Unit
}

class FirstReceiver extends Receiver {
    def off() =
        println("One Button Selected")
}

class ThirdReceiver extends Receiver {
    def off() =
        println("Move Execute")
}

class SecondReceiver extends Receiver {
    def off() =
        println("Two Buttons Selected")
}