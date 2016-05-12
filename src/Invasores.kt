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
    var thread: Thread? = null
    var running = false

    init {
        Window(WIDTH, HEIGHT, "Sasa", this)
    }

    @Synchronized fun start() {
        thread = Thread(this)
        (thread as Thread).start()
    }

    override fun run() {
        throw UnsupportedOperationException()
    }


}


fun main(args: Array<String>) {
    Invasores()
}
