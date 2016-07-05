package net.eviera.invasores.manager

import net.eviera.invasores.event.Event
import net.eviera.invasores.event.ScoreEvent
import java.util.*

object EventManager {

    var events = LinkedList<Event>()

    fun  publish(scoreEvent: ScoreEvent) {
        events.push(scoreEvent)
    }


}