package juego;

import java.awt.Cursor;
import java.awt.Graphics;

import motor.Entidad;
import motor.Lienzo;
import motor.Scene;

/**
 * @author Dilan Rojas
 * @date Nov 18, 2025
 * @version 1.0
 * @description description
 */

public class Game extends Entidad {

	private static Lienzo lienzo;

	private Scene escenaActual;

	public Game(Scene scene) {
		lienzo = new Lienzo(Config.WIDTH, Config.HEIGHT, Config.TITLE);
		escenaActual = scene;
		Scene.cambiarEscena(escenaActual);
	}

	public static void setCursor(int tipo) {
		if (lienzo != null) {
			lienzo.setCursor(Cursor.getPredefinedCursor(tipo));
		}
	}

	@Override
	public void actualizar() {
		escenaActual = Scene.getEscenaActual();
		escenaActual.actualizar();
	}

	@Override
	public void dibujar(Graphics g) {
		if (g == null) {
			lienzo.dibujar(escenaActual);
		}
	}

	@Override
	public void destruir() {
		lienzo.dispose();
	}
}
