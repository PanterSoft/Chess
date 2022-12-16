package de.htwg.se.Chess.controller

import de.htwg.se.Chess.util.Observer
import de.htwg.se.Chess.util.Observable
import de.htwg.se.Chess.controller.controllerComponent.GameState._
import de.htwg.se.Chess.model._

trait ControllerInterface extends Observable{
    def field : Board
    def game_state: GameState
    def board_to_string_c(): String
    def move_c(pos_now : String, pos_new : String) : Unit
    def domove(): Unit
    def get_player_c(pos_now: String): String
    def change_player(): Unit
    def last_turn(): String
    def check_winner(): Unit
    def undo(): Unit
    def redo(): Unit
}