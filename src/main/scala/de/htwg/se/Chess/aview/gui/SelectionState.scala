package de.htwg.se.Chess.aview

trait State {
  def changeState(): Unit
  //def displayState(): Unit
}


class Green(trafficSystem: TrafficSystem, receiver: Receiver) extends State {
    def changeState() =
        receiver.off()
        trafficSystem.previousState = this;
        trafficSystem.currentState=trafficSystem.yellow
    //def displayState() =
        //receiver.on()
}

class Red(trafficSystem: TrafficSystem, receiver: Receiver) extends State {
    def changeState() =
        receiver.off()
        trafficSystem.previousState = this;
        trafficSystem.currentState = trafficSystem.green
    //def displayState() =
        //receiver.on()
}

class Yellow(trafficSystem: TrafficSystem, receiver: Receiver) extends State {
    def changeState() =
        receiver.off()
        trafficSystem.previousState = this;
        trafficSystem.currentState = trafficSystem.red
    //def displayState() =
        //receiver.on()
}