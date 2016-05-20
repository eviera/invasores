import java.awt.Canvas
import java.awt.Color
import java.awt.EventQueue

/*
https://www.youtube.com/watch?v=1gir2R7G9ws

http://www.gamefromscratch.com/post/2015/12/02/Cheat-sheet-for-Learning-the-Kotlin-Language.aspx
https://kotlinlang.org/docs/reference/basic-syntax.html
http://petersommerhoff.com/dev/kotlin/kotlin-for-java-devs/

 */

class Invasores() : Canvas(), Runnable {

    val WIDTH = 640
    val HEIGHT = WIDTH / 12 * 9
    val thread: Thread = Thread(this)
    var running = false
    val handler: Handler

    init {
        Window(WIDTH, HEIGHT, "Sasa", this)
        handler = Handler(Player(WIDTH / 2 - 32, HEIGHT / 2 - 32))
        addKeyListener(KeyInput(handler))
        start()
    }

    @Synchronized fun start() {
        thread.start()
        running = true
    }

    @Synchronized fun stop() {
        thread.join()
        running = false
    }


    override fun run() {
        var lastTime = System.nanoTime()
        val targetFPS = 60.0
        val optimalTime = 1000000000 / targetFPS
        //Delta determina si el loop se esta adelantando mucho (>=1). Si es asi, hace un while de espera
        var delta = 0.0
        var timer = System.currentTimeMillis()
        var frames = 0

        while (running) {
            /*
                Determina cuanto paso desde el ultimo update para calcular cuanto se deberian
                mover los objetos en este loop
             */
            val now = System.nanoTime()
            delta += (now - lastTime) / optimalTime
            lastTime = now
            //Hace updates mientras espera a que el delta llegue a 1 (que es el momento optimo para hacer el render)
            while (delta >= 1) {
                tick()
                delta--
            }
            if (running) {
                render()
            }
            frames++

            //Reporta una vez por segundo los FPS
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000
                //println("FPS: ${frames}")
                frames = 0
            }
        }

        stop()

    }

    private fun render() {
        val bs = this.bufferStrategy
        if (bs == null) {
            createBufferStrategy(3)
            return
        }

        val g = bs.drawGraphics
        g.color = Color.BLACK
        g.fillRect(0, 0, WIDTH, HEIGHT)

        handler.render(g)

        g.dispose()
        bs.show()

    }


    private fun tick() {
        handler.tick()
    }


}


fun main(args: Array<String>) {
    EventQueue.invokeLater { run { Invasores() } }
}
