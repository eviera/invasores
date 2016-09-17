package net.eviera.invasores.manager

import net.eviera.invasores.event.Event
import net.eviera.invasores.event.Listener
import java.util.*

object EventManager {

    val listeners = HashMap<Event.Type, LinkedHashSet<Listener>>()   //Uso un HashSet para evitar duplicados
    val eventQueue = LinkedList<Event>()

    fun publish(event: Event) {
        eventQueue.push(event)
    }


    fun addGameListener(listener: Listener) {
        addListener(Event.Type.GAME, listener)
    }

    fun addBrickListener(listener: Listener) {
        addListener(Event.Type.BRICK, listener)
    }

    fun addAlienListener(listener: Listener) {
        addListener(Event.Type.ALIEN, listener)
    }


    fun addPlayerListener(listener: Listener) {
        addListener(Event.Type.PLAYER, listener)
    }



    private fun addListener(type: Event.Type, listener: Listener) {
        var listenersByType = listeners[type]
        if (listenersByType == null)  {
            listenersByType = LinkedHashSet<Listener>()
        }
        listenersByType.add(listener)
        listeners[type] = listenersByType

    }

    fun dispatchEvents() {
        while (!eventQueue.isEmpty()) {
            val e = eventQueue.pop()
            listeners[e.type]?.forEach { l -> l.fired(e)}
        }
    }

}