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
    val field_test = new Board()
    val history_test = new History()
    val controller_test = new Controller(field_test, history_test)
    val tui_main_test = new tui(controller_test)

    val eol = sys.props("line.separator")

    val expected_field = "   A    B    C    D    E    F    G    H  " + """
/----+----+----+----+----+----+----+----\
| R1 | k1 | B1 | Q1 | K1 | B1 | k1 | R1 | 1
+----+----+----+----+----+----+----+----+
|    | P1 | P1 | P1 | P1 | P1 | P1 | P1 | 2
+----+----+----+----+----+----+----+----+
| P1 |    |    |    |    |    |    |    | 3
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 4
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 5
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 6
+----+----+----+----+----+----+----+----+
| P2 | P2 | P2 | P2 | P2 | P2 | P2 | P2 | 7
+----+----+----+----+----+----+----+----+
| R2 | k2 | B2 | Q2 | K2 | B2 | k2 | R2 | 8
\----+----+----+----+----+----+----+----/""" + eol

    val expected_field2 = "   A    B    C    D    E    F    G    H  " + """
/----+----+----+----+----+----+----+----\
| R1 | k1 | B1 | Q1 | K1 | B1 | k1 | R1 | 1
+----+----+----+----+----+----+----+----+
| P1 | P1 | P1 | P1 | P1 | P1 | P1 | P1 | 2
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 3
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 4
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 5
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 6
+----+----+----+----+----+----+----+----+
| P2 | P2 | P2 | P2 | P2 | P2 | P2 | P2 | 7
+----+----+----+----+----+----+----+----+
| R2 | k2 | B2 | Q2 | K2 | B2 | k2 | R2 | 8
\----+----+----+----+----+----+----+----/""" + eol

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

    /* Controller Test */

    "notify its Observer after move" in {
        controller_test.move_c("A2", "A3")
        controller_test.board_to_string_c should be(expected_field)
    }

    "handle undo/redo of a field correctly" in {
        controller_test.move_c("A2", "A3")
        controller_test.board_to_string_c should be (expected_field)
        controller_test.undo
        //controller_test.board_to_string_c should be (expected_field2)
    //controller.check_winner should be(0)
    //controller.redo
    //controller.board_to_string_c should be (expected_field)
    //controller.move_c("D2", "D4") // Player 1
    //controller.move_c("A3", "A4") // Player 2
    //controller.move_c("D1", "D3")
    //controller.move_c("A4", "A5")
    //controller.move_c("D3", "E3")
    //controller.move_c("A5", "A6")
    //controller.move_c("E3", "E7")
    //controller.move_c("B2", "B3")
    //controller.move_c("E7", "E8")
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

    "welcomeMessage() should create a String\n" + expected_welcome_message in {
        tui_main_test.welcomeMessage shouldBe (expected_welcome_message)
    }

    "recognize invalid input" in {
        match_pattern(tui_main_test.commands("hello")) should be
        ("ERROR! Wrong usage! Try help !")
    }
    "recognize the input 'help' as returning a help signal" in {
        match_pattern(tui_main_test.commands("help")) should be(expected_help_field)
    }
    "recognize the input 'exit' as returning a quit signal" in {
        match_pattern(tui_main_test.commands("exit")) should be("Goodbye :)")
    }

    "Check move via TUI (A2 => A3)" in {
        tui_main_test.process("move A2 A3")
        controller_test.field.board_to_string() should be (expected_field)
    }

    "Check move via TUI (A7 => A6)" in {
        tui_main_test.process("move A7 A6")
        controller_test.field.board_to_string() should be (expected_field)
    }
