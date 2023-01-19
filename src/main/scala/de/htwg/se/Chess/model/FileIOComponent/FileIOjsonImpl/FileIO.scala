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
import play.api.libs.json.*

import scala.io.Source
import de.htwg.se.Chess.model.Board
//import de.htwg.se.Chess.controller.controllerComponent.GameState
import de.htwg.se.Chess.model.*
import scala.annotation.meta.field
import scala.collection.mutable._
import scala.collection.mutable.Queue

class FileIO extends FileIOInterface {

  override def load(game: BoardInterface): BoardInterface =
    import scalafx.stage.FileChooser
    import scalafx.stage.FileChooser.ExtensionFilter
    import java.io.File

    val fileChooser = new FileChooser()
    fileChooser.setTitle("Load Game")
    fileChooser.setInitialDirectory(new File("src/main/savegames/"))
    // Set shown file filter to JSON files only
    fileChooser.extensionFilters.addAll(
      new ExtensionFilter("JSON Files", "*.json")
    )
    val seletedFile = fileChooser.showOpenDialog(null)

    if(seletedFile != null) {
      val source: String = Source.fromFile(seletedFile).getLines.mkString
      val json: JsValue = Json.parse(source)
      JsonToGame(json)
    } else {
      game
    }


  override def save(game: BoardInterface): Unit =
    import java.io._
    import scala.xml._
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
    //"state" -> JsString(game.field.controller.last_turn)
  )

  def get_pos() =
    var y = Range.inclusive(1,8)
    val x_map = Map(1->"A", 2->"B", 3->"C", 4->"D", 5->"E", 6->"F", 7->"G", 8->"H")

    for (i<-x_map) yield
      for (j<-y) yield
        var queue = Queue()
        queue.enqueue(x_map.get(i(1)(0)) + j.toString())
    //@todo  alle elemente in queue einfügen
    // ein element rausziehen

  def vectorMapToJson(vector: VectorMap[String, String]) =
    Json.obj(
      "entry" -> {for(i <- queue) yield (Json.obj("pos:" -> vector.get(i)),
                                      "name" -> vector.get(i))
                  }
    )

  def JsonToVectorMap(vectorMapJson: JsValue, mtype: String) = {

  }

  def JsonToGame(gameJson: JsValue) = {

    Board()
  }
}