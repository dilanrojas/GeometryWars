package juego.escenas.dificultadNiveles;

import juego.escenas.Nivel;

/**
 * @author Dilan Rojas
 * @date Nov 22, 2025
 * @version 1.0
 * @description description
 */

public class NivelMedio extends Nivel {
	public NivelMedio(double tiempoEntreOrdas, int enemigosPorOleada, int enemigosParaGanar) {
		super(8.0, enemigosPorOleada, enemigosParaGanar);
	}

	@Override
	public void generarEnemigos() {
		oleada(0.3, 0.7, 0.1, 500, 580);
	}
}
