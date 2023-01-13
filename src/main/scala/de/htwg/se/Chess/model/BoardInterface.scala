package de.htwg.se.Chess.model

import scala.collection.immutable.VectorMap
import de.htwg.se.Chess.model._

trait BoardInterface(){
    def move(pos_now: String, pos_new: String): Board
    def game_finished(game_map: VectorMap[String, String]): Int
    def board_to_string(): String
}