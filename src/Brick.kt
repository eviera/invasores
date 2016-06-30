import org.newdawn.slick.Image

class Brick (val tileX: Int, val tileY: Int) : CollisionableRectangle(Helper.convertXTileCoordToPixelCoord(tileX), Helper.convertYTileCoordToPixelCoord(tileY), Const.SP_SIZE, Const.SP_SIZE) {

    enum class STATUS constructor(val tileId: Int) {
        ENTERO(Const.GAME_TILES_ID.BRICK_ENTERO.ordinal),
        PARTIDO(Const.GAME_TILES_ID.BRICK_PARTIDO.ordinal),
        ROTO(Const.GAME_TILES_ID.BRICK_ROTO.ordinal);
    }

    lateinit var sprites: Array<Image>
    var alive: Boolean = true
    var status = STATUS.ENTERO

    fun init() {
        CollisionManager.addBrick(this)
    }

    fun remove() {
        CollisionManager.removeBrick(this)
        alive = false
        TiledMapManager.remove(Helper.convertXPixelCoordToTileCoord(x), Helper.convertYPixelCoordToTileCoord(y))
    }

    override fun collisionWith(collisioned: CollisionableRectangle) {
        remove()
        //TiledMapManager.change(Helper.convertXPixelCoordToTileCoord(x), Helper.convertYPixelCoordToTileCoord(y), status.tileId)
    }

}
