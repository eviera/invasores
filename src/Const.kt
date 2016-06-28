object Const {
    val GAME_WIDTH = 1200
    val GAME_HEIGHT = 600
    val SP_SIZE = 32f

    val PLAYER_START_X: Float = GAME_WIDTH / 2f
    val PLAYER_START_Y: Float = GAME_HEIGHT - SP_SIZE * 2f
    val PLAYER_SPEED = 0.9f //0.9 pixels/milisegundo
    val PLAYER_SHOOT_SPEED = 2f

    val ALIEN_START_SPEED = 0.1f
    val ALIEN_ROWS = 3
    val ALIEN_COLS = 8
    val ALIEN_X_SHIFT = 2f //cuanto esta desplazada una fila de aliens de la otra en la coord x
    val ALIEN_GAP_FROM_BORDERS = SP_SIZE / 2f //cuanto se separan de los bordes los aliens cuando vuelan (para no tocarlos)
    val ALIEN_START_X = ALIEN_GAP_FROM_BORDERS //donde se empiezan a dibujar los aliens (justo el monto que se separa del borde)
    val ALIEN_END_X = GAME_WIDTH - (ALIEN_GAP_FROM_BORDERS + Helper.getAlienColPos(ALIEN_X_SHIFT, ALIEN_COLS - 1) + ALIEN_GAP_FROM_BORDERS) //Cuanto se pueden desplazar los aliens a la derecha es el ancho de la pantalla menos ancho de todos los aliens dibujados menos los dos bordes
    val ALIEN_START_Y = SP_SIZE / 3f

    val BRICK_QUANTITY = 20

    enum class MOV {
        H,V
    }
}