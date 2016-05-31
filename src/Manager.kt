import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image

object Manager {


    fun init(gc: GameContainer, player: Player, aliens: Array<Alien?>) {
        //Cargo la nave del jugador
        val nave = Image("/resources/images/nave_64.png")
        player.init(nave)

        //Cargo los aliens
        val alien1 = Image("/resources/images/alien1_64.png")
        val alien2 = Image("/resources/images/alien2_64.png")
        val alien3 = Image("/resources/images/alien3_64.png")

        //TODO: hacer las imagenes. Crear los aliens con imagenes random y cargarlos en 3 filas

    }

    fun render(gc: GameContainer, g: Graphics, player: Player, aliens: Array<Alien?>) {
        player.render(gc, g)
    }


}