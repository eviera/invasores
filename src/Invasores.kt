import org.newdawn.slick.*

class Invasores : BasicGame("Invasores") {

    val player = Player()
    val aliens = arrayOfNulls<Alien>(24)

    override fun init(gc: GameContainer?) {
        /*
         En este metodo se cargan las imagenes
         */
        InitHelper.init(gc, player, aliens)
        player.init(gc)
    }

    override fun update(gc: GameContainer?, delta: Int) {
        val input = gc?.input
        if (input?.isKeyDown(Input.KEY_LEFT) as Boolean) { player.setX(player.getX() - 0.5f * delta) }
        if (input?.isKeyDown(Input.KEY_RIGHT) as Boolean) { player.setX(player.getX() + 0.5f * delta) }

    }

    override fun render(gc: GameContainer?, g: Graphics?) {
        player.render(gc, g)
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