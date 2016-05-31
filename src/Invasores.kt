import org.newdawn.slick.*

class Invasores : BasicGame("Invasores") {

    val player = Player()
    val aliens = arrayOfNulls<Alien>(24)

    override fun init(gc: GameContainer?) {
        if (gc == null ) throw RuntimeException("Error de gc null")
        Manager.init(gc, player, aliens)
    }

    override fun update(gc: GameContainer?, delta: Int) {
        if (gc == null ) throw RuntimeException("Error de gc null")

        val input = gc.input
        if (input?.isKeyDown(Input.KEY_LEFT) as Boolean) { player.setX(player.getX() - 0.5f * delta) }
        if (input?.isKeyDown(Input.KEY_RIGHT) as Boolean) { player.setX(player.getX() + 0.5f * delta) }

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