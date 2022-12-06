package de.htwg.se.Chess.aview

import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.controller.SolveCommand
import de.htwg.se.Chess.controller.GameState
//import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.util.Observer
import scala.util.{Try,Success,Failure}
import scala.util.matching.Regex

class tui(controller: Controller) extends Observer{
    val boss = new Boss(None)
    val supervisor = new Supervisor(Some(boss))
    val agent = new Agent(Some(supervisor))

    val moveRegex: Regex = "move [A-H][1-8] [A-H][1-8]".r

    controller.add(this)
    println(welcomeMessage)
    update

    def process(in: String): Unit =
      commands(in) match
        case None =>
        case Some(s) => println(s)

    def readTextIn(in_new:String) : Try[List[String]] =
      Try(in_new.split(" ").toList)

    def checkRegex(input_string: String, regex_pattern: Regex): Boolean =
      regex_pattern.findPrefixOf(input_string) match
        case Some(s) => true
        case None => false

    def commands(in: String) : Option[String] =
        val events = Array(
          Event(1, "exit"),
          Event(2, "help"),
          Event(3, "undo"),
          Event(3, "redo"),
          Event(4, "sth else"),
          Event(5, "move")
        )

        readTextIn(in) match {
          case Success(value) =>
            value match
              case "exit" :: Nil => Some(agent.handleEvent(events(0)))
              case "help" :: Nil => Some(agent.handleEvent(events(1)))
              case "undo" :: Nil => agent.handleEvent(events(2))
                  controller.undo(); None
              case "redo" :: Nil => agent.handleEvent(events(3))
                  controller.redo(); None
              case "move" :: pos_now_in :: pos_new_in :: Nil =>
                if (checkRegex(in, moveRegex))
                  if (controller.last_turn() == controller.get_player_c   (pos_now_in))
                    controller.domove()
                    controller.move_c(pos_now_in, pos_new_in)
                    Some(agent.handleEvent(events(4)))
                  else
                    Some("Invalid Move!")
                else
                  Some(agent.handleEvent(events(5)))
              case _ => Some(agent.handleEvent(events(5)))
          case Failure(_) => Some(agent.handleEvent(events(4)))
        }

    override def update: Unit =
        println(controller.board_to_string_c())
        println(GameState.message(controller.game_state))
        controller.game_state=GameState.NO_WINNER_YET

    case class Event(level: Int, title: String)

    //Base handler class
    abstract class Handler {
      val successor: Option[Handler]
      def handleEvent(event: Event): String
    }

    //Customer service agent
    class Agent(val successor: Option[Handler]) extends Handler {
      override def handleEvent(event: Event): String = {
        event match {
          case e if e.level < 2 => "Goodbye :)"
          case e if e.level < 3 => helpString
          case e if e.level > 1 => {
            successor match {
              case Some(h: Handler) => h.handleEvent(e)
              case None => errorMessage
            }
          }
        }
      }
    }

    class Supervisor(val successor: Option[Handler]) extends Handler {
      override def handleEvent(event: Event): String = {
        event match {
            case e if e.level < 4 =>
              "Supervisor handled event 2: " + e.title
            case e if e.level > 2 => {
              successor match {
              case Some(h: Handler) => h.handleEvent(e)
              case None => errorMessage
            }
          }
        }
      }
    }
    class Boss(val successor: Option[Handler]) extends Handler {
      override def handleEvent(event: Event): String = {
        event match {
          case e if e.level < 5 => ""
          case e if e.level > 3 => successor match {
            case Some(h: Handler) => h.handleEvent(e)
            case None => errorMessage
          }
        }
      }
    }
}

val eol = sys.props("line.separator")

val helpString = """  /-----------------------------------\
  |            HELP TABLE             |
  |-----------------------------------|
  |   help              (Display help)|
  |   exit             (Close process)|
  |   undo            (Undo Operation)|
  |   redo            (Redo Operation)|
  |                                   |
  |   move Pos_now Pos_new (make Move)|
  \-----------------------------------/""" + eol

val welcomeMessage = """  /-----------------------------------\
  |       Schach - Chess - Game       |
  |-----------------------------------|
  |      Textbased User Interface     |
  |         HTWG Konstanz 2022        |
  |              v1.0.0               |
  \-----------------------------------/""" + eol

val errorMessage = "ERROR! Wrong usage! Try help !"