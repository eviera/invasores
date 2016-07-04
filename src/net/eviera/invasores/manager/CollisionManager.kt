package net.eviera.invasores.manager

import net.eviera.invasores.entity.CollisionableRectangle

object CollisionManager {

    val alienCollisionables = mutableListOf<CollisionableRectangle>()
    val playerCollisionables = mutableListOf<CollisionableRectangle>()
    val shootToAlienCollisionables = mutableListOf<CollisionableRectangle>()
    val shootToPlayerCollisionables = mutableListOf<CollisionableRectangle>()
    val brickCollisionables = mutableListOf<CollisionableRectangle>()

    fun addAlien(colissionable: CollisionableRectangle) = alienCollisionables.add(colissionable)
    fun removeAlien(colissionable: CollisionableRectangle) = alienCollisionables.remove(colissionable)

    fun addPlayer(colissionable: CollisionableRectangle) = playerCollisionables.add(colissionable)
    fun removePlayer(colissionable: CollisionableRectangle) = playerCollisionables.remove(colissionable)

    fun addShootToAlien(colissionable: CollisionableRectangle) = shootToAlienCollisionables.add(colissionable)
    fun removeShootToAlien(colissionable: CollisionableRectangle) = shootToAlienCollisionables.remove(colissionable)

    fun addShootToPlayer(colissionable: CollisionableRectangle) = shootToPlayerCollisionables.add(colissionable)
    fun removeShootToPlayer(colissionable: CollisionableRectangle) = shootToPlayerCollisionables.remove(colissionable)

    fun addBrick(colissionable: CollisionableRectangle) = brickCollisionables.add(colissionable)
    fun removeBrick(colissionable: CollisionableRectangle) = brickCollisionables.remove(colissionable)

    fun checkCollision() {
        val pairCollisions = mutableListOf<Pair<CollisionableRectangle, CollisionableRectangle>>()

        if (shootToAlienCollisionables.isNotEmpty()) {
            for(shoot in shootToAlienCollisionables) {
                for (alien in alienCollisionables) {
                    if (shoot.intersects(alien)) {
                        pairCollisions.add(Pair(shoot, alien))
                    }
                }
                for (brick in brickCollisionables) {
                    if (shoot.intersects(brick)) {
                        pairCollisions.add(Pair(shoot, brick))
                    }
                }
            }

        }

        if (shootToPlayerCollisionables.isNotEmpty()) {
            for(shoot in shootToPlayerCollisionables) {
                for (player in playerCollisionables) {
                    if (shoot.intersects(player)) {
                        pairCollisions.add(Pair(shoot, player))
                    }
                }
                for (brick in brickCollisionables) {
                    if (shoot.intersects(brick)) {
                        pairCollisions.add(Pair(shoot, brick))
                    }
                }
            }
        }

        //Si hay colisiones, aviso a los colisionados
        if (pairCollisions.isNotEmpty()) {
            for (pair in pairCollisions) {
                pair.first.collisionWith(pair.second)
                pair.second.collisionWith(pair.first)
            }
        }

    }

}