package de.htwg.se.Chess.aview

import de.htwg.se.Chess.aview.SelectionSystem

trait State {
  def changeState(): Unit
  //def displayState(): Unit
}


class First(selectionsystem: SelectionSystem, receiver: Receiver) extends State {
    def changeState() =
        receiver.off()
        selectionsystem.previousState = this;
        selectionsystem.currentState=selectionsystem.second
    //def displayState() =
        //receiver.on()
}

class Third(selectionsystem: SelectionSystem, receiver: Receiver) extends State {
    def changeState() =
        receiver.off()
        selectionsystem.previousState = this;
        selectionsystem.currentState = selectionsystem.first
    //def displayState() =
        //receiver.on()
}

class Second(selectionsystem: SelectionSystem, receiver: Receiver) extends State {
    def changeState() =
        receiver.off()
        selectionsystem.previousState = this;
        selectionsystem.currentState = selectionsystem.third
    //def displayState() =
        //receiver.on()
}