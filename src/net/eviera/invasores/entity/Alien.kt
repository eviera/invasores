package net.eviera.invasores.entity

import net.eviera.invasores.event.ScoreEvent
import net.eviera.invasores.helper.Const
import net.eviera.invasores.manager.CollisionManager
import net.eviera.invasores.manager.EventManager
import org.newdawn.slick.*

class Alien (x: Float, y: Float, var alienXDisplacement: Float, var alienYDisplacement: Float) : CollisionableRectangle(x, y, Const.SP_SIZE, Const.SP_SIZE){

    lateinit var sprite: Animation
    var alive: Boolean = true
    lateinit var shoot: Shoot
    lateinit var shootSprite: Image
    var isShooting = false

    fun init(sprite: Animation, shootSprite: Image) {
        this.sprite = sprite
        this.shootSprite = shootSprite
        CollisionManager.addAlien(this)
    }

    fun update(gc: GameContainer, delta: Int, alienXDisplacement: Float, alienYDisplacement: Float, hasToShoot: Boolean) {
        if (alive) {
            this.alienXDisplacement = alienXDisplacement
            this.alienYDisplacement = alienYDisplacement
            if (hasToShoot) {
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
    }

    fun render(gc: GameContainer, g: Graphics) {
        if (alive) {
            x += alienXDisplacement
            y += alienYDisplacement
            sprite.draw(x, y)

            if (isShooting) {
                shoot.render(gc, g)
            }
        }
    }

    fun remove() {
        CollisionManager.removeAlien(this)
        alive = false
    }

    fun shoot() {
        if (!isShooting) {
            isShooting = true
            shoot = Shoot(x, y, shootSprite)
            shoot.init(Shoot.To.SHOOT_TO_PLAYER)
            playShoot()
        }
    }


    override fun collisionWith(collisioned: CollisionableRectangle) {
        playExplosion()
        EventManager.publish(ScoreEvent(Const.SCORE_ALIEN_HIT))
        remove()
    }

    companion object Sounds {
        lateinit var shootSound: Sound
        lateinit var alienExplosion: Sound
        fun init (alienExplosion: Sound, shootSound: Sound) {
            this.alienExplosion = alienExplosion
            this.shootSound = shootSound
        }
        fun playExplosion() {
            alienExplosion.play()
        }
        fun playShoot() {
            shootSound.play()
        }
    }

}
