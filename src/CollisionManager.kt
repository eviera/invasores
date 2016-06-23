object CollisionManager {

    val alienCollisionables = mutableListOf<CollisionableRectangle>()
    val playerCollisionables = mutableListOf<CollisionableRectangle>()
    val shootCollisionables = mutableListOf<CollisionableRectangle>()

    fun addAlien(colissionable: CollisionableRectangle) = alienCollisionables.add(colissionable)
    fun removeAlien(colissionable: CollisionableRectangle) = alienCollisionables.remove(colissionable)

    fun addPlayer(colissionable: CollisionableRectangle) = playerCollisionables.add(colissionable)
    fun removePlayer(colissionable: CollisionableRectangle) = playerCollisionables.remove(colissionable)

    fun addShoot(colissionable: CollisionableRectangle) = shootCollisionables.add(colissionable)
    fun removeShoot(colissionable: CollisionableRectangle) = shootCollisionables.remove(colissionable)


    fun checkCollision() {
        if (shootCollisionables.isNotEmpty()) {
            for(shoot in shootCollisionables) {
                for (alien in alienCollisionables) {
                    if (shoot.intersects(alien)) {
                        println("${alien} tocado")
                    }
                }
            }
        }

    }

}