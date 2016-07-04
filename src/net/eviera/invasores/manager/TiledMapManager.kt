package net.eviera.invasores.manager

import net.eviera.invasores.entity.Brick
import net.eviera.invasores.helper.Const
import org.newdawn.slick.tiled.TiledMap

object TiledMapManager {

    lateinit var tiledMap: TiledMap

    fun init() {
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
    }

    fun render() {
        tiledMap.render(0, 0)
    }

    fun remove(x: Int, y: Int) = tiledMap.setTileId(x, y, Const.GAME_TILES_LAYER, Const.GAME_TILES_ID.NULL.ordinal)

    fun change(x: Int, y: Int, newTileId: Int) = tiledMap.setTileId(x, y, Const.GAME_TILES_LAYER, newTileId)


}