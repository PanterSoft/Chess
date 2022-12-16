package de.htwg.se.Chess

import de.htwg.se.Chess.controller.ControllerInterface
import de.htwg.se.Chess.controller.controllerComponent.Controller
import de.htwg.se.Chess.model.BoardInterface
import de.htwg.se.Chess.model.Board

object ChessModule {
    given BoardInterface = Board()
    given ControllerInterface = Controller(field)
}