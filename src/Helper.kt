import Const.SP_SIZE
import Const.ALIEN_START_X
import Const.ALIEN_START_Y
import java.util.*

object Helper {

    fun getRandomAnimationInterval() = Random().nextInt(300) + 100

    fun getAlienColPos(colDisp: Float, c: Int) = ALIEN_START_X * colDisp + c * SP_SIZE * 2.5f
    fun getAlienRowPos(f: Int) = ALIEN_START_Y + SP_SIZE * f * 1.3f
}