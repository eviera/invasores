import java.awt.Color
import java.awt.Graphics
import java.util.*

class Player(x: Int, y: Int) : GameObject(x, y, ID.Player) {

    val r = Random()

    init {
        //velX = r.nextInt(5) + 1
        //velY = r.nextInt(5) + 1
    }

    override fun tick() {
        x += velX
        y += velY
    }

    override fun render(g: Graphics) {
        g.color = Color.white
        g.fillRect(x, y, 32, 32)
    }
}