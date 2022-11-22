package de.htwg.se.Chess.controller

import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.model.History
import de.htwg.se.Chess.util.Observer

import scala.language.reflectiveCalls
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ControllerSpec extends AnyWordSpec:

  val controller = Controller(new Board(), new History())
  //val observer = new Observer {
  //  var updated: Boolean = false
//
  //  def isUpdated: Boolean = updated
  //  override def update: Unit = updated = true
  //}
  //controller.add(observer)

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
| R2 | k2 | B2 | Q2 | k2 | B2 | k2 | R2 | 8
\----+----+----+----+----+----+----+----/""" + eol

  "notify its Observer after move" in {
    controller.move_c("A2", "A3")
    controller.board_to_string_c should be(expected_field)
  }