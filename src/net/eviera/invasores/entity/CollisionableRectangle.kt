package net.eviera.invasores.entity

import org.newdawn.slick.geom.Rectangle

abstract class CollisionableRectangle(x: Float, y: Float, width: Float, height: Float) : Rectangle(x, y, width, height) {

    abstract fun collisionWith(collisioned: CollisionableRectangle)

}