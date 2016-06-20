import Const.SP_SIZE
import Const.ALIEN_START_X
import java.util.*

object Helper {

    fun getRandomAnimationInterval() = Random().nextInt(300) + 100

    fun getAlienColPos(colDisp: Float, c: Int) = ALIEN_START_X * colDisp + c * SP_SIZE * 1.7f
    fun getAlienRowPos(f: Int) = SP_SIZE / 3f + SP_SIZE * f * 1.3f
}