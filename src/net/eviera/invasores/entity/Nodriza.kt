package net.eviera.invasores.entity

import net.eviera.invasores.helper.Const
import net.eviera.invasores.manager.CollisionManager
import org.newdawn.slick.Animation
import org.newdawn.slick.Image
import org.newdawn.slick.Sound

class Nodriza (x: Float, y: Float) : CollisionableRectangle(x, y, Const.SP_SIZE * 2, Const.SP_SIZE) {

    lateinit var sprite: Animation
    lateinit var shootSprite: Image
    lateinit var explosion: Animation
    lateinit var shoot: Shoot
    var alive = true
    var isExploding = false
    var isShooting = false
    var explosionRemainingTime = Const.ALIEN_EXPLODING_TIME

    fun init(sprite: Animation, shootSprite: Image, explosion: Animation) {
        this.sprite = sprite
        this.shootSprite = shootSprite
        this.explosion = explosion
        CollisionManager.add(CollisionManager.COLLISION_CLASS.NODRIZA, this)
    }

    /*
    fun update(gc: GameContainer, delta: Int, alienXDisplacement: Float, alienYDisplacement: Float, hasToShoot: Boolean) {

        if (alive || isExploding) {
            x += alienXDisplacement
            y += alienYDisplacement
        }

        if (alive) {
            if (hasToShoot) {
                shoot()
            }
        }

        if (isExploding) {
            alienExplosionRemainingTime -= delta
            if (alienExplosionRemainingTime <= 0) {
                isExploding = false
            }
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

        //Rendereo primero el disparo para que el alien este por delante
        if (isShooting) {
            shoot.render(gc, g)
        }
        if (alive) {
            sprite.draw(x, y)
        }
        if (isExploding) {
            alienExplosion.draw(x, y)
        }
    }

    fun remove() {
        CollisionManager.removeAlien(this)
        alive = false
        isExploding = true
    }

    fun shoot() {
        if (!isShooting) {
            isShooting = true
            shoot = Shoot(x, y, shootSprite)
            shoot.init(Shoot.To.SHOOT_TO_PLAYER)
            playShoot()
        }
    }

    */

    override fun collisionWith(collisioned: CollisionableRectangle) {
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
