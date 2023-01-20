package de.htwg.se.Chess

import com.google.inject.AbstractModule
import com.google.inject.TypeLiteral

import de.htwg.se.Chess.controller.ControllerInterface
import de.htwg.se.Chess.controller.controllerComponent.Controller
import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.model.FileIOComponent.*

//switch between those two, to switch from xml file interpreter/loader to json file interpreter/loader
//import model.FileIOComponent.FileIOjsonImpl.FileIO
import model.FileIOComponent.FileIOxmlImpl.FileIO

class ChessModule extends AbstractModule {
    override def configure(): Unit = {
        bind(classOf[ControllerInterface]).toInstance(new Controller(field = Board(), fileIO = FileIO()))

        //switch between those two, to switch from xml file interpreter/loader to json file interpreter/loader
        bind[FileIOInterface](new TypeLiteral[FileIOInterface] {}).to(classOf[FileIOxmlImpl.FileIO])
        //bind[FileIOInterface](new TypeLiteral[FileIOInterface] {}).to(classOf[FileIOjsonImpl.FileIO])
    }
}