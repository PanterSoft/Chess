package de.htwg.se.Chess.aview.gui

import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.scene.layout.Pane

case class PieceRegion() extends Pane {

  val maybePiece = ObjectProperty[Option[Piece]](None)

  def maybePiece_=(value: Option[Piece]): Unit = {
    maybePiece() = value
  }

  val theStyle = StringProperty("")

  maybePiece.onChange((a, b, c) => {
    theStyle() = maybePiece()
        .map(p => p.color.toString() + "_" + p.name + ".png")
        .map(x => "-fx-background-image: url('file:src/main/scala/de/htwg/se/Chess/aview/gui/Resources/pieces/" + x + "'); "+
                  "-fx-background-position: center center; "+
                  "-fx-background-size: cover, auto;" +
                  "-fx-background-insets: -20, -10;" +
                  "-fx-background-repeat: no-repeat;")
        .getOrElse("")
  })

  //

  style <== theStyle

  prefHeight = 60
  prefWidth = 60

}