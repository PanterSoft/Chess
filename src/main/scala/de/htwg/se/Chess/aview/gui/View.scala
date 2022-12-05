package de.htwg.se.Chess.aview.gui

import scalafx.geometry.{HPos, Insets}
import scalafx.scene.Scene
import scalafx.scene.layout._
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.aview.gui.api.Position

class View(field: Board) {

  val centerPane = new GridPane {
    hgap = 10
    vgap = 10
    padding = Insets(10)
    columnConstraints = List(new ColumnConstraints {
        halignment = HPos.Right
        minWidth = 65
      },
      new ColumnConstraints {
        halignment = HPos.Left
        minWidth = 200
      }
    )
  }

  lazy val tiles: Seq[Tile] = {
    val positions = for {
      x <- 0 until 8
      y <- 0 until 8
    } yield (x, y)
    positions.map { position =>
      val x = position._1
      val y = position._2
      val square = SquareRegion(position)
      val actionOverlay = ActionOverlayRegion(field, position)
      val actions = MoveAndReplaceChoice(field, position)
      val chessPiece = PieceRegion()
      chessPiece.maybePiece <==  field.posPieces(x)(y)
      Tile(position, square, actionOverlay, chessPiece, actions) // actionOverlay, chessPiece, actions
    }
  }

  def board(): GridPane = {
    val board = new GridPane()
    tiles.foreach(tile => {
      board.add(new StackPane() {
        children = tile.regions
      }, tile.x, tile.y)
    })
    board
  }

  val game = new BorderPane() {
    center = new StackPane() {
      children = board()
    }
  }

  val scene = new Scene(640, 640) {
    root = new AnchorPane() {
      children = List(
        game
      )
    }
  }

  case class Tile(position: Position, regions: Region*) {
    def x = position._1
    def y = position._2
  }
}