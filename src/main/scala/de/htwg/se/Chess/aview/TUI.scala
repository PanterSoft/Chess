package de.htwg.se.Chess.aview

import de.htwg.se.Chess.controller.Controller
import de.htwg.se.Chess.controller.SolveCommand
import de.htwg.se.Chess.controller.GameState
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.util.Observer

class tui(controller: Controller) extends Observer{
    val boss = new Boss(None)
    val supervisor = new Supervisor(Some(boss))
    val agent = new Agent(Some(supervisor))

    controller.add(this)
    println(welcomeMessage)
    update

    def process(in: String): Unit =
        commands(in) match {
            case None =>
            case Some(s) => println(s)
        }

    def commands(in: String) : Option[String] =
        //println("Passing events")
        val events = Array(
          Event(1, "exit"),
          Event(2, "help"),
          Event(3, "undo"),
          Event(3, "redo"),
          Event(5, "sth else"),
          Event(4, "move")
        )
        //events foreach {(e : Event) => agent.handleEvent(e)}
        in.split(" ").toList match {
            case "exit" :: Nil => println(agent.handleEvent(events(0))); None
                //Some("Goodbye :)")
            case "help" :: Nil => println(agent.handleEvent(events(1))); None
                //Some(helpString)
            case "undo" :: Nil => agent.handleEvent(events(2))
                controller.undo; None
            case "redo" :: Nil => agent.handleEvent(events(3))
                controller.redo; None
            case "move" :: pos_old :: pos_new :: Nil => agent.handleEvent(events(5))
                controller.domove // problem wenn man move A2 A4e eingibt dann stürzt das programm ab
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
            case _ => println(agent.handleEvent(events(4))); None
                //Some(errorMessage)
        }

    override def update: Boolean =
        println(controller.board_to_string_c)
        println(GameState.message(controller.game_state))
        controller.game_state=GameState.IDLE
        true
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

val errorMessage: String = "ERROR! Wrong usage! Try help !"

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
        //println("CS Agent Handled event 1: " + e.title)
      case e if e.level < 3 => helpString
        //println("CS Agent Handled event 2: " + e.title)
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
    //val tui = new tui(controller: Controller)
    //controller.add(this)
    override def handleEvent(event: Event): String = {
        event match {
            case e if e.level < 3 => ""
            //println("Supervisor handled event 1: " + e.title)
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
      case e if e.level < 5 =>
        "Boss handled event: " + e.title
      case e if e.level > 3 => successor match {
        case Some(h: Handler) => h.handleEvent(e)
        case None => errorMessage
      }
    }
  }
}