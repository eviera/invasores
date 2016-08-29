package net.eviera.invasores.entity

import net.eviera.invasores.event.PlayerEvent
import net.eviera.invasores.helper.Const
import net.eviera.invasores.helper.Const.GAME_WIDTH
import net.eviera.invasores.helper.Const.PLAYER_SPEED
import net.eviera.invasores.helper.Const.SP_SIZE
import net.eviera.invasores.manager.CollisionManager
import net.eviera.invasores.manager.EventManager
import org.newdawn.slick.*
import org.newdawn.slick.particles.ConfigurableEmitter
import org.newdawn.slick.particles.ParticleSystem

class Player : CollisionableRectangle(Const.PLAYER_START_X, Const.PLAYER_START_Y, SP_SIZE, SP_SIZE) {

    lateinit var sprite: Image
    lateinit var shoot: Shoot
    lateinit var shootSprite: Image
    lateinit var playerExplosionEmitter: ConfigurableEmitter
    lateinit var playerExplosionSystem: ParticleSystem
    var alive = true
    var isShooting = false
    var isExploding = false
    var playerExplosionRemainingTime = 0f

    fun init(sprite: Image, shootSprite: Image, playerExplosionSystem: ParticleSystem) {
        this.sprite = sprite
        this.shootSprite = shootSprite
        this.playerExplosionSystem = playerExplosionSystem
        this.playerExplosionEmitter = playerExplosionSystem.getEmitter(0) as ConfigurableEmitter
        CollisionManager.add(this)
    }

    fun update(gc: GameContainer, delta: Int) {
        val input = gc.input

        if (alive) {
            /*
            delta tiene la cantidad de milisegundos que pasaron desde la iteracion anterior
            playerDisplacement es la distancia en pixels que se tiene que mover. Por ejemplo si pasaron 8 milisegundos desde el ultimo frame
            y PLAYER_SPEED (en pixels/milisegundo) es 0.5, entonces se va a mover 8 * 0.5 = 4 pixels en este frame
            */
            val playerDisplacement = PLAYER_SPEED * delta

            //Controlo que no se pase de los bordes snappeando a 0 o a GAME_WIDTH - SP_SIZE (ancho del juego - tamaño del sprite)
            if (input.isKeyDown(Input.KEY_LEFT)) {
                var pxDest = x - playerDisplacement
                if (pxDest < 0) pxDest = 0f
                x = pxDest
            }

            if (input.isKeyDown(Input.KEY_RIGHT)) {
                var pxDest = x + playerDisplacement
                if (pxDest > GAME_WIDTH - SP_SIZE) pxDest = GAME_WIDTH - SP_SIZE
                x = pxDest
            }

            if (input.isKeyPressed(Input.KEY_SPACE)) {
                shoot()
            }
        }

        if (isExploding) {
            playerExplosionRemainingTime -= delta
            if (playerExplosionRemainingTime <= 0) {
                isExploding = false

                //Publico el evento de la muerte del player cuando termina de hacer la explosion
                EventManager.publish(PlayerEvent(false))

                reset()
            }
            playerExplosionSystem.update(delta)
        }

        if (isShooting) {
            if (shoot.alive) {
                shoot.update(gc, delta)
            } else {
                isShooting = false
            }
        }

    }

    fun render(gc: GameContainer, g: Graphics) {
        if (alive) {
            g.drawImage(sprite, x, y)
        }
        if (isShooting) {
            shoot.render(gc, g)
        }
        if (isExploding) {
            playerExplosionSystem.render()
        }
    }


    fun shoot() {
        if (!isShooting) {
            isShooting = true
            shoot = Shoot(x, y, shootSprite)
            shoot.init(Shoot.To.SHOOT_TO_ALIEN)
            playShoot()
        }
    }

    override fun collisionWith(collisioned: CollisionableRectangle) {
        playExplosion()
        playerExplosionSystem.reset()
        playerExplosionEmitter.setPosition(x + (Const.SP_SIZE / 2), y + (Const.SP_SIZE / 2))
        playerExplosionRemainingTime = playerExplosionEmitter.initialLife.max
        isExploding = true
        alive = false
    }

    override fun getType(): COLLISION_CLASS {
        return COLLISION_CLASS.PLAYER
    }

    private fun reset() {
        alive = true
        x = Const.PLAYER_START_X
        y = Const.PLAYER_START_Y
        playerExplosionSystem.reset()
    }

    companion object Sounds {
        lateinit var shootSound: Sound
        lateinit var playerExplosion: Sound
        fun init (playerExplosion: Sound, shootSound: Sound) {
            this.playerExplosion = playerExplosion
            this.shootSound = shootSound
        }
        fun playExplosion() {
            playerExplosion.play()
        }
        fun playShoot() {
            shootSound.play()
        }
    }

}