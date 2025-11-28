package juego.entidades.enemigos;

import juego.Assets;
import juego.entidades.Enemigo;
import juego.entidades.Nave;
import motor.util.Vector2D;

/**
 * @author AnaGonzalezC5F593
 * @date 21 nov 2025
 * @version 1.0
 * @description Enemigo de dificiltad dificil
 */


public class EnemigoDificil extends Enemigo {

	  public EnemigoDificil(Vector2D posicion, Nave target) {
      super(
          Assets.textura_enemigoDificil,
          posicion,
          target,
          40.0,  // <-- Velocidad
          4400,  // <-- Aceleracion
          40     // <-- Desviacion
      );
    }
}

