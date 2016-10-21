package net.eviera.invasores.manager

import net.eviera.invasores.entity.Score
import net.eviera.invasores.helper.Const
import net.eviera.invasores.helper.Helper
import org.newdawn.slick.*
import org.newdawn.slick.state.StateBasedGame
import java.util.*

object HighScoreManager {

    lateinit var fontComputer24: TrueTypeFont
    val scores = ArrayList<Score>(3)
    val savedState = SavedState("invasores.state")

    fun init() {
        fontComputer24 = Helper.getComputerFont(Const.FONT_SIZE_24)

        //Cargo 3 highscores
        savedState.load()
        for (i in 1..3) {
            val highScoreName = savedState.getString("highscore.name.$i", "...")
            val highScoreScore = savedState.getNumber("highscore.value.$i", 0.0)
            scores.add(Score(highScoreName, highScoreScore.toInt()))
        }
    }

    fun render(gc: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        if (gc == null || g == null) throw RuntimeException("Error de inicializacion")

        g.color = Color.white
        var i = 0
        for ((name, value) in HighScoreManager.scores) {
            fontComputer24.drawString(Const.GAME_WIDTH / 2f - 100f, Const.GAME_HEIGHT / 2f + Const.FONT_SIZE_24 * (2 + i), name)
            fontComputer24.drawString(Const.GAME_WIDTH / 2f + 80f, Const.GAME_HEIGHT / 2f + Const.FONT_SIZE_24 * (2 + i), value.toString())
            i++
        }

    }

    fun isNewScore(score: Int) = score > scores.maxBy { it.value }!!.value

}