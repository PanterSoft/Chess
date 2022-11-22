package de.htwg.se.Chess.model

class PlayerSystem {
    var intialReciever:Reciever=new PlayerOneReciever()
    var player_one:State=new PlayerOne(this,intialReciever)
    var player_two:State=new PlayerTwo(this,new PlayerTwoReciever)
    var currentState:State=new PlayerOne(this,intialReciever)
    var previousState:State=new PlayerTwo(this,intialReciever)

    def changeState(): Unit = currentState.changeState()

    def displayState(): Unit = currentState.displayState()
}

trait State {
  def changeState(): Unit
  def displayState(): Unit
}

class PlayerOne(playersystem: PlayerSystem, reciever: Reciever) extends State {
    def changeState() =
        //reciever.off()
        playersystem.previousState=this;
        playersystem.currentState=playersystem.player_two
    def displayState() =
        reciever.on()
}

class PlayerTwo(playersystem: PlayerSystem, reciever: Reciever) extends State {
    def changeState() =
        //reciever.off()
        playersystem.previousState = this;
        playersystem.currentState = playersystem.player_one
    def displayState() =
        reciever.on()
}

val playersystem:PlayerSystem = new PlayerSystem()

def change_player(): Unit =
    playersystem.changeState()

def at_turn(): Int =
    if(playersystem.currentState.isInstanceOf[PlayerOne])
        1
    else
        2



/* implement in TUI

val playersystem:PlayerSystem = new PlayerSystem()

    println(at_turn())
    change_player()
    println(at_turn())
    change_player()
    println(at_turn())

*/