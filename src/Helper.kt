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

    fun convertXTileCoordToPixelCoord(tileCoord: Int) = tileCoord * Const.GAME_TILES_WIDTH * 1f
    fun convertYTileCoordToPixelCoord(tileCoord: Int) = tileCoord * Const.GAME_TILES_HEIGHT * 1f

    fun convertXPixelCoordToTileCoord(x: Float) = BigDecimal((x / Const.GAME_TILES_WIDTH).toDouble()).setScale(1, RoundingMode.DOWN).toInt()
    fun convertYPixelCoordToTileCoord(y: Float) = BigDecimal((y / Const.GAME_TILES_HEIGHT).toDouble()).setScale(1, RoundingMode.DOWN).toInt()


}