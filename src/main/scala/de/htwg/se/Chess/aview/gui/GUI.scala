package de.htwg.se.Chess.aview.gui

import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.application.JFXApp3.PrimaryStage
import de.htwg.se.Chess.model.Board.apply
import de.htwg.se.Chess.model.Board

object App extends JFXApp3 {
  override def start(): Unit =
    val field = Board()
    val view = new View(field)

    stage = new PrimaryStage {
      title.value = "Chess Game"
      scene = view.scene
    }
}