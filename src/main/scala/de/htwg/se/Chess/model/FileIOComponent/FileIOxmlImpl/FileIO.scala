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
    val source = scala.xml.XML.loadFile("board.xml")
    val xml = XML.loadString(source.mkString)
    var field: VectorMap[String, String] = VectorMap[String, String]()

    for (index <- 0 until 8 * 8) {
      val pos = ((xml \\ "pos")(index)).text.trim()
      val figure = ((xml \\ "figure")(index)).text.trim()

      if (figure == "__") {
        val figure_r = "  "
        field = field.updated(pos, figure_r)
      } else {
        field = field.updated(pos, figure)
      }

    }
    print(field)
    Board(field)

  override def save(game: BoardInterface): Unit =
    import java.io._
    import scala.xml._
    val pw = new PrintWriter(new File("board.xml"))
    pw.write(vectorMapToXml(game).toString())
    pw.close()

  def vectorMapToXml(board_object: BoardInterface) =
    <game>
      {for ((pos, figure) <- board_object.board) yield {
        <pos>
          {pos}
        </pos>
        <figure>
          {
            if (figure == "  ") {
              "__"
            } else {
              figure
            }
          }
        </figure>
        }
      }
    </game>
}