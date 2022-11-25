//package de.htwg.se.Chess.aview
//
//import de.htwg.se.Chess.controller.Controller
//import de.htwg.se.Chess.aview.tui
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
//    def match_pattern(test: Option[String])=
//        test match {
//                case None => ""
//                case Some(s) => s
//            }
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
//    val expected_help_field = """  /-----------------------------------\
//  |            HELP TABLE             |
//  |-----------------------------------|
//  |   help              (Display help)|
//  |   exit             (Close process)|
//  |   undo            (Undo Operation)|
//  |   redo            (Redo Operation)|
//  |                                   |
//  |   move Pos_now Pos_new (make Move)|
//  \-----------------------------------/""" + eol
//
//    val expected_welcome_message = """  /-----------------------------------\
//  |       Schach - Chess - Game       |
//  |-----------------------------------|
//  |      Textbased User Interface     |
//  |         HTWG Konstanz 2022        |
//  |              v1.0.0               |
//  \-----------------------------------/""" + eol
//
//    "recognize invalid input" in {
//        match_pattern(tui_test.commands("hello world")) should be("ERROR! Wrong //usage! Try help !")
//    }
//    "recognize the input 'help' as returning a help signal" in {
//        match_pattern(tui_test.commands("help")) should be(expected_help_field)
//    }
//    "recognize the input 'exit' as returning a quit signal" in {
//        match_pattern(tui_test.commands("exit")) should be("Goodbye :)")
//    }