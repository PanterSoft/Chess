package de.htwg.se.Chess.model

import de.htwg.se.Chess.model.History
import scala.collection.immutable.VectorMap
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatest.matchers.must.Matchers
import scala.collection.immutable.VectorMap

class HistorySpec extends AnyWordSpec:
    val history = new History

    val empty_history_map = VectorMap[String, String]()
    val history_test_map = VectorMap("A2"->"A3")

    "add_to_history(history_map, pos_now, pos_new) should return an updated Game History" in {
        history.add_to_history("A2", "A3") should be (history_test_map)
    }

    //val history_test_game = test_field + ("A2"->"A3")

    "check_turn(game_map, history_map) should return an updated Game History" in {
        history.check_turn() should be (1)
    }