import java.awt.Canvas
import java.awt.Dimension
import javax.swing.JFrame

open class Window(width: Int, height: Int, title: String, invasores: Invasores) : Canvas() {
    init {
        val frame = JFrame(title)
        frame.preferredSize = Dimension(width, height)
        frame.maximumSize = frame.preferredSize
        frame.minimumSize = frame.preferredSize

        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isResizable = false
        frame.setLocationRelativeTo(null)
        frame.add(invasores)
        frame.isVisible = true
    }


}