object ColissionManager {

    val colissionables = mutableListOf<Colissionable>()

    fun add(colissionable: Colissionable) = colissionables.add(colissionable)
    fun remove(colissionable: Colissionable) = colissionables.remove(colissionable)

    fun checkCollision() {


    }
}