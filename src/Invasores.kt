import java.awt.Canvas

/*
https://www.youtube.com/watch?v=1gir2R7G9ws

http://www.gamefromscratch.com/post/2015/12/02/Cheat-sheet-for-Learning-the-Kotlin-Language.aspx
https://kotlinlang.org/docs/reference/basic-syntax.html
http://petersommerhoff.com/dev/kotlin/kotlin-for-java-devs/

 */

class Invasores() : Canvas(), Runnable {

    val WIDTH = 640
    val HEIGHT = WIDTH / 12 * 9
    val thread: Thread
    var running = false

    init {
        Window(WIDTH, HEIGHT, "Sasa", this)
        thread = Thread(this)
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
        val amountOfTicks = 60.0
        val ns = 1000000000 / amountOfTicks
        var delta = 0.0
        var timer = System.currentTimeMillis()
        var frames = 0
        while(running) {
            val now = System.nanoTime()
            delta += (now - lastTime) / ns
            lastTime = now
            while (delta  >= 1) {
                tick()
                delta--
            }
            if(running) {
                render()
            }
            frames++
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000
                System.out.println("FPS: ${frames}")
                frames = 0
            }
        }
        stop()

    }

    private fun render() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun tick() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}


fun main(args: Array<String>) {
    Invasores()
}
