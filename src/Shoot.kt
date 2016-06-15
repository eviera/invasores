import org.newdawn.slick.Animation
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.geom.Rectangle

class Shoot (x: Float, y: Float) : Rectangle(x, y, Const.SP_SIZE, Const.SP_SIZE) {

    var shoot : Animation? = null

    fun init(shoot: Animation) {
        this.shoot = shoot
    }

    fun render(gc: GameContainer, g: Graphics) {
        (shoot as Animation).draw(x, y)
    }



}