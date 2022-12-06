package de.htwg.se.Chess.aview

class TrafficSystem {
    var intialReceiver:Receiver=new GreenReceiver()
    var green:State=new Green(this,intialReceiver)
    var red:State=new Red(this,new RedReceiver)
    var yellow:State=new Yellow(this,new YellowReceiver)
    var currentState:State=new Green(this,intialReceiver)
    var previousState:State=new Green(this,intialReceiver)

    def changeState(): Unit = currentState.changeState()

    //def displayState(): Unit = currentState.displayState()
}