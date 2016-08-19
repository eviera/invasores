package net.eviera.invasores.entity

import org.newdawn.slick.geom.Rectangle

abstract class CollisionableRectangle(x: Float, y: Float, width: Float, height: Float) : Rectangle(x, y, width, height) {

    enum class COLLISION_CLASS {
        ALIEN, PLAYER, BRICK, NODRIZA, SHOOT_TO_ALIEN, SHOOT_TO_PLAYER
    }

    abstract fun collisionWith(collisioned: CollisionableRectangle)
    abstract fun getType() : COLLISION_CLASS

}