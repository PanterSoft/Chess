package de.htwg.se.Chess
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import javax.swing.text.PlainView

class ChessSpec extends AnyWordSpec:
    "TUI" should {
        """have a First row as String of form 'Player One: enter your Name:
            Testname1
            Player Two: enter your Name:
            Testname2'""" in {
            playerSetup("Testname1", "Testname2") shouldBe("""Player One: enter your Name:
            Testname1
            Player Two: enter your Name:
            Testname2""" + eol)
        }

        """have 'Game Start: +
          ->'""" in {
            gameLoop() shouldBe("""Game Start
            ->""" + eol)
        }

        "have 'Befehle wie 'K1 auf B5' Bewegt König 1 auf Feld B5.'" in {
            commands("Feldbewegungen") shouldBe("Befehle wie 'K1 auf B5' Bewegt König 1 auf Feld B5." + eol)
        }
    }