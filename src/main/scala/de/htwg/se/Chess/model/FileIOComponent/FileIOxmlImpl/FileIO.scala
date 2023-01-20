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
import scala.collection.immutable.VectorMap
import scala.collection.mutable.Queue


class FileIO extends FileIOInterface {

  override def load(): Board =
    val source = scala.xml.XML.loadFile("field.xml")
    val xml = XML.loadString(source.mkString)
    var field: VectorMap[String, String] = VectorMap[String, String]()

    for (index <- 0 until 8 * 8) {
      val pos = (xml \ "pos").toString()
      val figure = (xml \ "figure").toString()
      field = field.updated(pos, figure)
    }
    Board(field)

  override def save(game: BoardInterface): Unit =
    import java.io._
    import scala.xml._
    val pw = new PrintWriter(new File("board.xml"))
    pw.write(vectorMapToXml(game).toString())
    pw.close()

  def vectorMapToXml(board_object: BoardInterface) =
    val tmp:VectorMap[String, String] = board_object.board
    <game>
      <field_entrys>
        {for ((pos, figure) <- tmp) {
            <pos>
              {pos}
            </pos>
            <figure>
              {figure}
            </figure>
          }
        }
      </field_entrys>
    </game>
}