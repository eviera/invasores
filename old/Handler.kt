import ID.Enemy
import java.awt.Graphics
import java.util.*

/**
 * Maneja todos los objetos de la pantalla <br>
 * El Player es un caso especial que se puede obtener por separado
 */
class Handler(var player: Player) {

    val objs = LinkedList<GameObject>()
    var keyLeft: Boolean = false;
    var keyRight: Boolean = false;
    var keySpace: Boolean = false;

    init {
        //En la construccion, agrego el player a la lista de objetos
        add(player)
    }

    fun tick() {
        if (keyLeft && !keyRight) {
            player.velX = -5
        }

        if (keyRight && !keyLeft) {
            player.velX = 5
        }

        if (!keyLeft && !keyRight) {
            player.velX = 0
        }

        for (obj in objs) {
            obj.tick()
        }

    }

    fun render(g: Graphics) {
        for (obj in objs) {
            obj.render(g)
        }
    }

    fun add(obj: GameObject) = objs.add(obj)
    fun remove(obj: GameObject) = objs.remove(obj)

}
