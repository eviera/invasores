package net.eviera.invasores.entity

import net.eviera.invasores.helper.Const
import net.eviera.invasores.helper.Const.GAME_WIDTH
import net.eviera.invasores.helper.Const.PLAYER_SPEED
import net.eviera.invasores.helper.Const.SP_SIZE
import net.eviera.invasores.manager.CollisionManager
import org.newdawn.slick.*

class Player : CollisionableRectangle(Const.PLAYER_START_X, Const.PLAYER_START_Y, SP_SIZE, SP_SIZE) {

    lateinit var sprite: Image
    lateinit var shoot: Shoot
    lateinit var shootSprite: Image
    var isShooting = false

    fun init(sprite: Image, shootSprite: Image) {
        this.sprite = sprite
        this.shootSprite = shootSprite
        CollisionManager.addPlayer(this)
    }

    fun update(gc: GameContainer, delta: Int) {
        val input = gc.input

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


        if (isShooting) {
            if (shoot.alive) {
                shoot.update(gc, delta)
            } else {
                isShooting = false
            }
        }

    }

    fun render(gc: GameContainer, g: Graphics) {
        g.drawImage(sprite, x, y)
        if (isShooting) {
            shoot.render(gc, g)
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

    }

    companion object Sounds {
        lateinit var shootSound: Sound
        fun init (shootSound: Sound) {
            this.shootSound = shootSound
        }
        fun playShoot() {
            shootSound.play()
        }
    }


}