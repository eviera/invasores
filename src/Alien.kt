import org.newdawn.slick.Animation
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.geom.Rectangle

class Alien (x: Float, y: Float) : Rectangle(x, y, Const.SP_SIZE, Const.SP_SIZE) {

    var nave : Animation? = null

    fun init(nave: Animation) {
        this.nave = nave
    }

    fun render(gc: GameContainer, g: Graphics) {
        (nave as Animation).draw(x, y)
    }



}