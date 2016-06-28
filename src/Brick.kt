import org.newdawn.slick.Animation
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image

class Brick (x: Float, y: Float) : CollisionableRectangle(x, y, Const.SP_SIZE, Const.SP_SIZE){

    enum class STATUS {
        ENTERO,
        PARTIDO,
        ROTO
    }

    lateinit var sprites: Array<Image>
    var alive: Boolean = true
    var status = STATUS.ENTERO

    fun init(sprites: Array<Image>) {
        this.sprites = sprites
        CollisionManager.addBrick(this)
    }

    fun update(gc: GameContainer, delta: Int, alienXDisplacement: Float, alienYDisplacement: Float) {
        if (alive) {

        }
    }

    fun render(gc: GameContainer, g: Graphics) {
        if (alive) {
            sprites[status.ordinal].draw(x, y)
        }
    }

    fun remove() {
        CollisionManager.removeBrick(this)
        alive = false
    }

    override fun collisionWith(collisioned: CollisionableRectangle) {
        remove()
    }

}
