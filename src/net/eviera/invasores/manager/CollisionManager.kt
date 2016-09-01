package net.eviera.invasores.manager

import net.eviera.invasores.entity.CollisionableRectangle
import net.eviera.invasores.entity.CollisionableRectangle.COLLISION_CLASS

object CollisionManager {


    val collisionables = mapOf(
            COLLISION_CLASS.ALIEN to mutableListOf<CollisionableRectangle>(),
            COLLISION_CLASS.PLAYER to mutableListOf<CollisionableRectangle>(),
            COLLISION_CLASS.BRICK to mutableListOf<CollisionableRectangle>(),
            COLLISION_CLASS.NODRIZA to mutableListOf<CollisionableRectangle>(),
            COLLISION_CLASS.SHOOT_TO_ALIEN to mutableListOf<CollisionableRectangle>(),
            COLLISION_CLASS.SHOOT_TO_PLAYER to mutableListOf<CollisionableRectangle>()
            )

    fun add(collisionable: CollisionableRectangle) = collisionables[collisionable.getType()]?.add(collisionable)
    fun remove(collisionable: CollisionableRectangle) = collisionables[collisionable.getType()]?.remove(collisionable)

    fun checkCollision() {
        val pairCollisions = mutableListOf<Pair<CollisionableRectangle, CollisionableRectangle>>()

        //Tiros desde player a alien (y choques en ladrillos)
        if (collisionables[COLLISION_CLASS.SHOOT_TO_ALIEN]!!.isNotEmpty()) {
            for(shoot in collisionables[COLLISION_CLASS.SHOOT_TO_ALIEN]!!) {
                for (alien in collisionables[COLLISION_CLASS.ALIEN]!!) {
                    if (shoot.intersects(alien)) {
                        pairCollisions.add(Pair(shoot, alien))
                    }
                }
                for (brick in collisionables[COLLISION_CLASS.BRICK]!!) {
                    if (shoot.intersects(brick)) {
                        pairCollisions.add(Pair(shoot, brick))
                    }
                }
            }

        }

        //Tiros desde alien a player (y choques en ladrillos)
        if (collisionables[COLLISION_CLASS.SHOOT_TO_PLAYER]!!.isNotEmpty()) {
            for(shoot in collisionables[COLLISION_CLASS.SHOOT_TO_PLAYER]!!) {
                for (player in collisionables[COLLISION_CLASS.PLAYER]!!) {
                    if (shoot.intersects(player)) {
                        pairCollisions.add(Pair(shoot, player))
                    }
                }
                for (brick in collisionables[COLLISION_CLASS.BRICK]!!) {
                    if (shoot.intersects(brick)) {
                        pairCollisions.add(Pair(shoot, brick))
                    }
                }
            }
        }

        //Choque de aliens contra los ladrillos o el player
        if (collisionables[COLLISION_CLASS.ALIEN]!!.isNotEmpty()) {
            for(alien in collisionables[COLLISION_CLASS.ALIEN]!!) {
                for (brick in collisionables[COLLISION_CLASS.BRICK]!!) {
                    if (alien.intersects(brick)) {
                        pairCollisions.add(Pair(alien, brick))
                    }
                }
                for (player in collisionables[COLLISION_CLASS.PLAYER]!!) {
                    if (alien.intersects(player)) {
                        pairCollisions.add(Pair(alien, player))
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