package de.htwg.se.Chess.model.FileIOComponent.FileIOjsonImpl

import de.htwg.se.Chess.model.FileIOComponent.FileIOInterface
import de.htwg.se.Chess.model.BoardInterface
import de.htwg.se.Chess.model.Board
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


import scala.util.{Failure, Success, Try}
import play.api.libs.json.*

import scala.io.Source
import de.htwg.se.Chess.model.Board

class FileIO extends FileIOInterface {

  override def load(game: BoardInterface): Unit =
    import java.io._
    import scalafx.stage.FileChooser
    import scalafx.stage.FileChooser.ExtensionFilter
    val fileChooser = new FileChooser()
    fileChooser.setTitle("Load Game")
    fileChooser.setInitialDirectory(new File("src/main/savegames/"))
    // Set shown file filter to JSON files only
    fileChooser.extensionFilters.addAll(
      new ExtensionFilter("JSON Files", "*.json")
    )
    val seletedFile = fileChooser.showOpenDialog(null)
    
    val gameMode = Map((12, 4) -> "easy", (10, 4) -> "medium", (10, 5) -> "hard", (8, 5) -> "extrem")
    
    if(seletedFile != null && seletedFile.getName().contains(gameMode.get((game.field.rows, game.field.cols)).get)) {
      val source: String = Source.fromFile(seletedFile).getLines.mkString
      val json: JsValue = Json.parse(source)
      JsonToGame(json)
    } else {
      game
    }  
    
  
  override def save(game: BoardInterface): Unit = 
    import java.io._
    import scala.xml._
    //get gameMode as String representation
    val gameMode = Map((12, 4) -> "easy", (10, 4) -> "medium", (10, 5) -> "hard", (8, 5) -> "extrem")
    //get timestamp, path and create filename
    val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))
    val path = "src/main/savegames/"
    val filename = path + "game_" + gameMode.get((game.field.rows, game.field.cols)).get + "_" + timestamp + ".json"
    //write to game file
    val pw = new PrintWriter(new File(filename))
    pw.write(gameToJson(game).toString())
    pw.close()

  def cellToJson(cell: Object, x: Int, y: Int) = 
    val cellJson = Json.obj(
      "x" -> x,
      "y" -> y,
      "value" -> cell.toString()
    )
    cellJson

  def vectorToJson(vector: Vector[Object], row: Int) =
    Json.obj(
      "row" -> row,
      "cells" -> {
        //for loop with index
        for (i <- vector.indices) yield cellToJson(vector(i), row, i)
      }
    )


  def gameToJson(game: BoardInterface) =
    Json.obj(
      "matrix" -> {
        for (row <- game.field.matrix.m.indices) 
          yield vectorToJson(game.field.matrix.m(row), row)
      },
      "hmatrix" -> {
        for (row <- game.field.hmatrix.m.indices) 
          yield vectorToJson(game.field.hmatrix.m(row), row)
      },
      "turn" -> game.currentTurn,
      "code" -> vectorToJson(game.code.code.asInstanceOf[Vector[Object]], 0),
    )
  }
