package de.htwg.se.Chess

import scala.collection.immutable.VectorMap

import de.htwg.se.Chess.aview.tui
import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.model._

import scala.language.reflectiveCalls
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class GeneralGameSpec extends AnyWordSpec:
    // Test Init
    val field_test = Board()
    val history_test = new History()
    val controller_test = new Controller(field_test, history_test)
    val tui_main_test = new tui(controller_test)

    val test_map = VectorMap("A1"->"R1", "B1"-> "k1", "C1"->"B1", "D1"->"Q1", "E1"->"K1", "F1"->"B1", "G1"->"k1", "H1"->"R1", "A2"->"P1", "B2"->"P1", "C2"->"P1", "D2"->"P1", "E2"->"P1", "F2"->"P1", "G2"->"P1", "H2"->"P1","A7"->"P2", "B7"->"P2", "C7"->"P2", "D7"->"P2", "E7"->"P2", "F7"->"P2", "G7"->"P2", "H7"->"P2", "A8"->"R2", "B8"->"k2", "C8"->"B2", "D8"->"Q2", "E8"->"K2", "F8"->"B2", "G8"->"k2", "H8"->"R2")

    val empty_map = VectorMap("A1"->"  ", "B1"->"  ", "C1"->"  ", "D1"->"  ", "E1"->"  ", "F1"->"  ", "G1"->"  ", "H1"->"  ", "A2"->"  ", "B2"->"  ", "C2"->"  ", "D2"->"  ", "E2"->"  ", "F2"->"  ", "G2"->"  ", "H2"->"  ","A3"->"  ", "B3"->"  ", "C3"->"  ", "D3"->"  ", "E3"->"  ", "F3"->"  ", "G3"->"  ", "H3"->"  ", "A4"->"  ", "B4"->"  ", "C4"->"  ", "D4"->"  ", "E4"->"  ", "F4"->"  ", "G4"->"  ", "H4"->"  ", "A5"->"  ", "B5"->"  ", "C5"->"  ", "D5"->"  ", "E5"->"  ", "F5"->"  ", "G5"->"  ", "H5"->"  ", "A6"->"  ", "B6"->"  ", "C6"->"  ", "D6"->"  ", "E6"->"  ", "F6"->"  ", "G6"->"  ", "H6"->"  ", "A7"->"  ", "B7"->"  ", "C7"->"  ", "D7"->"  ", "E7"->"  ", "F7"->"  ", "G7"->"  ", "H7"->"  ", "A8"->"  ", "B8"->"  ", "C8"->"  ", "D8"->"  ", "E8"->"  ", "F8"->"  ", "G8"->"  ", "H8"->"  ")

    val init_test_field = empty_map.++(test_map)

    def combine_map_test(test_map: Board, pos_old: String, pos_new: String): Board =
        val figure = test_map.board.get(pos_old)
        val map_new = test_map.board + (pos_old -> "  ", pos_new -> field_test.match_pattern(figure))
        val map_test_combined = empty_map.++(map_new)
        return Board(map_test_combined)

    val eol = sys.props("line.separator")

    val expected_field = combine_map_test(Board(init_test_field), "A2", "A3")

    /* History Tests */

    val empty_history_map = VectorMap[String, String]()
    val history_test_map = VectorMap("A2"->"A3")

    "add_to_history(history_map, pos_now, pos_new) should return an updated //Game History" in {
        history_test.add_to_history("A2", "A3") should be (History(history_test_map))
    }

    "check_turn() should return 1 (Player One first move )" in {
        history_test.check_turn() should be (1)
    }

    "check_turn() should return 2 (Player Two)" in {
        History(history_test_map).check_turn() should be (2)
    }

    val history_test_map_2 = history_test_map + ("A3"->"A4")

    "check_turn() should return 1 (Player One second move)" in {
        History(history_test_map_2).check_turn() should be (1)
    }

    /* TUI Tests */

     def match_pattern(test: Option[String])=
        test match {
                case None => ""
                case Some(s) => s
            }

    val expected_help_field = """  /-----------------------------------\
  |            HELP TABLE             |
  |-----------------------------------|
  |   help              (Display help)|
  |   exit             (Close process)|
  |   undo            (Undo Operation)|
  |   redo            (Redo Operation)|
  |                                   |
  |   move Pos_now Pos_new (make Move)|
  \-----------------------------------/""" + eol

    val expected_welcome_message = """  /-----------------------------------\
  |       Schach - Chess - Game       |
  |-----------------------------------|
  |      Textbased User Interface     |
  |         HTWG Konstanz 2022        |
  |              v1.0.0               |
  \-----------------------------------/""" + eol

    //"recognize invalid input" in {
    //    match_pattern(tui_main_test.commands("hello")) should be
    //    ("ERROR! Wrong usage! Try help !")
    //}
    "recognize the input 'help' as returning a help signal" in {
        tui_main_test.process("help") should be(expected_help_field)
    }
    //"recognize the input 'exit' as returning a quit signal" in {
    //    match_pattern(tui_main_test.commands("exit")) should be("Goodbye :)")
    //}

    /* TUI Game Tests */

    val test_map_0 = combine_map_test(Board(init_test_field), "B2", "B3")

    "Check move via TUI (B2 => B3)" in {
        tui_main_test.process("move B2 B3")
        controller_test.field should be (test_map_0)
    }

    val test_map_1 = combine_map_test(test_map_0, "D7", "D6")

    "Check move via TUI (D7 => D6)" in {
        tui_main_test.process("move D7 D6")
        controller_test.field should be (test_map_1)
    }

    val test_map_2 = combine_map_test(test_map_1, "B3", "B4")

    "Check move via TUI (B3 => B4)" in {
        tui_main_test.process("move B3 B4")
        controller_test.field should be (test_map_2)
    }

    val test_map_3 = combine_map_test(test_map_2, "C7", "C5")

    "Check move via TUI (C7 => C5)" in {
        tui_main_test.process("move C7 C5")
        controller_test.field should be (test_map_3)
    }

    val test_map_4 = combine_map_test(test_map_3, "B4", "C5")

    "Check move via TUI (B4 => C5)" in {
        tui_main_test.process("move B4 C5")
        controller_test.field should be (test_map_4)
    }

    val test_map_5 = combine_map_test(test_map_4, "D6", "C5")

    "Check move via TUI (D6 => C5)" in {
        tui_main_test.process("move D6 C5")
        controller_test.field should be (test_map_5)
    }

    val test_map_6 = combine_map_test(test_map_5, "H2", "H4")

    "Check move via TUI (H2 => H4)" in {
        tui_main_test.process("move H2 H4")
        controller_test.field should be (test_map_6)
    }

    // Undo/Redo Tests

    "undo last move" in {
        controller_test.undo
        controller_test.field should be (test_map_5)
    }

    "redo undo move" in {
        controller_test.redo
        controller_test.field should be (test_map_6)
    }