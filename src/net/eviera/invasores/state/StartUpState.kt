package net.eviera.invasores.state

import net.eviera.invasores.helper.Const
import org.newdawn.slick.Color
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.state.transition.FadeInTransition
import org.newdawn.slick.state.transition.FadeOutTransition
import org.newdawn.slick.tests.states.TestState3

class StartUpState : BasicGameState() {
    override fun init(gc: GameContainer?, game: StateBasedGame?) {

    }

    override fun update(gc: GameContainer?, game: StateBasedGame?, delta: Int) {
        if (gc == null || game == null) throw RuntimeException("Error de update")
        val input = gc.input

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            game.enterState(Const.STATES.GAME.ordinal, null, FadeInTransition(Color.black))
        }
    }

    override fun render(gc: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        if (gc == null || g == null) throw RuntimeException("Error de inicializacion")
        g.color = Color.white
        g.drawString("Press space", 100f, 100f)
    }

    override fun getID(): Int {
        return Const.STATES.STARTUP.ordinal
    }
}