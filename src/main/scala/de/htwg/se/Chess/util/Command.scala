package de.htwg.se.Chess.util

trait Command {
  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit
}