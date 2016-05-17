import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class KeyInput(val handler: Handler) : KeyAdapter() {



    // http://gamedev.stackexchange.com/questions/56017/java-best-implementation-keylistener-for-games


    override fun keyPressed(e: KeyEvent?) {
        val key = e?.keyCode
        if (key == KeyEvent.VK_RIGHT) handler.keyRight = true
        if (key == KeyEvent.VK_LEFT) handler.keyLeft = true

    }

    override fun keyReleased(e: KeyEvent?) {
        val key = e?.keyCode
        if (key == KeyEvent.VK_RIGHT) handler.keyRight = false
        if (key == KeyEvent.VK_LEFT) handler.keyLeft = false
    }
}