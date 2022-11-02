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

    val test_array2 = Array.ofDim[String](8, 8)
    for (i <- 0 to 7; j <- 0 to 7)
        test_array2(i)(j) = "  "
    val standard_game = test_array2

    // Initialize New Game
        // Player One
        standard_game(0)(0) = "T1"
        standard_game(1)(0) = "J1"
        standard_game(2)(0) = "R1"
        standard_game(3)(0) = "Q1"
        standard_game(4)(0) = "K1"
        standard_game(5)(0) = "R1"
        standard_game(6)(0) = "J1"
        standard_game(7)(0) = "T1"

        standard_game(0)(1) = "F1"
        standard_game(1)(1) = "F1"
        standard_game(2)(1) = "F1"
        standard_game(3)(1) = "F1"
        standard_game(4)(1) = "F1"
        standard_game(5)(1) = "F1"
        standard_game(6)(1) = "F1"
        standard_game(7)(1) = "F1"

        // Player Two
        standard_game(0)(7) = "T2"
        standard_game(1)(7) = "J2"
        standard_game(2)(7) = "R2"
        standard_game(3)(7) = "Q2"
        standard_game(4)(7) = "K2"
        standard_game(5)(7) = "R2"
        standard_game(6)(7) = "J2"
        standard_game(7)(7) = "T2"

        standard_game(0)(6) = "F2"
        standard_game(1)(6) = "F2"
        standard_game(2)(6) = "F2"
        standard_game(3)(6) = "F2"
        standard_game(4)(6) = "F2"
        standard_game(5)(6) = "F2"
        standard_game(6)(6) = "F2"
        standard_game(7)(6) = "F1"

    "newGame() should init state array" in {
      Game().newGame() shouldBe (standard_game)
    }

    "check_move(0, 0, 0, 1) should be false (T1 on F1)" in {
      Game().check_move(0, 0, 0, 1) shouldBe (false) // diese tests sind pseudo tests vermute ich
    }



}
