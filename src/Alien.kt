import org.newdawn.slick.Animation
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics

class Alien (x: Float, y: Float, var alienXDisplacement: Float, var alienYDisplacement: Float) : CollisionableRectangle(x, y, Const.SP_SIZE, Const.SP_SIZE){

    lateinit var sprite: Animation
    var alive: Boolean = true

    fun init(sprite: Animation) {
        this.sprite = sprite
        CollisionManager.addAlien(this)
    }

    fun update(gc: GameContainer, delta: Int, alienXDisplacement: Float, alienYDisplacement: Float) {
        if (alive) {
            this.alienXDisplacement = alienXDisplacement
            this.alienYDisplacement = alienYDisplacement
        }
    }

    fun render(gc: GameContainer, g: Graphics) {
        if (alive) {
            x += alienXDisplacement
            y += alienYDisplacement
            sprite.draw(x, y)
        }
    }

    fun remove() {
        CollisionManager.removeAlien(this)
        alive = false
    }

    override fun collisionWith(collisioned: CollisionableRectangle) {
        remove()
    }

}
