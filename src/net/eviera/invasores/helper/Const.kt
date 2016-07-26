package net.eviera.invasores.helper

object Const {

    enum class STATES {
        STARTUP,
        GAME,

    }

    enum class GAME_TILES_ID {
        NULL,
        ALIEN_1_1,
        ALIEN_1_2,
        ALIEN_2_1,
        ALIEN_2_2,
        ALIEN_3_1,
        ALIEN_3_2,              //6
        NAVE,
        NAVE_SHOOT,
        ALIEN_SHOOT,
        NAVE_SCORE,
        PLACEHOLDER_11,
        PLACEHOLDER_12,          //12
        ALIEN_EXPLOSION_1,
        ALIEN_EXPLOSION_2,
        ALIEN_EXPLOSION_3,
        PLACEHOLDER_16,
        PLACEHOLDER_17,
        PLACEHOLDER_18,          //18
        PLACEHOLDER_19,
        PLACEHOLDER_20,
        PLACEHOLDER_21,
        PLACEHOLDER_22,
        PLACEHOLDER_23,
        PLACEHOLDER_24,          //24
        PLACEHOLDER_25,
        PLACEHOLDER_26,
        PLACEHOLDER_27,
        PLACEHOLDER_28,
        PLACEHOLDER_29,
        PLACEHOLDER_30,          //30
        PLACEHOLDER_31,
        PLACEHOLDER_32,
        PLACEHOLDER_33,
        //Los ladrillos estan al final porque hay un bug que ocurre cuando un sprite tiene todos los bordes llenos (se ve un borde fantasma en los sprites colindantes)
        BRICK_ENTERO,
        BRICK_PARTIDO,
        BRICK_ROTO,             //36
    }

    enum class MOV {
        H,V
    }

    val SP_SIZE = 32f
    val FONT_SIZE = 24f
    val GAME_TILES_LAYER = 0
    val GAME_TILES_WIDTH = 36
    val GAME_TILES_HEIGHT = 18
    val GAME_WIDTH = GAME_TILES_WIDTH * 32      //px
    val GAME_HEIGHT = GAME_TILES_HEIGHT * 32    //px
    val GAME_FLOOR = GAME_HEIGHT - SP_SIZE + 5 //donde se dibuja el 'piso' del jugador, arriba del score

    val PLAYER_START_X: Float = GAME_WIDTH / 2f
    val PLAYER_START_Y: Float = GAME_HEIGHT - SP_SIZE * 2f
    val PLAYER_SPEED = 0.9f //0.9 pixels/milisegundo
    val PLAYER_SHOOT_SPEED = 0.8f
    val PLAYER_EXPLODING_TIME = 150 //en millis

    val ALIEN_SPEED_INIT = 0.09f
    val ALIEN_SPEED_INCREMENT = 0.015f
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
    val ALIEN_SHOOT_SPEED = 0.4f
    val ALIEN_EXPLODING_TIME = 150 //en millis

    val BRICK_QUANTITY = 20

    val SCORE_ALIEN_HIT = 100

    val FLASH_TIMEOUT = 1 * 1000 //En segundos, cuanto se queda el mensaje flash activo
}