package de.htwg.se.Chess.model

trait Reciever {
def on(): String
}

class PlayerOneReciever extends Reciever {
    def on(): String =
        return "1"
}

class PlayerTwoReciever extends Reciever {
    def on(): String =
        return "2"
}