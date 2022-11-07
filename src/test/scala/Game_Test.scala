package de.htwg.se.Chess
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import javax.swing.text.PlainView
import java.io.ByteArrayInputStream
import scala.io.StdIn.readLine
import scala.io.StdIn
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers
import scala.collection.immutable.VectorMap

class GameSpec extends AnyWordSpec:
  val Test = Game()

  val test_map = VectorMap("A1"->"R1", "B1"-> "k1", "C1"->"B1", "D1"->"Q1", "E1"->"K1", "F1"->"B1", "G1"->"k1", "H1"->"R1", "A2"->"P1", "B2"->"P1", "C2"->"P1", "D2"->"P1", "E2"->"P1", "F2"->"P1", "G2"->"P1", "H2"->"P1", "A8"->"R2", "B8"->"k2", "C8"->"B2", "D8"->"Q2", "E8"->"k2", "F8"->"B2", "G8"->"k2", "H8"->"R2", "A7"->"P2", "B7"->"P2", "C7"->"P2", "D7"->"P2", "E7"->"P2", "F7"->"P2", "G7"->"P2", "H7"->"P2")

    val test_empty_map = VectorMap("A1"->"  ", "B1"-> "  ", "C1"->"  ", "D1"->"  ", "E1"->"  ", "F1"->"  ", "G1"->"  ", "H1"->"  ", "A2"->"  ", "B2"->"  ", "C2"->"  ", "D2"->"  ", "E2"->"  ", "F2"->"  ", "G2"->"  ", "H2"->"  ", "A3"->"  ", "B3"-> "  ", "C3"->"  ", "D3"->"  ", "E3"->"  ", "F3"->"  ", "G3"->"  ", "H3"->"  ", "A4"->"  ", "B4"->"  ", "C4"->"  ", "D4"->"  ", "E4"->"  ", "F4"->"  ", "G4"->"  ", "H4"->"  ", "A5"->"  ", "B5"-> "  ", "C5"->"  ", "D5"->"  ", "E5"->"  ", "F5"->"  ", "G5"->"  ", "H5"->"  ", "A6"->"  ", "B6"->"  ", "C6"->"  ", "D6"->"  ", "E6"->"  ", "F6"->"  ", "G6"->"  ", "H6"->"  ", "A7"->"  ", "B7"-> "  ", "C7"->"  ", "D7"->"  ", "E7"->"  ", "F7"->"  ", "G7"->"  ", "H7"->"  ", "A8"->"  ", "B8"->"  ", "C8"->"  ", "D8"->"  ", "E8"->"  ", "F8"->"  ", "G8"->"  ", "H8"->"  ")

  val test_field = test_empty_map.++(test_map)

  "Game" should {

    /**
      * Playfield Tests
      */

    val eol = sys.props("line.separator")

    "have a top_row as string of form " +
    "'   A    B    C    D    E    F    G    H  '" in {
      Game().top_row() shouldBe("   A    B    C    D    E    F    G    H  " + eol)
    }

    "have a border_row as string of form " +
    "'   +----+----+----+----+----+----+----+----+'" in {
      Game().border_row() shouldBe("+----+----+----+----+----+----+----+----+" + eol)
    }

    """have a First row as String of form '/----+----+----+----+----+----+----+----\'""" in {
      Game().first_last_row() shouldBe("""/----+----+----+----+----+----+----+----\""" + eol)
    }

    "have a Border row as string of form '|----+----+----+----+----+----+----+----|'" in {
      Game().border_row() shouldBe("+----+----+----+----+----+----+----+----+" + eol)
    }

    "newGame() should init state array" in {
      Test.newGame() shouldBe (test_empty_map.++(test_map))
    }
  }

  "empty_field(test_field, 'A5') should be true (Empty Field)" in {
    Test.empty_field(test_field, "A5") shouldBe (true)
  }



  //"move(0, 1, 0, 2) should be true (P1=> empty field)" in {
  //  Test.move(test_field, "A1", "A2") shouldBe ("")
  //}
  //"move(0, 0, 0, 2) should be true (R1=> empty field)" in {
  //  Test.move(0, 0, 0, 2) shouldBe ("")
  //}
  //"move(6, 0, 7, 2) should be true (k1=> empty field)" in {
  //  Test.move(6, 0, 7, 2) shouldBe ("")
  //}
  //"move(1, 1, 1, 2) should be true (P1=> empty field)" in {
  //  Test.move(1, 1, 1, 2) shouldBe ("")
  //}
  //"move(2, 0, 1, 1) should be true (B1=> empty field)" in {
  //  Test.move(2, 0, 1, 1) shouldBe ("")
  //}
  //"move(2, 1, 2, 3) should be true (P1=> empty field)" in {
  //  Test.move(2, 1, 2, 3) shouldBe ("")
  //}
  //"move(3, 0, 2, 1) should be true (Q1=> empty field)" in {
  //  Test.move(3, 0, 2, 1) shouldBe ("")
  //}
  //"move(2, 1, 2, 2) should be true (Q1=> empty field)" in {
  //  Test.move(2, 1, 2, 2) shouldBe ("")
  //}
  //"move(4, 0, 3, 0) should be true (K1=> empty field)" in {
  //  Test.move(4, 0, 3, 0) shouldBe ("")
  //}
  //"move(0, 6, 0, 5) should be true (P2 1 forward)" in {
  //  Test.move(0, 6, 0, 5) shouldBe ("")
  //}
  //"move(1, 6, 1, 4) should be true (P2 2 forward)" in {
  //  Test.move(1, 6, 1, 4) shouldBe ("")
  //}
  //"move(0, 3, 1, 4) should be true (P1 Attack P2)" in {
  //  Test.move(0, 3, 1, 4) shouldBe ("")
  //}
  //"move(0, 5, 1, 4) should be true (P2 Attack P1)" in {
  //  Test.move(0, 5, 1, 4) shouldBe ("")
  //}
//
//
  //"move(3, 0, 2, 1) should be false (K1=> Blocked/ invalid Move)" in {
  //  Test.move(3, 0, 2, 1) shouldBe ("Invalid Move")
  //}
  //"move(2, 2, 3, 1) should be false (Q1=> Blocked/ invalid Move)" in {
  //  Test.move(2, 2, 3, 1) shouldBe ("Invalid Move")
  //}
  //"move(1, 0, 3, 1) should be false (k1=> Blocked/ invalid Move)" in {
  //  Test.move(1, 0, 3, 1) shouldBe ("Invalid Move")
  //}
  //"move(0, 2, 1, 2) should be false (R1=> Blocked/ invalid Move)" in {
  //  Test.move(0, 2, 1, 2) shouldBe ("Invalid Move")
  //}
  //"move(1, 1, 0, 2) should be false (B1=> Blocked/ invalid Move)" in {
  //  Test.move(1, 1, 0, 2) shouldBe ("Invalid Move")
  //}
  //"move(4, 1, 4, 4) should be false (P1=> Blocked/ invalid Move)" in {
  //  Test.move(4, 1, 4, 4) shouldBe ("Invalid Move")
  //}