package de.htwg.se.Chess.model.FileIOComponent.FileIOjsonImpl

import de.htwg.se.Chess.model.FileIOComponent.FileIOInterface
import de.htwg.se.Chess.model.BoardInterface
//import de.htwg.se.Chess.model.Board
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.collection.immutable.VectorMap

import scala.util.{Failure, Success, Try}

import scala.io.Source

import de.htwg.se.Chess.model.*
import scala.annotation.meta.field
import scala.collection.mutable._
import scala.collection.mutable.Queue

import scalafx.stage.FileChooser
import scalafx.stage.FileChooser.ExtensionFilter

// Json Libs
import java.io._
import play.api.libs.json._
import scala.languageFeature.postfixOps

class FileIO extends FileIOInterface {

  override def load(): Board =
    val source: String = Source.fromFile("board.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    var field: VectorMap[String, String] = VectorMap[String, String]()

    for (index <- 0 until 8 * 8) {
      val pos = (json \\ "pos")(index).as[String]
      val figure = (json \\ "figure")(index).as[String]
      field = field.updated(pos, figure)
    }
    Board(field)


  override def save(game: BoardInterface) =
    // write game to file
    val pw = new PrintWriter(new File("field.json"))
    pw.write(vectorMapToJson(game))
    pw.close

  def vectorMapToJson(board_object: BoardInterface): String =
    val tmp:VectorMap[String, String] = board_object.field.board
    var jsonData = Json.obj(
      "field" -> Json.obj(
        "field_entry" -> Json.toJson(
          for ((pos, figure) <- tmp) {
            Json.obj(
            "pos" -> pos,
            "figure" -> figure
            )
          }
        )
      )
    )
    Json.stringify(jsonData)
}