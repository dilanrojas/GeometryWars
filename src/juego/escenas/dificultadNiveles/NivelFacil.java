package juego.escenas.dificultadNiveles;

import juego.escenas.Nivel;

/**
 * @author AnaGonzalezC5F593
 * @date 21 nov 2025
 * @version 1.0
 * @description Configuración para los niveles de dificultad fácil
 */

public class NivelFacil extends Nivel {
  public NivelFacil(int enemigosPorOleada, int enemigosParaGanar) {
    super(10.0, enemigosPorOleada, enemigosParaGanar);
  }

  @Override
  public void generarEnemigos() {
    oleada(0.7, 0.3, 0.0, 600, 680);
  }
}
