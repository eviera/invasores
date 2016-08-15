package net.eviera.invasores.state

import net.eviera.invasores.helper.Const
import net.eviera.invasores.helper.Helper.drawStringCentered
import org.newdawn.slick.*
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.state.transition.FadeInTransition
import org.newdawn.slick.util.ResourceLoader
import java.awt.Font


class PauseState : BasicGameState() {

    lateinit var pauseLogoBackground: Image
    lateinit var fontComputer24: TrueTypeFont
    lateinit var fontComputer32: TrueTypeFont
    var blinkVisible = true
    var blinkTime = 0

    override fun init(gc: GameContainer?, game: StateBasedGame?) {
        pauseLogoBackground = Image("/resources/images/pause_logo_background.png")

        fontComputer24 = TrueTypeFont(Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("/resources/fonts/Computerfont.ttf")).deriveFont(Const.FONT_SIZE_24), false)
        fontComputer32 = TrueTypeFont(Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("/resources/fonts/Computerfont.ttf")).deriveFont(Const.FONT_SIZE_32), false)
    }

    override fun update(gc: GameContainer?, game: StateBasedGame?, delta: Int) {
        if (gc == null || game == null) throw RuntimeException("Error de update")
        val input = gc.input

        blinkTime += delta
        if (blinkTime > 400) {
            blinkVisible = !blinkVisible
            blinkTime = 0
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.input.clearKeyPressedRecord();
            game.enterState(Const.STATES.GAME.ordinal, null, FadeInTransition(Color.black, Const.PAUSE_TRANSITION_SPEED))
        }

        if (input.isKeyPressed(Input.KEY_Q)) {
            gc.exit()
        }

    }

    override fun render(gc: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        if (gc == null || g == null) throw RuntimeException("Error de inicializacion")

        //fondo
        g.drawImage(Background.img, 0f, 0f)
        g.color = Color.black
        g.fillRect(Const.GAME_WIDTH * 0.125f, Const.GAME_HEIGHT * 0.125f, Const.GAME_WIDTH * 0.75f, Const.GAME_HEIGHT * 0.75f)

        //recuadro
        g.color = Color.white
        g.lineWidth = 2f
        g.drawRect(Const.GAME_WIDTH * 0.125f, Const.GAME_HEIGHT * 0.125f, Const.GAME_WIDTH * 0.75f, Const.GAME_HEIGHT * 0.75f)
        g.resetLineWidth()

        //logo
        g.drawImage(pauseLogoBackground, Const.GAME_WIDTH / 2f - pauseLogoBackground.width / 2f, Const.GAME_HEIGHT * 0.125f + 20f)

        //textos
        fontComputer32.drawStringCentered(Const.GAME_HEIGHT * 0.125f + 150f, Const.GAME_WIDTH, "- P A U S E D -", if (blinkVisible) Color.white else Color.black)
        fontComputer24.drawStringCentered(Const.GAME_HEIGHT * 0.125f + 250f, Const.GAME_WIDTH, "press Q to exit")

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