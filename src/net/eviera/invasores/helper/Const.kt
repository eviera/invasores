package net.eviera.invasores.helper

object Const {

    enum class GAME_TILES_ID {
        NULL,
        ALIEN_1_1,
        ALIEN_1_2,
        ALIEN_2_1,
        ALIEN_2_2,
        ALIEN_3_1,
        ALIEN_3_2,
        NAVE,
        NAVE_SHOOT,
        ALIEN_SHOOT,
        BRICK_ENTERO,
        BRICK_PARTIDO,
        BRICK_ROTO,
    }

    enum class MOV {
        H,V
    }

    val GAME_TILES_LAYER = 0
    val GAME_TILES_WIDTH = 36
    val GAME_TILES_HEIGHT = 18
    val GAME_WIDTH = GAME_TILES_WIDTH * 32      //px
    val GAME_HEIGHT = GAME_TILES_HEIGHT * 32    //px
    val SP_SIZE = 32f

    val PLAYER_START_X: Float = GAME_WIDTH / 2f
    val PLAYER_START_Y: Float = GAME_HEIGHT - SP_SIZE * 2f
    val PLAYER_SPEED = 0.9f //0.9 pixels/milisegundo
    val PLAYER_SHOOT_SPEED = 0.8f

    val ALIEN_START_SPEED = 0.1f
    val ALIEN_ROWS = 3
    val ALIEN_COLS = 12
    val ALIEN_X_SHIFT = 2f //cuanto esta desplazada una fila de aliens de la otra en la coord x
    val ALIEN_GAP_X = 1.8f //Distancia horizontal entre aliens
    val ALIEN_GAP_Y = 1.5f //Distancia vertical entre aliens
    val ALIEN_GAP_FROM_BORDERS = SP_SIZE / 2f //cuanto se separan de los bordes los aliens cuando vuelan (para no tocarlos)
    val ALIEN_START_X = ALIEN_GAP_FROM_BORDERS //donde se empiezan a dibujar los aliens (justo el monto que se separa del borde)
    val ALIEN_END_X = GAME_WIDTH - (ALIEN_GAP_FROM_BORDERS + Helper.getAlienColPos(ALIEN_X_SHIFT, ALIEN_COLS - 1) + ALIEN_GAP_FROM_BORDERS) //Cuanto se pueden desplazar los aliens a la derecha es el ancho de la pantalla menos ancho de todos los aliens dibujados menos los dos bordes
    val ALIEN_START_Y = SP_SIZE / 3f
    val ALIEN_START_FIRE_RATE_MILLIS = 500 //cada estos milisegundos los aliens pueden disparar
    val ALIEN_SHOOT_SPEED = 1f

    val BRICK_QUANTITY = 20

    val SCORE_ALIEN_HIT = 100

}