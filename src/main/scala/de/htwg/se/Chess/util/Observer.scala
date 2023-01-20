package de.htwg.se.Chess.util

trait Observer:
  def update: Unit

class Observable:
  var subscribers: Vector[Observer] = Vector()
  def add(s: Observer) = subscribers = subscribers :+ s
  def notifyObservers = subscribers.foreach(o => o.update)