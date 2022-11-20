package de.htwg.se.Chess.model

trait Memento {

    def board(): Board

}

class BoardMemento(var board: Board) extends Memento {

}
