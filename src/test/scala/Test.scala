package de.htwg.se.Chess
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import javax.swing.text.PlainView
import java.io.ByteArrayInputStream
import scala.io.StdIn.readLine
import scala.io.StdIn
import org.scalatest.matchers.must.Matchers

class ChessSpec extends AnyWordSpec:
  "Chess" should {

    /**
      * Playfield Tests
      */

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

    val expected_field = """/----+----+----+----+----+----+----+----\
|    |    |    |    |    |    |    |    |
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    |
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    |
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    |
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    |
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    |
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    |
+----+----+----+----+----+----+----+----+
|    |    |    |    |    |    |    |    |
\----+----+----+----+----+----+----+----/"""

    "have a Play Field as string of form '\n" + expected_field + "'" in {
      Game().field(8,8) shouldBe(expected_field)
    }
  }
