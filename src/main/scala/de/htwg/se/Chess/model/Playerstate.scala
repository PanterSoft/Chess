package de.htwg.se.Chess.model

class PlayerSystem {
    var intialReceiver:Receiver=new PlayerOneReceiver()
    var player_one:State=new PlayerOne(this,intialReceiver)
    var player_two:State=new PlayerTwo(this,new PlayerTwoReceiver)
    var currentState:State=new PlayerOne(this,intialReceiver)
    var previousState:State=new PlayerTwo(this,intialReceiver)

    def changeState(): Unit = currentState.changeState()

    def displayState(): String = currentState.displayState()
}

trait State {
  def changeState(): Unit
  def displayState(): String
}

class PlayerOne(playersystem: PlayerSystem, receiver: Receiver) extends State {
    def changeState() =
        //receiver.off()
        playersystem.previousState=this;
        playersystem.currentState=playersystem.player_two
    def displayState() =
        receiver.on()
}

class PlayerTwo(playersystem: PlayerSystem, receiver: Receiver) extends State {
    def changeState() =
        //receiver.off()
        playersystem.previousState = this;
        playersystem.currentState = playersystem.player_one
    def displayState() =
        receiver.on()
}