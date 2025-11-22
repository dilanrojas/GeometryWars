package juego;

import juego.escenas.niveles.Nivel1;
import motor.GameLoop;

/**
 * @author Dilan Rojas
 * @date Nov 18, 2025
 * @version 1.0
 * @description description
 */

public class AppJuego {
    public void iniciar() {
        // Paso 1: Cargar los recursos del juego: imágenes, sonidos, fuentes
        if (Assets.cargados()) {
            // Iniciar música
            Assets.reproducirMusicaFondo();
            
            // Paso 2: Crear el juego
            Game game = new Game(new Nivel1());
            // Paso 3: Iniciar el bucle principal del juego
            GameLoop gameLoop = new GameLoop(game);
        }
    }

    public AppJuego() {
        iniciar();
    }
}
