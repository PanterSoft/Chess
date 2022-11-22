package de.htwg.se.Chess.model

import scala.collection.immutable.VectorMap

class Solver(field:Board){
    //Check if game is over
    def solve(game_map: VectorMap[String, String]): Tuple2[Int, Board] =
        var res : Tuple2[Int, Board] = (0, field)
        if (!game_map.values.exists(_ == "K2"))
            res = (1, field)
            return res
        else if (!game_map.values.exists(_ == "K1"))
            res = (2, field)
            return res
        else
            return res
}