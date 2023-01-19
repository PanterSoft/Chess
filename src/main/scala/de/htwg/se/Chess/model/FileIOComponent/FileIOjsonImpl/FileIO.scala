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
import java.io.File

// Json Libs
import spray.json._
import DefaultJsonProtocol._

class FileIO extends FileIOInterface {

  override def load(game: BoardInterface): BoardInterface =
    val fileChooser = new FileChooser()
    fileChooser.setTitle("Load Game")
    fileChooser.setInitialDirectory(new File("src/main/savegames/"))
    // Set shown file filter to JSON files only
    fileChooser.extensionFilters.addAll(
      new ExtensionFilter("JSON Files", "*.json")
    )
    val selectedFile = fileChooser.showOpenDialog(null)

    if(selectedFile != null) {
      val source: String = Source.fromFile(selectedFile).getLines.mkString
      val jsonAst = source.parseJson
      //@todo need BoardInterafce, not Vectormap
      jsonToVectorMap(jsonAst)
    } else {
      game
    }


  override def save(game: BoardInterface): Unit =

    //get timestamp, path and create filename
    val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))
    val path = "src/main/savegames/"
    val filename = path + "game_" + timestamp + ".json"
    //write to game file
    val pw = new PrintWriter(new File(filename))
    pw.write(gameToJson(game).toString())
    pw.close()

  def gameToJson(game: BoardInterface) =
  Json.obj(
    "game" -> vectorMapToJson(game.field.board),
  )

  def vectorMapToJson(vector: VectorMap[String, String]) = {
    vector.toJson
  }

  def jsonToVectorMap(json): VectorMap[String, String] = {
    json.convertTo[VectorMap]
  }

  /*def vectorMapToJson(vector: VectorMap[String, String]) =
    var queue = get_pos()
    Json.obj(
      "entry" -> {for(i <- queue) yield (Json.obj("pos:" -> queue.dequeue()), "name" -> vector.get(queue.dequeue()))}
    )


  def JsonToGame(gameJson: JsValue) = {
    Board()
  }
*/
}