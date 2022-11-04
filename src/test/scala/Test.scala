package de.htwg.se.Chess
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import javax.swing.text.PlainView
import java.io.ByteArrayInputStream
import scala.io.StdIn.readLine
import scala.io.StdIn
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers

class GameSpec extends AnyWordSpec:
  val Test = Game()

  "Game" should {

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



  }

  "check_move(0, 1, 0, 2) should be true (P1=> empty field)" in {
    Test.move(0, 1, 0, 3) shouldBe ("")
  }
  "check_move(0, 0, 0, 2) should be true (R1=> empty field)" in {
    Test.move(0, 0, 0, 2) shouldBe ("")
  }
  "check_move(6, 0, 7, 2) should be true (k1=> empty field)" in {
    Test.move(6, 0, 7, 2) shouldBe ("")
  }
  "check_move(1, 1, 1, 2) should be true (P1=> empty field)" in {
    Test.move(1, 1, 1, 2) shouldBe ("")
  }
  "check_move(2, 0, 1, 1) should be true (B1=> empty field)" in {
    Test.move(2, 0, 1, 1) shouldBe ("")
  }
  "check_move(2, 1, 2, 3) should be true (P1=> empty field)" in {
    Test.move(2, 1, 2, 3) shouldBe ("")
  }
  "check_move(3, 0, 2, 1) should be true (Q1=> empty field)" in {
    Test.move(3, 0, 2, 1) shouldBe ("")
  }
  "check_move(2, 1, 2, 2) should be true (Q1=> empty field)" in {
    Test.move(2, 1, 2, 2) shouldBe ("")
  }
  "check_move(4, 0, 3, 0) should be true (K1=> empty field)" in {
    Test.move(4, 0, 3, 0) shouldBe ("")
  }
  "check_move(0, 6, 0, 5) should be true (P2 1 forward)" in {
    Test.move(0, 6, 0, 5) shouldBe ("")
  }
  "check_move(1, 6, 1, 4) should be true (P2 2 forward)" in {
    Test.move(1, 6, 1, 4) shouldBe ("")
  }
  "check_move(0, 3, 1, 4) should be true (P1 Attack P2)" in {
    Test.move(0, 3, 1, 4) shouldBe ("")
  }
  "check_move(0, 5, 1, 4) should be true (P2 Attack P1)" in {
    Test.move(0, 5, 1, 4) shouldBe ("")
  }


  "check_move(3, 0, 2, 1) should be false (K1=> Blocked/ invalid Move)" in {
    Test.move(3, 0, 2, 1) shouldBe ("Invalid Move")
  }
  "check_move(2, 2, 3, 1) should be false (Q1=> Blocked/ invalid Move)" in {
    Test.move(2, 2, 3, 1) shouldBe ("Invalid Move")
  }
  "check_move(1, 0, 3, 1) should be false (k1=> Blocked/ invalid Move)" in {
    Test.move(1, 0, 3, 1) shouldBe ("Invalid Move")
  }
  "check_move(0, 2, 1, 2) should be false (R1=> Blocked/ invalid Move)" in {
    Test.move(0, 2, 1, 2) shouldBe ("Invalid Move")
  }
  "check_move(1, 1, 0, 2) should be false (B1=> Blocked/ invalid Move)" in {
    Test.move(1, 1, 0, 2) shouldBe ("Invalid Move")
  }
  "check_move(4, 1, 4, 4) should be false (P1=> Blocked/ invalid Move)" in {
    Test.move(4, 1, 4, 4) shouldBe ("Invalid Move")
  }


  "empty_field(4, 1) should be true (Empty Field)" in {
    Test.empty_field(3, 5) shouldBe (true)
  }