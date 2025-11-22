package juego.escenas;

/**
 * @author Dilan Rojas
 * @date Nov 22, 2025
 * @version 1.0
 * @description description
 */

public class EscenaNivelMedio extends EscenaNivel {
	public EscenaNivelMedio(double tiempoEntreOrdas, int enemigosPorOleada, int enemigosParaGanar) {
		super(8.0, enemigosPorOleada, enemigosParaGanar);
	}

	@Override
	public void generarEnemigos() {
		oleada(0.3, 0.7, 0.1, 500, 580);
	}
}
