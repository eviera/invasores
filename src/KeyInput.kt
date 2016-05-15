import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class KeyInput(val handler: Handler) : KeyAdapter() {

    override fun keyPressed(e: KeyEvent?) {
        val key = e?.keyCode
        if (key == KeyEvent.VK_RIGHT) handler.player.velX = 5
        if (key == KeyEvent.VK_LEFT) handler.player.velX = -5

    }

    override fun keyReleased(e: KeyEvent?) {
        val key = e?.keyCode
        if (key == KeyEvent.VK_RIGHT) handler.player.velX = 0
        if (key == KeyEvent.VK_LEFT) handler.player.velX = 0
    }
}