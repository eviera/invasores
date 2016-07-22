package net.eviera.invasores.manager

import net.eviera.invasores.entity.HighScore
import org.newdawn.slick.SavedState
import java.util.*

object HighScoreManager {

    val scores = ArrayList<HighScore>(3)

    val savedState = SavedState("invasores.state")

    fun init() {
        //Cargo 3 highscores
        savedState.load()
        val highScoreName1 = savedState.getString("highscore.name.1", "...")
        val highScoreName2 = savedState.getString("highscore.name.2", "...")
        val highScoreName3 = savedState.getString("highscore.name.3", "...")
        val highScoreScore1 = savedState.getNumber("highscore.score.1", 0.0)
        val highScoreScore2 = savedState.getNumber("highscore.score.2", 0.0)
        val highScoreScore3 = savedState.getNumber("highscore.score.3", 0.0)

        scores.add(HighScore(highScoreName1, highScoreScore1.toInt()))
        scores.add(HighScore(highScoreName2, highScoreScore2.toInt()))
        scores.add(HighScore(highScoreName3, highScoreScore3.toInt()))

    }

    fun isNewScore(score: Int)  {
        for (score in scores) {
        }
    }
}