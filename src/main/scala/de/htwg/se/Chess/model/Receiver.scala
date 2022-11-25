package de.htwg.se.Chess.model

trait Receiver {
def on(): String
}

class PlayerOneReceiver extends Receiver {
    def on(): String =
        return "1"
}

class PlayerTwoReceiver extends Receiver {
    def on(): String =
        return "2"
}