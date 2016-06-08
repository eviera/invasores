import java.util.*
import Const.SP_SIZE
import org.newdawn.slick.*
import org.newdawn.slick.util.ResourceLoader
import java.awt.Font
import java.io.File

object Manager {

    val fontComputer24 = TrueTypeFont(Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("/resources/fonts/Computerfont.ttf")).deriveFont(24f), false)

    fun init(gc: GameContainer, player: Player, aliens: Array<Alien?>) {
        //Cargo la spritesheet
        val sprites = SpriteSheet(Image("/resources/images/sprites.png"), 64, 64)

        //Cargo la nave del jugador
        val nave =
        player.init(sprites.getSprite(0, 1))

        //Cargo los aliens
        for (f in 0..2) {
            val colDisp = if (f == 1 ) 1.3f else 1f
            for (c in 0..7) {
                val alienAnimation = Animation(arrayOf(sprites.getSprite(f * 2, 0), sprites.getSprite(f * 2 + 1, 0)), getRandomAnimationInterval())
                val alien = Alien(SP_SIZE * colDisp + c * SP_SIZE * 1.7f, SP_SIZE / 3 + SP_SIZE * f * 1.3f)
                alien.init(alienAnimation)
                aliens[f * 8 + c] = alien
            }
        }


    }

    private fun getRandomAnimationInterval() = Random().nextInt(300) + 100

    fun render(gc: GameContainer, g: Graphics, player: Player, aliens: Array<Alien?>) {

        //Rendereo elementos de pantalla
        //Scoreboard
        g.color = Color.white
        g.drawLine(0f, Const.GAME_HEIGHT - Const.SP_SIZE + 5, Const.GAME_WIDTH * 1f, Const.GAME_HEIGHT - Const.SP_SIZE + 5)
        fontComputer24.drawString(0f, Const.GAME_HEIGHT - 50f, "POINTS")



        player.render(gc, g)
        for (alien in aliens) {
            alien?.render(gc, g)
        }
    }


}