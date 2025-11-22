package juego.escenas;

/**
 * @author AnaGonzalezC5F593
 * @date 21 nov 2025
 * @version 1.0
 * @description  contiene configuración base para cualquier nivel de dificultad fácil.
 */

public class EscenaNivelFacil extends EscenaNivel {
  public EscenaNivelFacil(int enemigosPorOleada, int enemigosParaGanar) {
    super(10.0, enemigosPorOleada, enemigosParaGanar);
  }

  @Override
  public void generarEnemigos() {
    oleada(0.7, 0.3, 0.0, 600, 680);
  }
}
