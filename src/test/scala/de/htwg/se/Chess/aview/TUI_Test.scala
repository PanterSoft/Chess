//package de.htwg.se.Chess.aview
//
//import de.htwg.se.Chess.controller.Controller
//
//import org.scalatest.wordspec.AnyWordSpec
//import org.scalatest.matchers.should.Matchers._
//import javax.swing.text.PlainView
//import java.io.ByteArrayInputStream
//import scala.io.StdIn.readLine
//import scala.io.StdIn
//import org.scalatest.matchers.must.Matchers
//import org.scalatest.matchers.should.Matchers
//import de.htwg.se.Chess.model.Board
//import de.htwg.se.Chess.model.History
//
//
//class TUISpec extends AnyWordSpec:
//    val eol = sys.props("line.separator")
//
//    val field = new Board()
//    val history = new History()
//    val controller = new Controller(field, history)
//    val tui_test = tui(controller)
//
//    val expected_field = "   A    B    C    D    E    F    G    H  " + """
///----+----+----+----+----+----+----+----\
//| R1 | k1 | B1 | Q1 | K1 | B1 | k1 | R1 | 1
//+----+----+----+----+----+----+----+----+
//| P1 | P1 | P1 | P1 | P1 | P1 | P1 | P1 | 2
//+----+----+----+----+----+----+----+----+
//|    |    |    |    |    |    |    |    | 3
//+----+----+----+----+----+----+----+----+
//|    |    |    |    |    |    |    |    | 4
//+----+----+----+----+----+----+----+----+
//|    |    |    |    |    |    |    |    | 5
//+----+----+----+----+----+----+----+----+
//|    |    |    |    |    |    |    |    | 6
//+----+----+----+----+----+----+----+----+
//| P2 | P2 | P2 | P2 | P2 | P2 | P2 | P2 | 7
//+----+----+----+----+----+----+----+----+
//| R2 | k2 | B2 | Q2 | k2 | B2 | k2 | R2 | 8
//\----+----+----+----+----+----+----+----/""" + eol
//
//    val expected_help_field = """
//        ------------------------------------
//        |            HELP TABLE             |
//        |-----------------------------------|
//        |   help              (Display help)|
//        |   exit             (Close process)|
//        |                                   |
//        |   move(x1 y1 x2 y2                |
//        |   after start current pos x y     |
//        |   then new pos x y {x y x y}      |
//        -------------------------------------
//        """
//
//    "commands(help) should create'\n" + expected_help_field + "'" in {
//        tui_test.commands("help") shouldBe (expected_help_field)
//    }
//
//    val expected_welcome_message = """
//        ------------------------------------
//        |       Schach - Chess - Game       |
//        |-----------------------------------|
//        |      Textbased User Interface     |
//        |         HTWG Konstanz 2022        |
//        |              v1.0.0               |
//        -------------------------------------
//        """
//
//    "welcomeMessage() should create a String" + expected_welcome_message in {
//        tui_test.welcomeMessage shouldBe (expected_welcome_message)
//    }
//
//    "commands(exit) should create a String (Goodbye :))" in {
//        tui_test.commands("exit") shouldBe ("Goodbye :)")
//    }
//
//    val errorMessage = "ERROR! Wrong usage! Try \"help\" !"
//
//    "commands() should create a String'" + errorMessage + "'" in {
//        tui_test.commands("") shouldBe (errorMessage)
//    }
//
//
//    "commands() should create a String'" + expected_field + "'" in {
//        tui_test.commands("start") shouldBe (expected_field)
//    }