import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.geom.Rectangle

class Shoot (x: Float, y: Float, val sprite: Image) : CollisionableRectangle(x, y, Const.SP_SIZE, Const.SP_SIZE) {

    var alive: Boolean = true

    fun init() {
        CollisionManager.addShoot(this)
    }

    fun update(gc: GameContainer, delta: Int) {
        val displacement = Const.PLAYER_SHOOT_SPEED * delta
        val yDest = y - displacement
        if (yDest < 0) {
            remove()
        }
        y = yDest

    }

    fun render(gc: GameContainer, g: Graphics) {
        if (alive) {
            sprite.draw(x, y)
        }
    }

    fun remove() {
        alive = false
        CollisionManager.removeShoot(this)
    }

    override fun collisionWith() {

    }

}