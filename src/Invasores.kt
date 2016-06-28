import org.newdawn.slick.*
import org.newdawn.slick.util.ResourceLoader
import java.awt.Font

class Invasores : BasicGame("Invasores") {

    val player = Player()
    val aliens = arrayOfNulls<Alien>(24)
    lateinit var fontComputer24: TrueTypeFont

    /**
     * Mantiene la posicion de los aliens (en el extremo izquierdo)
     */
    var aliensX = Const.ALIEN_START_X
    var aliensY = Const.ALIEN_START_Y
    /**
     * Hasta donde debe llegar cuando bajen los aliens en el proximo escalon
     */
    var aliensEndY = aliensY + Const.SP_SIZE

    var aliensDirection = 1
    var movimiento : Const.MOV = Const.MOV.H;


    override fun init(gc: GameContainer?) {
        if (gc == null ) throw RuntimeException("Error de gc null")

        fontComputer24 = TrueTypeFont(Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("/resources/fonts/Computerfont.ttf")).deriveFont(24f), false)

        //Cargo la spritesheet
        val sprites = SpriteSheet(Image("/resources/images/sprites_32.png"), Const.SP_SIZE.toInt(), Const.SP_SIZE.toInt())

        //Cargo la nave del jugador
        player.init(sprites.getSprite(0, 1), sprites.getSprite(1, 1), Sound("resources/sounds/player_shoot.wav"))

        //Cargo los aliens
        for (f in 0..Const.ALIEN_ROWS - 1) {
            val colDisp = if (f == 1 ) Const.ALIEN_X_SHIFT else 1f
            for (c in 0..Const.ALIEN_COLS - 1) {
                val alienAnimation = Animation(arrayOf(sprites.getSprite(f * 2, 0), sprites.getSprite(f * 2 + 1, 0)), Helper.getRandomAnimationInterval())
                val alien = Alien(Helper.getAlienColPos(colDisp, c), Helper.getAlienRowPos(f), 0f, 0f)
                alien.init(alienAnimation)
                aliens[f * Const.ALIEN_COLS + c] = alien
            }
        }


    }

    override fun update(gc: GameContainer?, delta: Int) {
        if (gc == null ) throw RuntimeException("Error de gc null")
        val input = gc.input

        //Si hay mucho delta, lo seteo a un minimo de 20 (para cuando se draggea la ventana)
        var correctedDelta = delta
        if (correctedDelta > 20) {
            correctedDelta = 20
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit()
        }

        //Actualizo el jugador
        player.update(gc, correctedDelta)

        //Calculo la velocidad y direccion de los aliens
        var alienXDisplacement = 0f;
        var alienYDisplacement = 0f;

        when(movimiento) {
            Const.MOV.H -> {
                //Donde deberia dibujarse en X en el proximo frame los aliens
                alienXDisplacement = Const.ALIEN_START_SPEED * correctedDelta * aliensDirection
                var aliensXDest = aliensX + alienXDisplacement
                //Si me paso del borde derecho
                if (aliensXDest > Const.ALIEN_END_X) {
                    aliensXDest = Const.ALIEN_END_X
                    aliensDirection = -1
                    alienXDisplacement = 0f
                    movimiento = Const.MOV.V
                //Si me paso del borde izquierdo
                } else if (aliensXDest < Const.ALIEN_START_X) {
                    aliensXDest = Const.ALIEN_START_X
                    aliensDirection = 1
                    alienXDisplacement = 0f
                    movimiento = Const.MOV.V
                }
                aliensX = aliensXDest
            }
            Const.MOV.V -> {
                alienYDisplacement = (Const.ALIEN_START_SPEED  * 2) * correctedDelta
                var aliensYDest = aliensY + alienYDisplacement
                if (aliensYDest > aliensEndY) {
                    aliensYDest = aliensEndY
                    aliensEndY += Const.SP_SIZE
                    alienYDisplacement = 0f
                    movimiento = Const.MOV.H
                }

                aliensY = aliensYDest
            }
        }


        for (alien in aliens) {
            alien?.update(gc, correctedDelta, alienXDisplacement, alienYDisplacement)
        }

        //Chequeo las colisiones
        CollisionManager.checkCollision()


    }



    override fun render(gc: GameContainer?, g: Graphics?) {
        if (gc == null || g == null) throw RuntimeException("Error de inicializacion")

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


fun main(args: Array<String>) {
    val appgc = AppGameContainer(Invasores())
    appgc.setDisplayMode(Const.GAME_WIDTH, Const.GAME_HEIGHT, false)
    appgc.setTargetFrameRate(60)
    appgc.setVSync(true)
    appgc.setShowFPS(false)
    appgc.start()
}