import org.newdawn.slick.Animation
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.geom.Rectangle

class Alien (x: Float, y: Float, var alienDisplacement: Float) : CollisionableRectangle(x, y, Const.SP_SIZE, Const.SP_SIZE){

    lateinit var sprite: Animation

    fun init(sprite: Animation) {
        this.sprite = sprite
        CollisionManager.addAlien(this)
    }

    fun update(gc: GameContainer, delta: Int, alienDisplacement: Float) {
        this.alienDisplacement = alienDisplacement
    }

    fun render(gc: GameContainer, g: Graphics) {
        x = x + alienDisplacement
        sprite.draw(x , y)
    }

    fun remove() {
        CollisionManager.removeAlien(this)
    }

    override fun collisionWith() {

    }

}
