import org.newdawn.slick.*
import Const.PLAYER_SPEED
import Const.GAME_WIDTH
import Const.SP_SIZE

class Invasores : BasicGame("Invasores") {

    val player = Player()
    val aliens = arrayOfNulls<Alien>(24)

    override fun init(gc: GameContainer?) {
        if (gc == null ) throw RuntimeException("Error de gc null")
        Manager.init(gc, player, aliens)
    }

    override fun update(gc: GameContainer?, delta: Int) {
        if (gc == null ) throw RuntimeException("Error de gc null")

        /*
            delta tiene la cantidad de milisegundos que pasaron desde la iteracion anterior
            playerDisplacement es la distancia en pixels que se tiene que mover. Por ejemplo si pasaron 8 milisegundos desde el ultimo frame
            y PLAYER_SPEED (en pixels/milisegundo) es 0.5, entonces se va a mover 8 * 0.5 = 4 pixels en este frame
         */
        val playerDisplacement = PLAYER_SPEED * delta


        //Controlo que no se pase de los bordes snappeando a 0 o a GAME_WIDTH - SP_SIZE (ancho del juego - tamaño del sprite)
        val input = gc.input
        if (input?.isKeyDown(Input.KEY_LEFT) as Boolean) {
            val px = player.getX()
            var pxDest = px - playerDisplacement
            if (pxDest < 0) pxDest = 0f
            player.setX(pxDest)
        }
        if (input?.isKeyDown(Input.KEY_RIGHT) as Boolean) {
            val px = player.getX()
            var pxDest = px + playerDisplacement
            if (pxDest > GAME_WIDTH - SP_SIZE) pxDest = GAME_WIDTH - SP_SIZE
            player.setX(pxDest)
        }

    }

    override fun render(gc: GameContainer?, g: Graphics?) {
        if (gc == null || g == null) throw RuntimeException("Error de gc o g null")
        Manager.render(gc, g, player, aliens)
    }

}


fun main(args: Array<String>) {
    val appgc = AppGameContainer(Invasores())
    appgc.setDisplayMode(Const.GAME_WIDTH, Const.GAME_HEIGHT, false)
    appgc.setTargetFrameRate(60)
    appgc.setVSync(true)
    appgc.setShowFPS(false)
    appgc.start()
}