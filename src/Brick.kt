import org.newdawn.slick.Image
import org.newdawn.slick.Sound

class Brick (val tileX: Int, val tileY: Int) : CollisionableRectangle(Helper.convertTileCoordToPixelCoord(tileX), Helper.convertTileCoordToPixelCoord(tileY), Const.SP_SIZE, Const.SP_SIZE) {

    enum class STATUS constructor(val tileId: Int) {
        VACIO(Const.GAME_TILES_ID.NULL.ordinal),            //0
        ROTO(Const.GAME_TILES_ID.BRICK_ROTO.ordinal),       //1
        PARTIDO(Const.GAME_TILES_ID.BRICK_PARTIDO.ordinal), //2
        ENTERO(Const.GAME_TILES_ID.BRICK_ENTERO.ordinal);   //3

    }

    lateinit var brickBreakSound: Sound
    var alive: Boolean = true
    var status = STATUS.ENTERO.ordinal  //3
    val statusValues = STATUS.values()

    fun init(brickBreakSound: Sound) {
        this.brickBreakSound = brickBreakSound
        CollisionManager.addBrick(this)
    }

    fun remove() {
        CollisionManager.removeBrick(this)
        alive = false
        TiledMapManager.remove(Helper.convertPixelCoordToTileCoord(x), Helper.convertPixelCoordToTileCoord(y))
    }

    override fun collisionWith(collisioned: CollisionableRectangle) {
        status--
        brickBreakSound.play()
        if (status == 0) {
            remove()
        } else {
            TiledMapManager.change(Helper.convertPixelCoordToTileCoord(x), Helper.convertPixelCoordToTileCoord(y), statusValues[status].tileId)
        }

    }

}
