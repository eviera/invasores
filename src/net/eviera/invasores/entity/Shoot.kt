package net.eviera.invasores.entity

import net.eviera.invasores.helper.Const
import net.eviera.invasores.helper.Helper
import net.eviera.invasores.manager.CollisionManager
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image

class Shoot (x: Float, y: Float, val sprite: Image) : CollisionableRectangle(x, y, Const.SP_SIZE, Const.SP_SIZE) {

    enum class To {
        SHOOT_TO_ALIEN,
        SHOOT_TO_PLAYER
    }

    var alive: Boolean = true
    lateinit var to: To

    fun init(to: To) {
        this.to = to
        when(to) {
            To.SHOOT_TO_ALIEN -> CollisionManager.addShootToAlien(this)
            To.SHOOT_TO_PLAYER -> CollisionManager.addShootToPlayer(this)
        }
    }

    fun update(gc: GameContainer, delta: Int) {
        when(to) {
            To.SHOOT_TO_ALIEN -> {
                val displacement = Const.PLAYER_SHOOT_SPEED * delta
                val yDest = y - displacement
                if (yDest < 0) {
                    remove()
                }
                y = yDest
            }

            To.SHOOT_TO_PLAYER -> {
                val displacement = Const.ALIEN_SHOOT_SPEED * delta
                val yDest = y + displacement
                if (yDest > Const.GAME_FLOOR - Const.SP_SIZE) {
                    remove()
                }
                y = yDest
            }
        }

    }

    fun render(gc: GameContainer, g: Graphics) {
        if (alive) {
            sprite.draw(x, y)
        }
    }

    fun remove() {
        alive = false
        when(to) {
            To.SHOOT_TO_ALIEN -> CollisionManager.removeShootToAlien(this)
            To.SHOOT_TO_PLAYER -> CollisionManager.removeShootToPlayer(this)
        }
    }

    override fun collisionWith(collisioned: CollisionableRectangle) {
        remove()
    }

}