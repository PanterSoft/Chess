package de.htwg.se.Chess

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import com.google.inject.TypeLiteral

import de.htwg.se.Chess.controller.ControllerInterface
import de.htwg.se.Chess.controller.controllerComponent.Controller
import de.htwg.se.Chess.model.BoardInterface
import de.htwg.se.Chess.model.Board
import javax.sound.midi.ControllerEventListener

import net.codingwell.scalaguice.ScalaModule

import model.FileIOComponent.FileIOInterface
import model.FileIOComponent.*

/*
* Uncoment the line below and in the Imports above to Save and Load
* from XML
*/
//import model.FileIOComponent.FileIOxmlImpl.FileIO

/*
* Uncoment the line below and in the Imports above to Save and Load
* from JSON
*/
import model.FileIOComponent.FileIOjsonImpl.FileIO


class ChessModule extends AbstractModule {
    override def configure(): Unit = {
        bind(classOf[ControllerInterface]).toInstance(new Controller(field = Board(), fileIO = FileIO()))

        /*
         * Uncoment the line below and in the Imports above to Save and Load
         * from XML
        */

        //bind[FileIOInterface](new TypeLiteral[FileIOInterface] {}).to(classOf[FileIOxmlImpl.FileIO])

        /*
         * Uncoment the line below and in the Imports above to Save and Load
         * from JSON
        */

        bind[FileIOInterface](new TypeLiteral[FileIOInterface] {}).to(classOf[FileIOjsonImpl.FileIO])
    }
}