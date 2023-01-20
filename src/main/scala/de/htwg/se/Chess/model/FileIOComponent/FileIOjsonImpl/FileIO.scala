package de.htwg.se.Chess.model.FileIOComponent.FileIOjsonImpl

import de.htwg.se.Chess.model.FileIOComponent.FileIOInterface
import de.htwg.se.Chess.model.BoardInterface
import de.htwg.se.Chess.model.Board
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.collection.immutable.VectorMap

import scala.util.{Failure, Success, Try}

import scala.io.Source
import de.htwg.se.Chess.model.Board

import de.htwg.se.Chess.model.*
import scala.annotation.meta.field
import scala.collection.mutable._
import scala.collection.mutable.Queue

import scalafx.stage.FileChooser
import scalafx.stage.FileChooser.ExtensionFilter


// Json Libs
import java.io._
import play.api.libs.json._

class FileIO extends FileIOInterface {

  override def load(game: BoardInterface): BoardInterface =
    val source: String = Source.fromFile("board.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    var field: BoardInterface = Board()


    // @TODO: hier weitermachen
    for (index <- 0 until size * size) {
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val value = (json \\ "cell")(index).as[String]
      value match {
        case " " => field = field.put(Stone.Empty, row, col)
        case "□" => field = field.put(Stone.B, row, col)
        case "■" => field = field.put(Stone.W, row, col)
        case _ =>
      }
    }
    game



  override def save(game: BoardInterface) =
    //get timestamp, path and create filename
    val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))
    val path = "src/main/savegames/"
    val filename = path + "game_" + timestamp + ".json"
    //write to game file
    val pw = new PrintWriter(new File(filename))
    pw.write(vectorMapToJson(game))
    pw.close()

  def vectorMapToJson(board_object: BoardInterface) =
    val tmp = board_object.field.board
    Json.obj(
      "field" -> Json.obj(
        "field_entry" -> Json.toJson(
          for {
            pos <- 1 until board_array.size + 1
            figure <- 1 until board_array.size + 1
          } yield {
            Json.obj(
              "pos" -> pos,
              "figure" -> figure,
            )
          }
        )
      )
    )

}