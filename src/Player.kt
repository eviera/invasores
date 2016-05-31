import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.geom.Rectangle

class Player : Rectangle(Const.PLAYER_START_X, Const.PLAYER_START_Y, Const.SP_SIZE, Const.SP_SIZE) {

    var nave :Image? = null

    fun init(image: Image) {
        nave = image
    }

    fun render(gc: GameContainer, g: Graphics) {
        g.drawImage(nave, x, y)
    }


}