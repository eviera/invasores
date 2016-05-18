import org.newdawn.slick.*

class Invasores : BasicGame("Invasores") {

    var nave :Image? = null
    var x = 200f;

    override fun init(gc: GameContainer?) {
        nave = Image("/resources/images/nave_64.png")
    }

    override fun update(gc: GameContainer?, delta: Int) {
        val input = gc?.input
        if (input?.isKeyDown(Input.KEY_LEFT)!!) { x-- }
        if (input?.isKeyDown(Input.KEY_RIGHT)!!) { x++ }

    }

    override fun render(gc: GameContainer?, g: Graphics?) {
        g?.drawImage(nave, x, 200f)
    }

}

fun main(args: Array<String>) {
    val appgc = AppGameContainer(Invasores())
    appgc.setDisplayMode(800, 600, false)
    appgc.start()
}