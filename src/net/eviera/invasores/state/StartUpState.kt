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
    lateinit var fontComputer24: TrueTypeFont

    override fun init(gc: GameContainer?, game: StateBasedGame?) {
        backimg = Image("/resources/images/startup_background.png")

        fontComputer24 = Helper.getComputerFont(Const.FONT_SIZE_24)

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

    }

    override fun render(gc: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        if (gc == null || g == null) throw RuntimeException("Error de inicializacion")
        g.drawImage(backimg, 0f, 0f)
        g.color = Color.white
        var i = 0
        for ((name, score) in HighScoreManager.scores) {
            fontComputer24.drawString(Const.GAME_WIDTH / 2f - 100f, Const.GAME_HEIGHT / 2f + Const.FONT_SIZE_24 * (2 + i), name)
            fontComputer24.drawString(Const.GAME_WIDTH / 2f + 80f, Const.GAME_HEIGHT / 2f + Const.FONT_SIZE_24 * (2 + i), score.toString())
            i++
        }
    }

    override fun getID(): Int {
        return Const.STATES.STARTUP.ordinal
    }
}