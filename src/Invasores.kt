import org.newdawn.slick.*
import Const.PLAYER_SPEED
import Const.GAME_WIDTH
import Const.SP_SIZE
import org.newdawn.slick.util.ResourceLoader
import java.awt.Font

class Invasores : BasicGame("Invasores") {

    val player = Player()
    val aliens = arrayOfNulls<Alien>(24)
    var fontComputer24: TrueTypeFont? = null


    override fun init(gc: GameContainer?) {
        if (gc == null ) throw RuntimeException("Error de gc null")

        fontComputer24 = TrueTypeFont(Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("/resources/fonts/Computerfont.ttf")).deriveFont(24f), false)

        //Cargo la spritesheet
        val sprites = SpriteSheet(Image("/resources/images/sprites.png"), 64, 64)

        //Cargo la nave del jugador
        val nave = player.init(sprites.getSprite(0, 1), sprites.getSprite(1, 1))

        //Cargo los aliens
        for (f in 0..2) {
            val colDisp = if (f == 1 ) 1.3f else 1f
            for (c in 0..7) {
                val alienAnimation = Animation(arrayOf(sprites.getSprite(f * 2, 0), sprites.getSprite(f * 2 + 1, 0)), Helper.getRandomAnimationInterval())
                val alien = Alien(SP_SIZE * colDisp + c * SP_SIZE * 1.7f, SP_SIZE / 3 + SP_SIZE * f * 1.3f)
                alien.init(alienAnimation)
                aliens[f * 8 + c] = alien
            }
        }


    }

    override fun update(gc: GameContainer?, delta: Int) {
        if (gc == null ) throw RuntimeException("Error de gc null")

        /*
            delta tiene la cantidad de milisegundos que pasaron desde la iteracion anterior
            playerDisplacement es la distancia en pixels que se tiene que mover. Por ejemplo si pasaron 8 milisegundos desde el ultimo frame
            y PLAYER_SPEED (en pixels/milisegundo) es 0.5, entonces se va a mover 8 * 0.5 = 4 pixels en este frame
         */
        val playerDisplacement = PLAYER_SPEED * delta


        //Controlo que no se pase de los bordes snappeando a 0 o a GAME_WIDTH - SP_SIZE (ancho del juego - tamaño del sprite)
        val input = gc.input
        if (input.isKeyDown(Input.KEY_LEFT) as Boolean) {
            val px = player.getX()
            var pxDest = px - playerDisplacement
            if (pxDest < 0) pxDest = 0f
            player.x =pxDest
        }
        if (input.isKeyDown(Input.KEY_RIGHT) as Boolean) {
            val px = player.getX()
            var pxDest = px + playerDisplacement
            if (pxDest > GAME_WIDTH - SP_SIZE) pxDest = GAME_WIDTH - SP_SIZE
            player.x = pxDest
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit()
        }


    }

    override fun render(gc: GameContainer?, g: Graphics?) {
        if (gc == null || g == null || fontComputer24 == null) throw RuntimeException("Error de inicializacion")
        val font = (fontComputer24 as TrueTypeFont)

        //Rendereo elementos de pantalla
        //Scoreboard
        g.color = Color.white
        g.drawLine(0f, Const.GAME_HEIGHT - Const.SP_SIZE + 5, Const.GAME_WIDTH * 1f, Const.GAME_HEIGHT - Const.SP_SIZE + 5)
        font.drawString(0f, Const.GAME_HEIGHT - 50f, "POINTS")


        player.render(gc, g)
        for (alien in aliens) {
            alien?.render(gc, g)
        }

    }

}


fun main(args: Array<String>) {
    val appgc = AppGameContainer(Invasores())
    appgc.setDisplayMode(Const.GAME_WIDTH, Const.GAME_HEIGHT, false)
    appgc.setTargetFrameRate(60)
    appgc.setVSync(true)
    appgc.setShowFPS(false)
    appgc.start()
}