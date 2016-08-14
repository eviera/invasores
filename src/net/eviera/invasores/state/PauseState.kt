package net.eviera.invasores.state

import net.eviera.invasores.helper.Const
import org.newdawn.slick.*
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.state.transition.FadeInTransition

class PauseState : BasicGameState() {

    override fun init(gc: GameContainer?, game: StateBasedGame?) {

    }

    override fun update(gc: GameContainer?, game: StateBasedGame?, delta: Int) {
        if (gc == null || game == null) throw RuntimeException("Error de update")
        val input = gc.input

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.input.clearKeyPressedRecord();
            game.enterState(Const.STATES.GAME.ordinal, null, FadeInTransition(Color.black))
        }

        if (input.isKeyPressed(Input.KEY_Q)) {
            gc.exit()
        }

    }

    override fun render(gc: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        if (gc == null || g == null) throw RuntimeException("Error de inicializacion")
        g.drawImage(Background.img, 0f, 0f)

        g.color = Color.white
        g.drawString("caca", 100f, 100f);
    }

    override fun getID(): Int {
        return Const.STATES.PAUSE.ordinal
    }

    companion object Background {
        lateinit var img: Image;
    }

}