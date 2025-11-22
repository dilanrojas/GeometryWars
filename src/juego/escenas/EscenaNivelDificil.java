package juego.escenas;

/**
 * @author Dilan Rojas
 * @date Nov 22, 2025
 * @version 1.0
 * @description description
 */

public class EscenaNivelDificil extends EscenaNivel {
	public EscenaNivelDificil(double tiempoEntreOrdas, int enemigosPorOleada, int enemigosParaGanar) {
		super(6.0, enemigosPorOleada, enemigosParaGanar);
	}

	@Override
	public void generarEnemigos() {
		oleada(0.1, 0.3, 0.6, 480, 520);
	}
}
