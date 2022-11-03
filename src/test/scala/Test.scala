package de.htwg.se.Chess
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import javax.swing.text.PlainView
import java.io.ByteArrayInputStream
import scala.io.StdIn.readLine
import scala.io.StdIn
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers

class ChessSpec extends AnyWordSpec:
  val Test = Game()

  "Chess" should {

    /**
      * Playfield Tests
      */

    "have a Numbering row as string of form " +
    "'   0    1    2    3    4    5      6    7'" in {
      Game().border_row() shouldBe("+----+----+----+----+----+----+----+----+")
    }

    """have a First row as String of form '/----+----+----+----+----+----+----+----\'""" in {
      Game().first_last_row() shouldBe("""/----+----+----+----+----+----+----+----\""" )
    }

    val test_array = Array.ofDim[String](8, 8)
    for (i <- 0 to 7; j <- 0 to 7)
        test_array(i)(j) = "  "

    "have a Cell row as string of form '|    |    |    |    |    |    |    |    |'" in {
      Game().cell_row(8, test_array, 0) shouldBe("|    |    |    |    |    |    |    |    |")
    }

    "have a Border row as string of form '|----+----+----+----+----+----+----+----|'" in {
      Game().border_row() shouldBe("+----+----+----+----+----+----+----+----+")
    }

    val expected_field = """   0    1    2    3    4    5    6    7
/----+----+----+----+----+----+----+----\
|    |    |    |    |    |    |    |    | 0
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 1
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 2
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 3
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 4
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 5
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 6
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    | 7
\----+----+----+----+----+----+----+----/"""

    "have a Play Field as string of form '\n" + expected_field + "'" in {
      Game().board(8,8) shouldBe(expected_field)
    }

     /** Game().state ist irgendwie anderweitig schon gecovered
      * """look like Array[Array("  ", "  "), Array("  ", "  ")]""" in {
      * Game().state should be(test_array)
    }
    */

    val standard_game = Array.ofDim[String](8, 8)
    for (i <- 0 to 7; j <- 0 to 7)
        standard_game(i)(j) = "  "

    // Initialize New Game
        // Player One
        standard_game(0)(0) = "R1"
        standard_game(1)(0) = "k1"
        standard_game(2)(0) = "B1"
        standard_game(3)(0) = "Q1"
        standard_game(4)(0) = "K1"
        standard_game(5)(0) = "B1"
        standard_game(6)(0) = "k1"
        standard_game(7)(0) = "R1"

        standard_game(0)(1) = "P1"
        standard_game(1)(1) = "P1"
        standard_game(2)(1) = "P1"
        standard_game(3)(1) = "P1"
        standard_game(4)(1) = "P1"
        standard_game(5)(1) = "P1"
        standard_game(6)(1) = "P1"
        standard_game(7)(1) = "P1"

        // Player Two
        standard_game(0)(7) = "R2"
        standard_game(1)(7) = "k2"
        standard_game(2)(7) = "B2"
        standard_game(3)(7) = "Q2"
        standard_game(4)(7) = "K2"
        standard_game(5)(7) = "B2"
        standard_game(6)(7) = "k2"
        standard_game(7)(7) = "R2"

        standard_game(0)(6) = "P2"
        standard_game(1)(6) = "P2"
        standard_game(2)(6) = "P2"
        standard_game(3)(6) = "P2"
        standard_game(4)(6) = "P2"
        standard_game(5)(6) = "P2"
        standard_game(6)(6) = "P2"
        standard_game(7)(6) = "P2"


    "newGame() should init state array" in {
      Test.newGame() shouldBe (standard_game)
    }

    "check_move(0, 1, 0, 2) should be true (P1=> empty field)" in {
      Test.move(0, 1, 0, 3) shouldBe (true)
    }

}
