package net.eviera.invasores.state

import net.eviera.invasores.helper.Const
import net.eviera.invasores.helper.Helper
import org.newdawn.slick.*
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame

class GameOverState : BasicGameState() {

    lateinit var backimg: Image
    lateinit var fontComputer24: TrueTypeFont

    override fun init(gc: GameContainer?, game: StateBasedGame?) {
        backimg = Image("/resources/images/gameover_background.png")
        fontComputer24 = Helper.getComputerFont(Const.FONT_SIZE_24)

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
        g.color = Color.white


    }

    override fun getID(): Int {
        return Const.STATES.GAMEOVER.ordinal
    }
}