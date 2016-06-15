import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.geom.Rectangle

class Player : Rectangle(Const.PLAYER_START_X, Const.PLAYER_START_Y, Const.SP_SIZE, Const.SP_SIZE) {

    var nave :Image? = null
    var shoot: Image? = null
    var isShooting = false

    fun init(nave: Image, shoot: Image) {
        this.nave = nave
        this.shoot = shoot
    }

    fun render(gc: GameContainer, g: Graphics) {
        g.drawImage(nave, x, y)
    }


}