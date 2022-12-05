package de.htwg.se.Chess.aview.gui

import scalafx.scene.layout.Region
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.aview.gui.api.Position


case class SquareRegion(field_pos: Position) extends Region {

  def blackOrWhite = {
    val isBlack = if (field_pos._1 % 2 == 0) (field_pos._2 % 2) == 1 else (field_pos._2 % 2) == 0
    if (isBlack)
      "-fx-background-color: rgba(117, 117, 117, 0.7);"
    else
      "-fx-background-color: rgb(230,230,230);"
  }

  style = blackOrWhite

  prefHeight = 80
  prefWidth = 80

}