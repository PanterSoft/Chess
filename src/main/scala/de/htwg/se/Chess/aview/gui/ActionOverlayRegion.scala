package de.htwg.se.Chess.aview.gui

import scalafx.Includes._
import scalafx.beans.property.BooleanProperty
import scalafx.scene.layout.Region
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.aview.gui.api.Position

object ActionOverlayRegion {
  val circle = "-fx-shape: \"M 0.0 25.0 a 25.0 25.0 0 1 1 50.0 0 a 25.0 25.0 0 1 1 -50.0 0 Z\";"
  val somePadding = "-fx-padding: 5px; -fx-border-insets: 5px; -fx-background-insets: 5px;"
  val lightBlue = "-fx-background-color: rgba(173,216,230, 0.8);"
  val lightRed = "-fx-background-color: rgba(255,102,102, 0.6);"
}

case class ActionOverlayRegion(model: Board, position: Position) extends Region {

  val actionExistsForField = BooleanProperty(false)
  //model.actions.onChange((a, b, c) => {
  //  actionExistsForField() = model.actions().count(a => a.target == position) > 0
  //})

  val isEnemyAtActionTargetField = BooleanProperty(false)
  //model.actions.onChange((a, b, c) => {
    //isEnemyAtActionTargetField() = model.actions().exists(_.target == position) &&
      //model.posPieces(position._1)(position._2)().isDefined
  //})

  val isCurrentlySelectedField = BooleanProperty(false)
  //model.board.onChange((a, b, c) => {
    //isCurrentlySelectedField() = model.currentField().contains(position)
  //})

  import ActionOverlayRegion._
  style <== (
    when(isEnemyAtActionTargetField)
      choose lightRed
      otherwise
        (when(actionExistsForField or isCurrentlySelectedField)
          choose lightBlue
          otherwise ""
        )
    ) + circle + somePadding

  prefHeight = 80
  prefWidth = 80

}
