package net.eviera.invasores.helper

import net.eviera.invasores.helper.Const.ALIEN_GAP_X
import net.eviera.invasores.helper.Const.ALIEN_GAP_Y
import net.eviera.invasores.helper.Const.ALIEN_START_X
import net.eviera.invasores.helper.Const.ALIEN_START_Y
import net.eviera.invasores.helper.Const.SP_SIZE
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

object Helper {

    fun getRandomAnimationInterval() = Random().nextInt(300) + 100

    fun getAlienColPos(colDisp: Float, c: Int) = ALIEN_START_X * colDisp + c * SP_SIZE * ALIEN_GAP_X
    fun getAlienRowPos(f: Int) = ALIEN_START_Y + SP_SIZE * f * ALIEN_GAP_Y

    fun convertTileCoordToPixelCoord(tileCoord: Int) = tileCoord * Const.SP_SIZE

    fun convertPixelCoordToTileCoord(pixelCoord: Float) = BigDecimal((pixelCoord / SP_SIZE).toDouble()).setScale(1, RoundingMode.DOWN).toInt()


    var PAUSE_DEV_MODE_ONLY = false
    fun DO_PAUSE_DEV_MODE_ONLY() {
        PAUSE_DEV_MODE_ONLY = true
    }

}