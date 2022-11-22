package de.htwg.se.Chess.util

trait Command {
  def doMove:Unit
  def undoMove:Unit
  def redoMove:Unit
}