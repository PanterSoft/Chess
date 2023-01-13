package de.htwg.se.Chess

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import com.google.inject.TypeLiteral

import de.htwg.se.Chess.controller.ControllerInterface
import de.htwg.se.Chess.controller.controllerComponent.Controller
import de.htwg.se.Chess.model.BoardInterface
import de.htwg.se.Chess.model.Board
import javax.sound.midi.ControllerEventListener

class ChessModule extends AbstractModule {
    override def configure(): Unit = {
        bind(classOf[ControllerInterface]).toInstance(new Controller(field = Board()))
    }
}