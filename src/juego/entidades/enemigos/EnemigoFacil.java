package juego.entidades.enemigos;

import juego.Assets;
import juego.entidades.Enemigo;
import juego.entidades.Nave;
import motor.util.Vector2D;

/**
 * @author AnaGonzalezC5F593
 * @date 21 nov 2025
 * @version 1.0
 * @description Clase EnemigoFacil es un enemigo sencillo, el cual no tiene un ataque especial ya quesu forma de ataque es colisionar con el jugador.
 */

public class EnemigoFacil extends Enemigo {
  public EnemigoFacil(Vector2D posicion, Nave target) {
    super(
        Assets.textura_enemigoFacil,
        posicion,
        target,
        15.0,  // <-- Velocidad
        1800,  // <-- Aceleracion
        0     // <-- Desviacion
    );
  }
}
