package net.eviera.invasores.state

import net.eviera.invasores.entity.Alien
import net.eviera.invasores.entity.Brick
import net.eviera.invasores.entity.FlashMessage
import net.eviera.invasores.entity.Player
import net.eviera.invasores.event.*
import net.eviera.invasores.helper.Const
import net.eviera.invasores.helper.Helper
import net.eviera.invasores.manager.CollisionManager
import net.eviera.invasores.manager.EventManager
import org.newdawn.slick.*
import org.newdawn.slick.openal.SoundStore
import org.newdawn.slick.particles.ParticleIO
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.state.transition.FadeInTransition
import org.newdawn.slick.tiled.TiledMap
import org.newdawn.slick.util.ResourceLoader
import java.awt.Font
import java.util.*

class GameState : BasicGameState() {


    //Entidades
    val player = Player()
    val aliens = arrayOfNulls<Alien>(Const.ALIEN_COLS * Const.ALIEN_ROWS)
    lateinit var nodriza: Alien
    lateinit var fontComputer24: TrueTypeFont
    lateinit var tiledMap: TiledMap
    lateinit var playerScoreSprite: Image
    val ran = Random()
    var flashMessage = FlashMessage()


    //Contadores
    /**
     * Mantiene la cuenta de los aliens vivos
     */
    var aliensAliveCount = aliens.size

    /**
     * La velocidad de los aliens, que se va incrementando cuando bajan
     */
    var alienSpeed = Const.ALIEN_SPEED_INIT

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
    var movimiento : Const.MOV = Const.MOV.H

    /**
     * Va sumando los deltas (milisegundos). Cuando superan el ALIEN_START_FIRE_RATE_MILLIS pueden disparar
     */
    var aliensShootDeltaCounter = 0

    var score = 0
    var lives = 3

    override fun init(gc: GameContainer?, game: StateBasedGame?) {
        if (gc == null || game == null) throw RuntimeException("Error de init")

        fontComputer24 = TrueTypeFont(Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("/resources/fonts/Computerfont.ttf")).deriveFont(Const.FONT_SIZE_24), false)

        //Volumen al medio
        SoundStore.get().soundVolume = 0.5f

        //Cargo la spritesheet
        val sprites = SpriteSheet(Image("/resources/images/sprites_32.png"), Const.SP_SIZE.toInt(), Const.SP_SIZE.toInt())

        //Cargo el tiledMap y computo los ladrillos del muro
        tiledMap = TiledMap("/resources/images/tiledmap.tmx")
        //Busco los ladrillos en el tiledMap, y cuando los encuentro, los creo
        for (x in 0..Const.GAME_TILES_WIDTH - 1) {
            for (y in 0..Const.GAME_TILES_HEIGHT - 1) {
                val tileId = tiledMap.getTileId(x, y, Const.GAME_TILES_LAYER)
                if (tileId == Const.GAME_TILES_ID.BRICK_ENTERO.ordinal) {
                    val brick = Brick(x, y)
                    brick.init()
                }
            }
        }

        //Cargo la nave del jugador
        val playerExplosionSystem = ParticleIO.loadConfiguredSystem("/resources/emitters/player_explosion.xml")
        player.init(sprites.getSprite(0, 1), sprites.getSprite(1, 1), playerExplosionSystem)
        playerScoreSprite = sprites.getSprite(3, 1)

        //Cargo los aliens
        val alienExplosion = Animation(arrayOf(sprites.getSprite(0, 2), sprites.getSprite(1, 2), sprites.getSprite(2, 2)), Const.ALIEN_EXPLODING_TIME / 3)
        alienExplosion.setLooping(false)
        for (f in 0..Const.ALIEN_ROWS - 1) {
            val colDisp = if (f == 1 ) Const.ALIEN_X_SHIFT else 1f
            for (c in 0..Const.ALIEN_COLS - 1) {
                val alienAnimation = Animation(arrayOf(sprites.getSprite(f * 2, 0), sprites.getSprite(f * 2 + 1, 0)), Helper.getRandomAnimationInterval())
                val alien = Alien(Helper.getAlienColPos(colDisp, c), Helper.getAlienRowPos(f), Const.SCORE_ALIEN.values()[f].score)
                alien.init(alienAnimation, sprites.getSprite(2, 1), alienExplosion)
                aliens[f * Const.ALIEN_COLS + c] = alien
            }
        }

        //Cargo la nave nodriza
        //nodriza = Alien()

        //Inicializo el flash
        flashMessage.init(fontComputer24)

        //Cargo los sonidos
        Player.Sounds.init(Sound("resources/sounds/player_explosion.wav"), Sound("resources/sounds/player_shoot.wav"))
        Brick.Sounds.init(arrayOf(Sound("resources/sounds/brick_break_1.wav"), Sound("resources/sounds/brick_break_2.wav"),
                Sound("resources/sounds/brick_break_3.wav"), Sound("resources/sounds/brick_break_4.wav")))
        Alien.Sounds.init(Sound("resources/sounds/alien_explosion.wav"), Sound("resources/sounds/alien_shoot.wav"))

        //Escucho eventos
        EventManager.addGameListener(object : Listener {
            override fun fired(e: Event) {
                val gameEvent = (e as GameEvent)
                score += gameEvent.score
                if (gameEvent.over) {
                    gc.input.clearKeyPressedRecord()
                    game.enterState(Const.STATES.GAMEOVER.ordinal, null, FadeInTransition(Color.black, Const.PAUSE_TRANSITION_SPEED))
                }
            }
        })

        EventManager.addBrickListener(object : Listener {
            override fun fired(e: Event) {
                val brickEvent = (e as BrickEvent)
                tiledMap.setTileId(brickEvent.x, brickEvent.y, Const.GAME_TILES_LAYER, brickEvent.tileId)
            }
        })

        EventManager.addAlienListener(object : Listener {
            override fun fired(e: Event) {
                val alienEvent = (e as AlienEvent)
                if (!alienEvent.alienAlive) {
                    aliensAliveCount--
                    if (aliensAliveCount < 0) {
                        aliensAliveCount = 0
                    }
                }
            }
        })

        EventManager.addPlayerListener(object : Listener{
            override fun fired(e: Event) {
                val playerEvent = (e as PlayerEvent)
                if (!playerEvent.playerAlive) {
                    lives--
                    if (lives == 0) {
                        EventManager.publish(GameEvent(0, true))
                    }
                }
            }

        })
    }

    override fun update(gc: GameContainer?, game: StateBasedGame?, delta: Int) {
        if (gc == null || game == null) throw RuntimeException("Error de update")
        val input = gc.input

        //TODO REMOVER!! Este es un hack para poder freezar la pantalla y ver que pasa
        if (Helper.PAUSE_DEV_MODE_ONLY) { return }


        //Si hay mucho delta, lo seteo a un minimo de 20 (para cuando se draggea la ventana)
        var correctedDelta = delta
        if (correctedDelta > 20) {
            correctedDelta = 20
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            PauseState.img = Helper.grayImage(gc)

            gc.input.clearKeyPressedRecord()
            game.enterState(Const.STATES.PAUSE.ordinal, null, FadeInTransition(Color.black, Const.PAUSE_TRANSITION_SPEED))
        }

        if (input.isKeyPressed(Input.KEY_EQUALS) || input.isKeyPressed(Input.KEY_ADD) || input.isKeyPressed(Input.KEY_RBRACKET)) {
            if (SoundStore.get().soundVolume < 1f) {
                SoundStore.get().soundVolume += 0.1f
                if (SoundStore.get().soundVolume > 1f) {
                    SoundStore.get().soundVolume = 1f
                }
            }
            val volume = Math.round(SoundStore.get().soundVolume * 100)
            flashMessage.show("VOLUME $volume")
        }
        if (input.isKeyPressed(Input.KEY_MINUS) || input.isKeyPressed(Input.KEY_SUBTRACT) || input.isKeyPressed(Input.KEY_SLASH)) {
            if (SoundStore.get().soundVolume > 0f) {
                SoundStore.get().soundVolume -= 0.1f
                if (SoundStore.get().soundVolume < 0f) {
                    SoundStore.get().soundVolume = 0f
                }
            }
            val volume = Math.round(SoundStore.get().soundVolume * 100)
            flashMessage.show("VOLUME $volume")
        }

        //Actualizo el jugador
        player.update(gc, correctedDelta)

        //Calculo la velocidad y direccion de los aliens
        var alienXDisplacement = 0f
        var alienYDisplacement = 0f
        when(movimiento) {

        //Movimiento horizontal
            Const.MOV.H -> {
                //Donde deberia dibujarse en X en el proximo frame los aliens
                alienXDisplacement = alienSpeed * correctedDelta * aliensDirection
                var aliensXDest = aliensX + alienXDisplacement
                //Si me paso del borde derecho
                if (aliensXDest > Const.ALIEN_END_X) {
                    aliensXDest = Const.ALIEN_END_X
                    aliensDirection = -1
                    alienXDisplacement = 0f
                    movimiento = Const.MOV.V
                    //Incremento la velocidad de los aliens
                    alienSpeed += Const.ALIEN_SPEED_INCREMENT
                    //Si me paso del borde izquierdo
                } else if (aliensXDest < Const.ALIEN_START_X) {
                    aliensXDest = Const.ALIEN_START_X
                    aliensDirection = 1
                    alienXDisplacement = 0f
                    movimiento = Const.MOV.V
                    //Incremento la velocidad de los aliens
                    alienSpeed += Const.ALIEN_SPEED_INCREMENT
                }
                aliensX = aliensXDest
            }

        //Movimiento vertical
            Const.MOV.V -> {
                //Hago el movimento en horizontal el doble de veloz que el vertical (porque queda mas lindo)
                alienYDisplacement = (alienSpeed  * 2) * correctedDelta
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


        //Determino si paso tiempo suficiente para que los aliens puedan disparar
        var hasAlienPermissionToFire = false
        aliensShootDeltaCounter += delta
        if (aliensShootDeltaCounter >= Const.ALIEN_START_FIRE_RATE_MILLIS) {
            hasAlienPermissionToFire = ran.nextInt(1) == 0
            aliensShootDeltaCounter = 0
        }

        //Actualizo los aliens y determino quien dispara (si puede)
        var alienToShoot = -1
        if (hasAlienPermissionToFire) {
            alienToShoot = ran.nextInt(aliensAliveCount)
        }
        var i = 0
        for (alien in aliens) {
            if (alien != null) {
                if (alien.alive) {
                    i++
                }
                alien.update(gc, correctedDelta, alienXDisplacement, alienYDisplacement, alienToShoot == i)
            }
        }

        flashMessage.update(gc, delta)

        //Chequeo las colisiones
        CollisionManager.checkCollision()

        //Despacho los eventos
        EventManager.dispatchEvents()

    }

    override fun render(gc: GameContainer?, game: StateBasedGame?, g: Graphics?) {
        if (gc == null || g == null) throw RuntimeException("Error de inicializacion")

        //Mapa
        tiledMap.render(0, 0)

        //Scoreboard
        g.color = Color.white

        g.drawLine(0f, Const.GAME_FLOOR, Const.GAME_WIDTH * 1f, Const.GAME_FLOOR)
        fontComputer24.drawString(Const.FONT_SIZE_24 / 2, Const.GAME_FLOOR + 2, "POINTS: $score")
        for (liveCount in 0..lives - 1) {
            g.drawImage(playerScoreSprite, Const.GAME_WIDTH - (liveCount * Const.SP_SIZE) - Const.SP_SIZE -  Const.FONT_SIZE_24 / 3, Const.GAME_FLOOR - 6)
        }


        player.render(gc, g)

        for (alien in aliens) {
            alien?.render(gc, g)
        }

        flashMessage.render(gc, g)

    }

    override fun getID(): Int {
        return Const.STATES.GAME.ordinal
    }


}