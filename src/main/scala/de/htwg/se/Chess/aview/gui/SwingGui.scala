package de.htwg.se.Chess.aview

import scala.swing._
import de.htwg.se.Chess.util.Observer
import de.htwg.se.Chess.controller.Controller
import scala.swing.Reactions.Reaction
import de.htwg.se.Chess.controller.GameState
import javax.swing.border.EmptyBorder
import scala.swing.event.Key
import scala.swing.event.ButtonClicked
import scalafx.scene.input.KeyCode.R
import scalafx.scene.input.KeyCode.G
import scala.swing.event.PopupMenuCanceled

class SwingGUI(controller: Controller) extends Frame with Observer:
    var pos1: String = ""
    var pos2: String = ""
    val selection_system:TrafficSystem = new TrafficSystem()

    override def update: Unit =
        println(controller.board_to_string_c())
        println(GameState.message(controller.game_state))
        controller.game_state=GameState.NO_WINNER_YET

    new Frame {
        title = "Chess Game"
        preferredSize = new Dimension(800, 800)
        resizable = true

        menuBar = new MenuBar {
            contents += new Menu("File") {
                mnemonic = Key.F
                contents += new MenuItem(Action("New") {
                    // implement newGame() method in Controller (unwichtig)
                })
                contents += new Separator
                contents += new MenuItem(Action("Quit") {
                    println("Bye!")
                    System.exit(0)
                })
            }
            contents += new Menu("Edit") {
                mnemonic = Key.E
                contents += new MenuItem(Action("Undo") {
                    controller.undo()
                    update
                })
                contents += new MenuItem(Action("Redo") {
                    controller.redo()
                    update
                })
            }
        }
        contents = contentPanel

        def contentPanel = new BorderPanel {
            add(new Label("Welcome to Chess"), BorderPanel.Position.North)
            add(new CellPanel(), BorderPanel.Position.Center)
        }

        def update: Unit =
            contents = contentPanel

        class CellPanel() extends GridPanel(8, 8):
            border = EmptyBorder(20,20,20,20)
            printField


            def printField =
                controller.field.board.map(x =>
                    contents += new CellButton(x._1, x._2))

        class CellButton(pos: String, figure: String) extends Button(figure):
            listenTo(mouse.clicks)
            listenTo(keys)
            reactions += {
                // put a stone on the playfield
                case ButtonClicked(button) =>
                    selectionHandler(pos, figure)
            }

        def selectionHandler(pos: String, figure: String) =
            if (pos1 != pos && pos2 != pos)
                if(selection_system.currentState.isInstanceOf[Green] && controller.last_turn() == controller.get_player_c(pos))
                    pos1 = pos
                    println(pos1)
                    selection_system.changeState()
                    println(selection_system.currentState.isInstanceOf[Green])
                    println(selection_system.currentState.isInstanceOf[Yellow])
                else if (selection_system.currentState.isInstanceOf[Yellow])
                    pos2 = pos
                    println(pos2)
                    selection_system.changeState()
                    controller.domove()
                    controller.move_c(pos1, pos2)
                    update
                    selection_system.changeState()
                    pos1 = ""
                    pos2 = ""
            else
                println("Invalid Move" + pos1 + " " + pos2)


        pack()
        centerOnScreen()
        open()
    }