package net.eviera.invasores.manager

import net.eviera.invasores.entity.Score
import net.eviera.invasores.helper.Const
import net.eviera.invasores.helper.Helper
import net.eviera.invasores.helper.Helper.drawStringCentered
import org.newdawn.slick.*
import org.newdawn.slick.state.StateBasedGame
import java.util.*

object HighScoreManager {

    lateinit var fontComputer24: TrueTypeFont
    lateinit var fontComputer32: TrueTypeFont
    val scores = ArrayList<Score>(3)
    val savedState = SavedState("invasores.state")
    var colorCycle = Color.white
    var colorCounter = 0
    var i = 0

    fun init() {
        fontComputer24 = Helper.getComputerFont(Const.FONT_SIZE_24)
        fontComputer32 = Helper.getComputerFont(Const.FONT_SIZE_32)

        //Cargo 3 highscores
        savedState.load()
        for (i in 1..3) {
            val highScoreName = savedState.getString("highscore.name.$i", "...")
            val highScoreScore = savedState.getNumber("highscore.value.$i", 0.0)
            scores.add(Score(highScoreName, highScoreScore.toInt()))
        }
    }

    fun update(gc: GameContainer, game: StateBasedGame, delta: Int) {
        //Si hay mucho delta, lo seteo a un minimo de 20 (para cuando se draggea la ventana)
        var correctedDelta = delta
        if (correctedDelta > 20) {
            correctedDelta = 20
        }
        colorCounter += correctedDelta
        if (colorCounter >= Const.COLOR_CYCLE_RATE_MILIS) {
            colorCounter = 0

            //TODO PRUEBAS COLORES


            /*
            val hsbArray = FloatArray(3)
            java.awt.Color.RGBtoHSB(colorCycle.red, colorCycle.green, colorCycle.blue, hsbArray)
            var hue = hsbArray[0]
            hue += 0.02f
            if (hue > 1.0f) {
                hue = 0f
            }
            val colorCalculator = java.awt.Color.getHSBColor(hue, 1f, 1f)
            colorCycle = Color(colorCalculator.red, colorCalculator.green, colorCalculator.blue)
            */
            i++
            if (i >= 255) i = 0
            colorCycle = makeColorGradient(i, 2.4f, 2.4f, 2.4f, 0, 2, 4, 128, 127)
        }

    }

    //De http://krazydad.com/tutorials/makecolors.php
    fun makeColorGradient(i: Int, frequency1: Float, frequency2: Float, frequency3: Float, phase1: Int, phase2: Int, phase3: Int, center: Int, width: Int): Color {
        val red = Math.sin((frequency1 * i + phase1).toDouble()) * width + center
        val green = Math.sin((frequency2*i + phase2).toDouble()) * width + center
        val blue = Math.sin((frequency3*i + phase3).toDouble()) * width + center
        return Color(red.toInt(), green.toInt(), blue.toInt())
    }




    fun render(gc: GameContainer, game: StateBasedGame, g: Graphics, isInHighScoreState: Boolean) {
        if (isInHighScoreState) {
            fontComputer32.drawStringCentered(Const.GAME_WIDTH / 2f - 300f, Const.GAME_WIDTH, "NEW HIGH SCORE", colorCycle)
        } else {
            fontComputer32.drawStringCentered(Const.GAME_WIDTH / 2f - 300f, Const.GAME_WIDTH, "HIGH SCORES")
        }

        var i = 0
        for ((name, value) in HighScoreManager.scores) {
            fontComputer24.drawString(Const.GAME_WIDTH / 2f - 100f, Const.GAME_HEIGHT / 2f + Const.FONT_SIZE_24 * (2 + i), name)
            fontComputer24.drawString(Const.GAME_WIDTH / 2f + 80f, Const.GAME_HEIGHT / 2f + Const.FONT_SIZE_24 * (2 + i), value.toString())
            i++
        }

    }

    fun isNewScore(score: Int) = score > scores.maxBy { it.value }!!.value

}