package net.eviera.invasores.helper

import net.eviera.invasores.helper.Const.ALIEN_GAP_X
import net.eviera.invasores.helper.Const.ALIEN_GAP_Y
import net.eviera.invasores.helper.Const.ALIEN_START_X
import net.eviera.invasores.helper.Const.ALIEN_START_Y
import net.eviera.invasores.helper.Const.SP_SIZE
import org.lwjgl.opengl.GL11
import org.newdawn.slick.Color
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Image
import org.newdawn.slick.TrueTypeFont
import org.newdawn.slick.opengl.renderer.SGL
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

object Helper {

    fun getRandomAnimationInterval() = Random().nextInt(300) + 100

    fun getRandomXInScreen() = Random().nextInt(Const.GAME_WIDTH - Const.SP_SIZE.toInt()) + Const.SP_SIZE.toInt()

    fun getAlienColPos(colDisp: Float, c: Int) = ALIEN_START_X * colDisp + c * SP_SIZE * ALIEN_GAP_X
    fun getAlienRowPos(f: Int) = ALIEN_START_Y + SP_SIZE * f * ALIEN_GAP_Y

    fun convertTileCoordToPixelCoord(tileCoord: Int) = tileCoord * Const.SP_SIZE

    fun convertPixelCoordToTileCoord(pixelCoord: Float) = BigDecimal((pixelCoord / SP_SIZE).toDouble()).setScale(1, RoundingMode.DOWN).toInt()

    fun grayImage(gc: GameContainer): Image {
        val background = Image(Const.GAME_WIDTH, Const.GAME_HEIGHT)
        gc.graphics.copyArea(background, 0, 0)
        GL11.glPixelTransferf(GL11.GL_RED_SCALE, 0.30f);
        GL11.glPixelTransferf(GL11.GL_GREEN_SCALE, 0.59f);
        GL11.glPixelTransferf(GL11.GL_BLUE_SCALE, 0.11f);
        background.bind();
        GL11.glCopyTexImage2D(SGL.GL_TEXTURE_2D, 0, GL11.GL_LUMINANCE16, 0, gc.height - background.height, background.texture.textureWidth,
                background.texture.textureHeight, 0);
        background.ensureInverted();

        return background
    }

    fun TrueTypeFont.drawStringCentered(y: Float, width: Int, whatChars: String) {
        drawStringCentered(y, width, whatChars, Color.white)
    }

    fun TrueTypeFont.drawStringCentered(y: Float, width: Int, whatChars: String, color: Color) {
        val centerX = width / 2f - getWidth(whatChars) / 2
        drawString(centerX, y, whatChars, color)
    }

    var PAUSE_DEV_MODE_ONLY = false
    fun DO_PAUSE_DEV_MODE_ONLY() {
        PAUSE_DEV_MODE_ONLY = true
    }

}