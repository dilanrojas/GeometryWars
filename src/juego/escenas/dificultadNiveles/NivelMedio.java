package juego.escenas.dificultadNiveles;

import juego.escenas.Nivel;

/**
 * @author Dilan Rojas
 * @date Nov 22, 2025
 * @version 1.0
 * @description description
 */

public class NivelMedio extends Nivel {
	public NivelMedio(int enemigosPorOleada, int enemigosParaGanar) {
		super(10.0, enemigosPorOleada, enemigosParaGanar);
	}

	@Override
	public void generarEnemigos() {
		oleada(0.2, 0.6, 0.2, 500, 580);
	}
}
