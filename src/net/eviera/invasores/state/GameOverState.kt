package net.eviera.invasores.state

import net.eviera.invasores.helper.Const
import net.eviera.invasores.helper.Helper
import net.eviera.invasores.manager.HighScoreManager
import org.newdawn.slick.*
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame

class GameOverState : BasicGameState() {

    lateinit var backimg: Image
    lateinit var fontComputer24: TrueTypeFont
    //Determina si estoy mostrando un highscore o no
    var isInHighScoreState = false

    override fun init(gc: GameContainer?, game: StateBasedGame?) {
        backimg = Image("/resources/images/gameover_background.png")
        fontComputer24 = Helper.getComputerFont(Const.FONT_SIZE_24)

        //Busco si tengo un nuevo highscore, y de ser asi, cambio el estado de highScoreState a true
        isInHighScoreState = HighScoreManager.isNewScore(State.score)

    }

    override fun update(gc: GameContainer?, game: StateBasedGame?, delta: Int) {
        if (gc == null || game == null) throw RuntimeException("Error de update")
        val input = gc.input

        if (input.isKeyPressed(Input.KEY_Q)) {
            gc.exit()
        }

    }

    override fun render(gc: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        if (gc == null || g == null) throw RuntimeException("Error de inicializacion")
        g.drawImage(backimg, 0f, 0f)

        HighScoreManager.render(gc, game, g, isInHighScoreState)

    }

    override fun getID(): Int {
        return Const.STATES.GAMEOVER.ordinal
    }

    companion object State {
        var score = 0
    }
}