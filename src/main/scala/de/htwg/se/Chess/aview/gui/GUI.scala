package de.htwg.se.Chess.aview.gui

import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import scalafx.scene.layout.StackPane
import scalafx.scene.shape.Circle
import scalafx.scene.layout.HBox
import scalafx.geometry.Insets
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.text.Text
import scalafx.scene.effect.DropShadow

object ScalaFXHelloWorld extends JFXApp3 {
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      //    initStyle(StageStyle.Unified)
      title = "Chess Game"
      scene = new Scene {
        fill = Color.rgb(38, 38, 38)
        content = new HBox {
          padding = Insets(50, 80, 50, 80)
          children = Seq(
            new Text {
              text = "Chess"
              style = "-fx-font: normal bold 100pt sans-serif"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(Red, DarkRed))
            },
            new Text {
              text = "Game"
              style = "-fx-font: italic bold 100pt sans-serif"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(White, DarkGray)
              )
              effect = new DropShadow {
                color = DarkGray
                radius = 15
                spread = 0.25
              }
            }
          )
        }
      }
    }
  }
}