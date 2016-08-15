package net.eviera.invasores.entity

import net.eviera.invasores.event.BrickEvent
import net.eviera.invasores.helper.Const
import net.eviera.invasores.helper.Helper
import net.eviera.invasores.manager.CollisionManager
import net.eviera.invasores.manager.EventManager
import org.newdawn.slick.Sound
import java.util.*

class Brick (val tileX: Int, val tileY: Int) : CollisionableRectangle(Helper.convertTileCoordToPixelCoord(tileX), Helper.convertTileCoordToPixelCoord(tileY), Const.SP_SIZE, Const.SP_SIZE) {

    enum class STATUS constructor(val tileId: Int) {
        VACIO(Const.GAME_TILES_ID.NULL.ordinal),            //0
        ROTO(Const.GAME_TILES_ID.BRICK_ROTO.ordinal),       //1
        PARTIDO(Const.GAME_TILES_ID.BRICK_PARTIDO.ordinal), //2
        ENTERO(Const.GAME_TILES_ID.BRICK_ENTERO.ordinal);   //3

    }

    var alive: Boolean = true
    var status = STATUS.ENTERO.ordinal  //3
    val statusValues = STATUS.values()

    fun init() {
        CollisionManager.addBrick(this)
    }

    fun remove() {
        CollisionManager.removeBrick(this)
        alive = false
        EventManager.publish(BrickEvent(Helper.convertPixelCoordToTileCoord(x), Helper.convertPixelCoordToTileCoord(y), Const.GAME_TILES_ID.NULL.ordinal))
    }

    override fun collisionWith(collisioned: CollisionableRectangle) {
        if (collisioned is Alien) {
            status = 0
            playBreakRnd()
            remove()
        } else {
            status--
            playBreakRnd()
            if (status == 0) {
                remove()
            } else if (status > 0) {
                EventManager.publish(BrickEvent(Helper.convertPixelCoordToTileCoord(x), Helper.convertPixelCoordToTileCoord(y), statusValues[status].tileId))
            } else {
                //no hago nada. Puede que al mismo tiempo lo haya destruido un rayo de los aliens y del jugador
            }
        }

    }

    companion object Sounds {
        lateinit var brickBreaks: Array<Sound>
        val ran = Random()
        fun init (brickBreaks: Array<Sound>) {
            this.brickBreaks = brickBreaks
        }
        fun playBreakRnd() {
            brickBreaks[ran.nextInt(brickBreaks.size)].play()
        }
    }

}
