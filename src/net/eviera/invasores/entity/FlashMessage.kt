package net.eviera.invasores.entity

import net.eviera.invasores.helper.Const
import org.newdawn.slick.Color
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.TrueTypeFont

class FlashMessage() {

    lateinit var fontComputer24: TrueTypeFont
    lateinit var message: String
    var timeout = 0
    var show = false

    fun init(fontComputer24: TrueTypeFont) {
        this.fontComputer24 = fontComputer24
    }

    fun show(message: String) {
        this.message = message
        show = true
        timeout = Const.FLASH_TIMEOUT
    }

    fun update(gc: GameContainer, delta: Int) {
        timeout -= delta
        if (timeout <= 0) {
            show = false
        }
    }

    fun render(gc: GameContainer, g: Graphics) {
        if (show) {
            g.color = Color.white
            val width = fontComputer24.getWidth(message)
            fontComputer24.drawString(Const.GAME_WIDTH / 2f - width / 2f, Const.GAME_FLOOR + 2, message)
        }
    }

}