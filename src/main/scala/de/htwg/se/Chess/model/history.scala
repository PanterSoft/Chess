package de.htwg.se.Chess.model

import scala.collection.immutable.VectorMap

case class History(val history: VectorMap[String, String]) {
    def this() =
        this(VectorMap())

    def add_to_history(pos_now: String, pos_new: String): History =
        val new_map = history + (pos_now -> pos_new)
        return History(new_map)


    def check_turn(): Int =
        if (history.isEmpty)
            return 1
        else
            if (Math.floorMod(history.size, 2) == 1)
                return 2
            else
                return 1
}