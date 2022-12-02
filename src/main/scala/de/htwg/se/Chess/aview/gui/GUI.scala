package de.htwg.se.Chess.aview.gui

import scalafx.Includes._
import scalafx.stage.Stage
import scalafx.scene.Scene
import scalafx.scene.layout.{ Pane, BorderPane }
import scalafx.scene.shape.Rectangle
import javafx.scene.paint.{ Color => JFXColor }
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.shape.Circle
import scalafx.scene.layout.HBox
import scalafx.geometry.Insets
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.text.Text
import scalafx.scene.effect.DropShadow
import scala.io.Source._
import de.htwg.se.Chess.controller._
import de.htwg.se.Chess.util.Observer

class gui(controller: Controller) extends JFXApp3 with Observer {
  controller.add(this)

  override def update: Boolean = {
    println(controller.board_to_string_c())
    println(GameState.message(controller.game_state))
    controller.game_state=GameState.NO_WINNER_YET
    true
  }
  override def start(): Unit = {
    stage = new PrimaryStage {
      title = "Chess Game"
      width = 800
      height = 600

      scene = new Scene(new scalafx.scene.Scene(root))

      width onChange show_g
      height onChange show_g
    }

    /**
      * Root
      *     |_BorderPane
      *                 |_centerPane
      *                 |           |_showBoard
      *                 |_leftPane
      *                           |_showHistory
      *
      */

    lazy val root = new BorderPane {
      top = topPane
      center = centerPane
      left = leftPane
      right = rightPane
      bottom = bottomPane
    }

    lazy val topPane: Pane = new Pane {
    }
    lazy val centerPane: Pane = new Pane {
      children = showBoard()
    }

    lazy val leftPane: Pane = new Pane {
      children = showHistory()
    }

    lazy val rightPane: Pane = new Pane {
    }

    lazy val bottomPane: Pane = new Pane {
      children = showButtonBar()
    }

    def show_g: Unit = {
      centerPane.children = showBoard(centerPane.width.get, centerPane.height.  get)
      leftPane.children = showHistory(leftPane.width.get, leftPane.height.get)
      bottomPane.children = showButtonBar(bottomPane.width.get, bottomPane. height.get)
    }

    def showBoard(paneWidth: Double = 784.0, paneHeight: Double = 562.0) = {
      val offX = 10.0
      val offY = 25.0
      val boardWidth = paneWidth - offX // 600
      val boardHeight = paneHeight - offY // 400

      new Rectangle {
        x = offX
        y = offY
        width = boardWidth
        height = boardHeight
        fill = JFXColor.DEEPSKYBLUE
      }
    }

    def showHistory(paneWidth: Double = 784.0, paneHeight: Double = 562.0) = {
      val offX = 25.0
      val offY = 25.0
      val boardWidth = 80//paneWidth - offX
      val boardHeight = 400//paneHeight - offY

      new Rectangle {
        x = offX
        y = offY
        width = boardWidth
        height = boardHeight
        fill = JFXColor.DEEPPINK
      }
    }

    def showButtonBar(paneWidth: Double = 784.0, paneHeight: Double = 562.0) = {
      val offX = 115.0
      val offY = 10
      val boardWidth = 600 //paneWidth - offX
      val boardHeight = 50//paneHeight - offY

      new Rectangle {
        x = offX
        y = offY
        width = boardWidth
        height = boardHeight
        fill = JFXColor.GREEN
      }
    }
  }
}