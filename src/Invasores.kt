import net.eviera.invasores.entity.Alien
import net.eviera.invasores.entity.Brick
import net.eviera.invasores.entity.Player
import net.eviera.invasores.helper.Const
import net.eviera.invasores.helper.Helper
import net.eviera.invasores.manager.CollisionManager
import net.eviera.invasores.manager.TiledMapManager
import org.newdawn.slick.*
import org.newdawn.slick.util.ResourceLoader
import java.awt.Font

class Invasores : BasicGame("Invasores") {

    val player = Player()
    val aliens = arrayOfNulls<Alien>(Const.ALIEN_COLS * Const.ALIEN_ROWS)
    lateinit var fontComputer24: TrueTypeFont

    /**
     * Mantiene la posicion vertical de los aliens (arranca en el extremo izquierdo)
     */
    var aliensX = Const.ALIEN_START_X

    /**
     * Mantiene la posicion horizontal de los aliens (arranca arriba)
     */
    var aliensY = Const.ALIEN_START_Y

    /**
     * Hasta donde debe llegar cuando bajen los aliens en el proximo escalon
     */
    var aliensEndY = aliensY + Const.SP_SIZE

    /**
     * Direccion del movimiento horizonal: 1 a la derecha, -1 a la izquierda
     */
    var aliensDirection = 1

    /**
     * Determina si se mueve en forma horizontal o vertical (arranca en horizontal)
     */
    var movimiento : Const.MOV = Const.MOV.H;

    val score = 0


    override fun init(gc: GameContainer?) {
        if (gc == null ) throw RuntimeException("Error de gc null")

        fontComputer24 = TrueTypeFont(Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("/resources/fonts/Computerfont.ttf")).deriveFont(24f), false)

        //Cargo la spritesheet
        val sprites = SpriteSheet(Image("/resources/images/sprites_32.png"), Const.SP_SIZE.toInt(), Const.SP_SIZE.toInt())

        //Levanto el net.eviera.invasores.manager.TiledMapManager
        TiledMapManager.init();

        //Cargo la nave del jugador
        player.init(sprites.getSprite(0, 1), sprites.getSprite(1, 1))

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

        //Cargo los sonidos
        Player.Sounds.init(Sound("resources/sounds/player_shoot.wav"))
        Brick.Sounds.init(arrayOf(Sound("resources/sounds/brick_break_1.wav"), Sound("resources/sounds/brick_break_2.wav"),
                Sound("resources/sounds/brick_break_3.wav"), Sound("resources/sounds/brick_break_4.wav")))
        Alien.Sounds.init(Sound("resources/sounds/alien_explosion.wav"))

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

            //Movimiento vertical
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

            //Movimiento horizontal
            Const.MOV.V -> {
                //Hago el movimento en horizontal el doble de veloz que el vertical (porque queda mas lindo)
                alienYDisplacement = (Const.ALIEN_START_SPEED  * 2) * correctedDelta
                var aliensYDest = aliensY + alienYDisplacement
                //Si llegue al borde del escalon, me quedo ahi, e incremento el escalon al siguiente peldaño (un SP_SIZE mas)
                if (aliensYDest > aliensEndY) {
                    aliensYDest = aliensEndY
                    aliensEndY += Const.SP_SIZE
                    alienYDisplacement = 0f
                    movimiento = Const.MOV.H
                }
                aliensY = aliensYDest
            }
        }


        //Actualizo los aliens
        for (alien in aliens) {
            alien?.update(gc, correctedDelta, alienXDisplacement, alienYDisplacement)
        }

        //Chequeo las colisiones
        CollisionManager.checkCollision()


    }



    override fun render(gc: GameContainer?, g: Graphics?) {
        if (gc == null || g == null) throw RuntimeException("Error de inicializacion")

        //Rendereo elementos de pantalla

        //Mapa
        TiledMapManager.render()

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