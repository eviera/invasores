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
    var colorCounter = 0
    var colorIndex = 0
    val colors = arrayOf(Color(255, 64, 239), Color(254, 242, 56), Color(255, 56, 93),
            Color(54, 255, 97), Color(82, 59, 255), Color(255, 115, 54), Color(255, 63, 144),
            Color(62, 216, 255), Color(198, 255, 61))

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
            colorIndex++
            if (colorIndex == colors.size) {
                colorIndex = 0
            }
        }

    }

    fun render(gc: GameContainer, game: StateBasedGame, g: Graphics, isInHighScoreState: Boolean) {
        if (isInHighScoreState) {
            fontComputer32.drawStringCentered(Const.GAME_WIDTH / 2f - 300f, Const.GAME_WIDTH, "NEW HIGH SCORE", colors[colorIndex])
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