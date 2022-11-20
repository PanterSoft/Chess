package de.htwg.se.Chess.model

trait Originator:
    def save(): Memento

    def restore(m: Memento): Unit

    def game_state_$eq(board: Board): Boolean

case class BoardOriginator(var board: Board) extends Originator:

    def save(): Memento =
        return new BoardMemento(this.board)

    def restore(m: Memento) = {
        this.board = m.board()
    }