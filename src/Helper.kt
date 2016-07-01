import Const.SP_SIZE
import Const.ALIEN_START_X
import Const.ALIEN_START_Y
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

object Helper {

    fun getRandomAnimationInterval() = Random().nextInt(300) + 100

    fun getAlienColPos(colDisp: Float, c: Int) = ALIEN_START_X * colDisp + c * SP_SIZE * 2.5f
    fun getAlienRowPos(f: Int) = ALIEN_START_Y + SP_SIZE * f * 1.3f

    fun convertTileCoordToPixelCoord(tileCoord: Int) = tileCoord * Const.SP_SIZE

    fun convertPixelCoordToTileCoord(pixelCoord: Float) = BigDecimal((pixelCoord / Const.SP_SIZE).toDouble()).setScale(1, RoundingMode.DOWN).toInt()


}