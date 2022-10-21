import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import javax.swing.text.PlainView
class ChessSpec extends AnyWordSpec:
  "Chess" should {
    """have a First row as String of form '/---+---+---+---+---+---+---+---\'""" in {
      first_last_row() shouldBe("""/---+---+---+---+---+---+---+---\""" + eol)
    }

    "have a Cell row as string of form '|---+---+---+---+---+---+---+---|'" in {
      cell_row() shouldBe("|   |   |   |   |   |   |   |   |" + eol)
    }

    "have a Border row as string of form '|---+---+---+---+---+---+---+---|'" in {
      border_row() shouldBe("+---+---+---+---+---+---+---+---+" + eol)
    }

    val expected_field = """/---+---+---+---+---+---+---+---\
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |
\---+---+---+---+---+---+---+---/"""

    "have a Play Field as string of form '\n" + expected_field + "'" in {

      play_field(8,8) shouldBe(expected_field + eol)
    }

  }
