import java.awt.Graphics

abstract class GameObject(var x: Int, var y: Int, var id: ID) {
    var velX: Int = 0
    var velY: Int = 0

    abstract fun tick()
    abstract fun render(g: Graphics)

}