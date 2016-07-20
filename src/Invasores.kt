import net.eviera.invasores.helper.Const
import net.eviera.invasores.state.GameState
import org.newdawn.slick.AppGameContainer
import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.StateBasedGame

class Invasores : StateBasedGame("Invasores") {

    override fun initStatesList(container: GameContainer?) {
        addState(GameState())
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