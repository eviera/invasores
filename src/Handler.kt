import ID.Enemy
import java.awt.Graphics
import java.util.*

/**
 * Maneja todos los objetos de la pantalla <br>
 * El Player es un caso especial que se puede obtener por separado
 */
class Handler(var player: Player) {

    val objs = LinkedList<GameObject>()

    init  {
        //En la construccion, agrego el player a la lista de objetos
        add(player)
    }

    fun tick() {
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
