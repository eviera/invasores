package net.eviera.invasores.event

open class Event(var type: Type) {

    enum class Type {
        GAME,
        BRICK,
        ALIEN,
        PLAYER,
    }

}