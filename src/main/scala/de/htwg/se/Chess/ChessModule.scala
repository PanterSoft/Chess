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
//import model.FileIOComponent.FileIOjsonImpl.FileIO
import model.FileIOComponent.FileIOxmlImpl.FileIO

class ChessModule extends AbstractModule {
    override def configure(): Unit = {
        bind(classOf[ControllerInterface]).toInstance(new Controller(field = Board(), fileIO = FileIO()))
        bind[FileIOInterface](new TypeLiteral[FileIOInterface] {}).to(classOf[FileIOxmlImpl.FileIO])
        //bind[FileIOInterface](new TypeLiteral[FileIOInterface] {}).to(classOf[FileIOjsonImpl.FileIO])
    }
}