package juego.escenas.dificultadNiveles;

import juego.escenas.Nivel;

/**
 * @author Dilan Rojas
 * @date Nov 22, 2025
 * @version 1.0
 * @description description
 */

public class NivelDificil extends Nivel {
	public NivelDificil(int enemigosPorOleada, int enemigosParaGanar) {
		super(5.0, enemigosPorOleada, enemigosParaGanar);
	}

	@Override
	public void generarEnemigos() {
		oleada(0.0, 0.4, 0.6, 300, 750);
	}
}
