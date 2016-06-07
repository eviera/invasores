import org.newdawn.slick.Animation
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import java.util.*
import Const.SP_SIZE

object Manager {


    fun init(gc: GameContainer, player: Player, aliens: Array<Alien?>) {
        //Cargo la nave del jugador
        val nave = Image("/resources/images/nave_64.png")
        player.init(nave)

        //Cargo los aliens
        for (f in 0..2) {
            val alienAnimation = Animation(arrayOf(Image("/resources/images/alien${f+1}_1_64.png"), Image("/resources/images/alien${f+1}_2_64.png")), getRandomAnimationInterval())
            for (c in 0..7) {
                val alien = Alien(SP_SIZE + c * SP_SIZE * 2, SP_SIZE + SP_SIZE * f * 2f)
                alien.init(alienAnimation)
                aliens[f * 8 + c] = alien
            }
        }

        /*
        for (i in 0..7) {
            val alienAnimation = Animation(arrayOf(Image("/resources/images/alien1_1_64.png"), Image("/resources/images/alien1_2_64.png")), getRandomAnimationInterval())
            val alien = Alien(SP_SIZE + (i * SP_SIZE * 2), SP_SIZE)
            alien.init(alienAnimation)
            aliens[i] = alien
        }
        for (i in 8..14) {
            val alienAnimation = Animation(arrayOf(Image("/resources/images/alien2_1_64.png"), Image("/resources/images/alien2_2_64.png")), getRandomAnimationInterval())
            val alien = Alien(SP_SIZE * 1.5f + ((i-8) * SP_SIZE * 2), SP_SIZE * 2.5f)
            alien.init(alienAnimation)
            aliens[i] = alien
        }
        for (i in 15..23) {
            val alienAnimation = Animation(arrayOf(Image("/resources/images/alien3_1_64.png"), Image("/resources/images/alien3_2_64.png")), getRandomAnimationInterval())
            val alien = Alien(SP_SIZE  + ((i-15) * SP_SIZE * 2), SP_SIZE * 4f)
            alien.init(alienAnimation)
            aliens[i] = alien
        }
        */

    }

    private fun getRandomAnimationInterval() = Random().nextInt(300) + 200

    fun render(gc: GameContainer, g: Graphics, player: Player, aliens: Array<Alien?>) {
        player.render(gc, g)
        for (alien in aliens) {
            alien?.render(gc, g)
        }
    }


}