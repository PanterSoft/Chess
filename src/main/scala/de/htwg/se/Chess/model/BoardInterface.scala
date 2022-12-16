package de.htwg.se.Chess.model

import scala.collection.immutable.VectorMap

trait BoardInterface{
    //def board_c: VectorMap[String, String]
    def move(pos_now: String, pos_new: String): Board
    def game_finished(game_map: VectorMap[String, String]): Int
    def board_to_string(): String
    def check_move(board: VectorMap[String, String], pos_now: String, pos_new: String): Boolean
    def get_player(pos: String): String
}