package net.eviera.invasores.event

class GameEvent(val score: Int, var over: Boolean = false) : Event(Type.GAME) {

}