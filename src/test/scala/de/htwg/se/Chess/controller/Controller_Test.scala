package de.htwg.se.Chess.controller

import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.model.History
import de.htwg.se.Chess.util.Observer

import scala.language.reflectiveCalls
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ControllerSpec extends AnyWordSpec:

  val controller = Controller(new Board(), new History())

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

  "notify its Observer after move" in {
    controller.move_c("A2", "A3")
    controller.board_to_string_c should be(expected_field)
  }

  "handle undo/redo of a field correctly" in {
    //controller.check_winner should be(0)
    controller.move_c("A2", "A3")
    controller.board_to_string_c should be (expected_field)
    //controller.undo
    //controller.board_to_string_c should be (expected_field2)
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