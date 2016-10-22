package net.eviera.invasores.state

import net.eviera.invasores.helper.Const
import net.eviera.invasores.helper.Helper
import net.eviera.invasores.manager.HighScoreManager
import org.newdawn.slick.*
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.state.transition.FadeInTransition

class StartUpState : BasicGameState() {

    lateinit var backimg: Image

    override fun init(gc: GameContainer?, game: StateBasedGame?) {
        backimg = Image("/resources/images/startup_background.png")

        //Inicializo el score
        HighScoreManager.init()

    }

    override fun update(gc: GameContainer?, game: StateBasedGame?, delta: Int) {
        if (gc == null || game == null) throw RuntimeException("Error de update")
        val input = gc.input

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            gc.input.clearKeyPressedRecord()
            game.enterState(Const.STATES.GAME.ordinal, null, FadeInTransition(Color.black))
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit()
        }

        HighScoreManager.update(gc, game, delta)

    }

    override fun render(gc: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        if (gc == null || g == null || game == null) throw RuntimeException("Error de inicializacion")
        g.drawImage(backimg, 0f, 0f)

        HighScoreManager.render(gc, game, g, false)
    }

    override fun getID(): Int {
        return Const.STATES.STARTUP.ordinal
    }
}