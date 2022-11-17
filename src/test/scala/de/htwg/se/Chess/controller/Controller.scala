package de.htwg.se.Chess.controller

import de.htwg.se.Chess.model.Board
import de.htwg.se.Chess.model.History
import de.htwg.se.Chess.util.Observer

import scala.language.reflectiveCalls
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ControllerSpec extends AnyWordSpec {

  "A Controller" when {
    "observed by an Observer" should {
      val controller = Controller(new Board(), new History())
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }/*
      controller.move_c(pos_now, pos_new)
      "nothify its observer after move_c" in {
        observer.update should be()
      }*/
    }
  }
}