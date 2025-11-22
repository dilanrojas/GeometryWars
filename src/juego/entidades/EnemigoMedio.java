package juego.entidades;

import juego.Assets;
import motor.util.Vector2D;

/**
 * @author AnaGonzalezC5F593
 * @date 21 nov 2025
 * @version 1.0
 * @description Enemigo de dificiltad media
 */

public class EnemigoMedio extends Enemigo {
	public EnemigoMedio(Vector2D posicion, Nave target) {
    super(
        Assets.textura_enemigoMedio,
        posicion,
        target,
        20.0,  // <-- Velocidad
        2400,  // <-- Aceleracion
        20     // <-- Desviacion
    );
  }
}
