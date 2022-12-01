package de.htwg.se.Chess.aview.gui

import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color

object ChessStage extends JFXApp3 {
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Chess"
      width = 1000
      height = 1000
      scene = new Scene {
        fill = Black
        content = new Rectangle {
          x = 250
          y = 250
          width = 800
          height = 800
          fill = Color.rgb(255, 0, 0)
        }
      }
    }
  }
}