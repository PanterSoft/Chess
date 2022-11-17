package de.htwg.se.Chess.model

import scala.collection.immutable.VectorMap

case class History(val history: VectorMap[String, String]) {
    def this() =
        this(VectorMap())

    def add_to_history(pos_now: String, pos_new: String): VectorMap[String, String] =
        val new_map = history + (pos_now -> pos_new)
        return new_map


    def check_turn(): Int =
        if (history.isEmpty)
            return 1
        else
            val last_move = history.last(0)
            val last_player = last_move.splitAt(1)(1)

            if (last_player.splitAt(1)(1) == "1")
                return 2
            else
                return 1
}