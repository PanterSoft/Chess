package de.htwg.se.Chess.aview

import scala.swing._
import de.htwg.se.Chess.util.Observer
import de.htwg.se.Chess.controller.Controller
import scala.swing.Reactions.Reaction
import de.htwg.se.Chess.controller.GameState
import javax.swing.border.EmptyBorder
import scala.swing.event.Key
import scala.swing.event.ButtonClicked

class SwingGUI(controller: Controller) extends Frame with Observer:

    val selection_system:TrafficSystem = new TrafficSystem()

    override def update: Unit =
        println(controller.board_to_string_c())
        println(GameState.message(controller.game_state))
        controller.game_state=GameState.NO_WINNER_YET

    new Frame {
        title = "Chess Game"
        preferredSize = new Dimension(800, 600)
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
                })
                contents += new MenuItem(Action("Redo") {
                    controller.redo()
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
                if(selection_system.currentState.isInstanceOf[Yellow])
                    selection_system.changeState()
                    println("Move!!!")
                else
                    selection_system.changeState()


        pack()
        centerOnScreen()
        open()
    }