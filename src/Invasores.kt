import org.newdawn.slick.AppGameContainer
import org.newdawn.slick.BasicGame
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics

class Invasores : BasicGame("Invasores") {

    override fun init(gc: GameContainer?) {
    }

    override fun update(gc: GameContainer?, delta: Int) {
    }

    override fun render(gc: GameContainer?, g: Graphics?) {
    }

}

fun main(args: Array<String>) {
    val appgc = AppGameContainer(Invasores())
    appgc.setDisplayMode(640, 480, false)
    appgc.start()
}