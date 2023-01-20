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

  override def load(game: BoardInterface): BoardInterface =
    import java.io._
    import scala.xml._

    val source = scala.io.Source.fromFile("game.xml")
    val xml = XML.loadString(source.mkString)
    source.close()
    //@todo add xml to boardInterface
    return game


  override def save(game: BoardInterface): Unit =
    import java.io._
    import scala.xml._
    val pw = new PrintWriter(new File("game.xml"))
    pw.write(vectorMapToXml(game).toString())
    pw.close()

  def match_pattern(option: Option[Int]) = option match {
    case Some(s) => (s)
    case None => (0)
  }

  def match_pattern(option: Option[String]) = option match {
      case Some(s) => (s)
      case None => ("Invalid")
  }

  def get_pos() : Queue[String] =
    val x_map = Map(1->"A", 2->"B", 3->"C", 4->"D", 5->"E", 6->"F", 7->"G", 8->"H")

    var queue = Queue[String]()

    for (i <- 0 to 8) yield
      for (j <- 0 to 8) yield
        val tmp_1 = match_pattern(x_map.get(i))
        val tmp_2 = j
        queue += tmp_1.toString + tmp_2.toString()
    queue

  def vectorMapToXml(game: BoardInterface) =
    <game>
      <code>
        <pos>
          {for(i <- get_pos()) yield (get_pos().dequeue())}
        </pos>
        <name>
          {for(i <- get_pos()) yield (get_pos())}
        </name>
      </code>
    </game>
}