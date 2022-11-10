package de.htwg.se.Chess.aview
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import javax.swing.text.PlainView
import java.io.ByteArrayInputStream
import scala.io.StdIn.readLine
import scala.io.StdIn
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers

//class TUISpec extends AnyWordSpec:
//
//    val TUI = new tui
//
//    val expected_field = """   0    1    2    3    4    5    6    7
///----+----+----+----+----+----+----+----\
//| R1 | k1 | B1 | Q1 | K1 | B1 | k1 | R1 | 0
//+----+----+----+----+----+----+----+----+
//| P1 | P1 | P1 | P1 | P1 | P1 | P1 | P1 | 1
//+----+----+----+----+----+----+----+----+
//|    |    |    |    |    |    |    |    | 2
//+----+----+----+----+----+----+----+----+
//|    |    |    |    |    |    |    |    | 3
//+----+----+----+----+----+----+----+----+
//|    |    |    |    |    |    |    |    | 4
//+----+----+----+----+----+----+----+----+
//|    |    |    |    |    |    |    |    | 5
//+----+----+----+----+----+----+----+----+
//| P2 | P2 | P2 | P2 | P2 | P2 | P2 | P2 | 6
//+----+----+----+----+----+----+----+----+
//| R2 | k2 | B2 | Q2 | K2 | B2 | k2 | R2 | 7
//\----+----+----+----+----+----+----+----/"""
//
//    "commands(start) should create'\n" + expected_field + "'" in {
//        TUI.commands("start") shouldBe (expected_field)
//    }
//
//    val expected_help_field = """
//        ------------------------------------
//        |            HELP TABLE             |
//        |-----------------------------------|
//        |   start                           |
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
//        TUI.commands("help") shouldBe (expected_help_field)
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
//    "welcomeMessage() should create a String'" + expected_welcome_message + "'" //in {
//        TUI.welcomeMessage shouldBe (expected_welcome_message)
//    }
//
//    "commands(exit) should create a String (Goodbye :))" in {
//        TUI.commands("exit") shouldBe ("Goodbye :)")
//    }
//
//    val errorMessage = "ERROR! Wrong usage! Try \"help\" !"
//
//    "commands() should create a String'" + errorMessage + "'" in {
//        TUI.commands("") shouldBe (errorMessage)
//    }
//
//    val expected_field_start = """   0    1    2    3    4    5    6    7
///----+----+----+----+----+----+----+----\
//| R1 | k1 | B1 | Q1 | K1 | B1 | k1 | R1 | 0
//+----+----+----+----+----+----+----+----+
//| P1 | P1 | P1 | P1 | P1 | P1 | P1 | P1 | 1
//+----+----+----+----+----+----+----+----+
//|    |    |    |    |    |    |    |    | 2
//+----+----+----+----+----+----+----+----+
//|    |    |    |    |    |    |    |    | 3
//+----+----+----+----+----+----+----+----+
//|    |    |    |    |    |    |    |    | 4
//+----+----+----+----+----+----+----+----+
//|    |    |    |    |    |    |    |    | 5
//+----+----+----+----+----+----+----+----+
//| P2 | P2 | P2 | P2 | P2 | P2 | P2 | P2 | 6
//+----+----+----+----+----+----+----+----+
//| R2 | k2 | B2 | Q2 | K2 | B2 | k2 | R2 | 7
//\----+----+----+----+----+----+----+----/"""
//
//    "commands() should create a String'" + expected_field_start + "'" in {
//        TUI.commands("start") shouldBe (expected_field_start)
//    }