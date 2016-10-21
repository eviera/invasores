#ToDo
##Prio.1
- ~~Muerte player~~
- ~~Alinear correctamente texto del footer~~
- ~~Muerte cuando los aliens llegan abajo~~
  - ~~Armar un evento de GameOver que se dispare cuando un alien alive llega abajo~~
    - ~~Este evento GameOver tambien se deberia disparar cuando el player se queda sin vidas (en lugar del boolean gameover)~~
  - ~~Los aliens tienen que romper los ladrillos~~
  - ~~Cuando un alien colisiona con el player, el player muere~~


##Prio.2
- Nave nodriza
- ~~Cambiar los aliens 2 y 3 por otros dibujos~~
- ~~Pantalla intro~~
- Pantalla Game Over
- HighScore
  - ~~Quitar la palabra High Score del back de StartUpState y ponerla como font.~~
    - Si estoy en HighScoreState poner "NEW HIGH SCORE" (color cyclye), sino "HIGH SCORES" (white)
  - Poder dibujar 2 o 3 scores dependiendo si esta en HighScoreState o no
  - Aceptar entrada de texto para el highscore con color cycle
- De la mecanica del juego
  - ~~Incrementar la velocidad de los aliens cuando bajan~~
  - Incrementar el rate of fire
  - ~~Contador de vidas~~
- ~~Tiros mas lentos-~~
- ~~Explosion alien~~
- ~~Explosion nave~~
- ~~Mas bricks~~
- ~~Mas aliens mas compacto el espacio~~
- ~~Control de volumen~~
  - ~~Visualizar el volumen cuando cambia (en porcentaje)~~
- Persistir configuracion (volumen, highscore, etc)
- ~~Scores distintos para cada tipo de alien~~
- Quitar magic numbers
- ~~Pantalla de pausa~~

#Bugs
- ~~Debajo de los aliens al principio aparecen unas lineas (es un bug cuando un tile esta todo lleno, como los bricks)~~
- ~~Parece que los aliens no disparan en el borde~~
- ~~Cuando se matan aliens de un costado, los que quedan no llegan al borde de la pantalla (es como si los que se mataron siguieran ahi)~~
- ~~El minimo en getAlienMinMaxX queda muy pegado a la izquierda de la pantalla~~
- Si queda un solo alien, no dispara

#NiceToHave
- Musica intro
- Distintos tipos de tiros y score segun alien
- Arte fijo o movil dentro del juego, de fondo
- Tiros mas 'finos'. Que el ancho del tiro sea de 8 pixels y no de 32