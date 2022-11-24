package de.htwg.se.Chess.aview

import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.controller.SolveCommand
import de.htwg.se.Chess.controller.GameState
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.util.Observer

class tui(controller: Controller) extends Observer{

    val eol = sys.props("line.separator")

    controller.add(this)
    println(welcomeMessage)
    update

    def process(in: String): Unit =
        commands(in) match {
            case None =>
            case Some(s) => println(s)
        }

    def commands(in: String) : Option[String] =
        in.split(" ").toList match {
            case "exit" :: Nil => Some("Goodbye :)")
            case "help" :: Nil => Some(helpString)
            case "undo" :: Nil => controller.undo; None
            case "redo" :: Nil => controller.redo; None
            case "move" :: pos_old :: pos_new :: Nil => controller.domove // problem wenn man move A2 A4e eingibt dann stürzt das programm ab
                val before_move = controller.field
                if (controller.last_turn() == controller.get_player_c(pos_old))
                    controller.move_c(pos_old, pos_new)
                    controller.change_player()
                val after_move = controller.field
                if (before_move == after_move)
                    println("No valid move.")
                else
                    controller.check_winner
                None
            case _ => Some(errorMessage)
        }

    def helpString: String = """  /-----------------------------------\
  |            HELP TABLE             |
  |-----------------------------------|
  |   help              (Display help)|
  |   exit             (Close process)|
  |   undo            (Undo Operation)|
  |   redo            (Redo Operation)|
  |                                   |
  |   move Pos_now Pos_new (make Move)|
  \-----------------------------------/""" + eol

    def welcomeMessage: String = """  /-----------------------------------\
  |       Schach - Chess - Game       |
  |-----------------------------------|
  |      Textbased User Interface     |
  |         HTWG Konstanz 2022        |
  |              v1.0.0               |
  \-----------------------------------/""" + eol

    def errorMessage: String =
        "ERROR! Wrong usage! Try help !"

    override def update: Boolean =
        println(controller.board_to_string_c)
        println(GameState.message(controller.game_state))
        controller.game_state=GameState.IDLE
        true
}