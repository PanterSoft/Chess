package de.htwg.se.Chess.model.FileIOComponent.FileIOxmlImpl

import de.htwg.se.Chess.model.FileIOComponent.FileIOInterface
import de.htwg.se.Chess.model.*
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions.*

import play.api.libs.json.*

import scala.io.Source

import java.io._
import scala.xml._

class FileIO extends FileIOInterface {

  override def load(game: BoardInterface): BoardInterface = 
    import java.io._
    import scala.xml._

    val source = scala.io.Source.fromFile("game.xml")
    val xml = XML.loadString(source.mkString)
    source.close()

    return game


  override def save(game: BoardInterface): Unit =
    import java.io._
    import scala.xml._
    val pw = new PrintWriter(new File("game.xml"))
    pw.write(gameToXml(game).toString())
    pw.close()

  def gameToXml(game: BoardInterface) =
    <game>
      <rows>
        {game.field.matrix.rows}
      </rows>
      <cols>
        {game.field.matrix.cols}
      </cols>
      {matrixToXml(game.field.matrix.asInstanceOf[Matrix[Object]])}
      {hmatrixToXml(game.field.hmatrix.asInstanceOf[Matrix[Object]])}
      <turns>
        {game.getCurrentTurn()}
      </turns>
      <code>
        {game.getCode().code}
      </code>
    </game>
}