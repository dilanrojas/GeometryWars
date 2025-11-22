package juego.escenas;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JOptionPane;

import juego.Assets;
import juego.entidades.AdministradorDeColisiones;
import juego.entidades.Controles;
import juego.entidades.Nave;
import juego.entidades.enemigos.EnemigoFacil;
import juego.entidades.enemigos.EnemigoMedio;
import motor.Scene;
import motor.entidades.ListaEntidades;
import motor.input.Key;
import motor.util.Vector2D;

/**
 * @author AnaGonzalezC5F593
 * @date 21 nov 2025
 * @version 1.0
 * @description Plantilla para los niveles
 */

public abstract class Nivel extends Scene {
	private Nave jugador;
	private ListaEntidades balas;
	private ListaEntidades listaEnemigos;
	private AdministradorDeColisiones administrador;
	private boolean primeraOrda = true;
	private double tiempoOrdaInicial = 1.0;

	public double tiempoEntreOrdas;
	private int enemigosPorOleada;
	private int enemigosParaGanar;
	private int enemigosMuertos = 0;

	private double contador = 0;

	public Nivel(double tiempoEntreOrdas, int enemigosPorOleada, int enemigosParaGanar) {
		this.listaEnemigos = new ListaEntidades();
		this.administrador = new AdministradorDeColisiones();
		Controles controles = new Controles(Key.W, Key.A, Key.D, Key.SPACE);
		this.jugador = new Nave(Assets.textura_nave, new Vector2D(200, 200), controles);
		this.balas = new ListaEntidades();

		this.tiempoEntreOrdas = tiempoEntreOrdas;
		this.enemigosPorOleada = enemigosPorOleada;
		this.enemigosParaGanar = enemigosParaGanar;
	}

	@Override
	public final void actualizar() {
		if (jugador != null)
			jugador.actualizar();

		balas.actualizar();
		disparar();
		listaEnemigos.actualizar();

		contador += motor.GameLoop.deltaTimeSeconds;
		if (primeraOrda && contador > tiempoOrdaInicial) {
			generarEnemigos();
			contador = 0;
			primeraOrda = false;

		} else if (!primeraOrda && contador > tiempoEntreOrdas) {
			generarEnemigos();
			contador = 0;

		}

		controlarEnemigos();

		destruir();
	}

	@Override
	public void dibujar(Graphics g) {
		balas.dibujar(g);
		listaEnemigos.dibujar(g);
		if (jugador != null)
			jugador.dibujar(g);
	}

	@Override
	public void destruir() {
		if (jugador != null && !jugador.estaViva())
			jugador = null;

		balas.destruir();
		listaEnemigos.destruir();
	}

	private void disparar() {
		if (jugador != null && jugador.quiereDisparar()) {
			balas.add(jugador.disparar());
			Assets.reproducirDisparo();
		}
	}

	private void controlarEnemigos() {
		if (administrador.detectarColisionesConBalas(listaEnemigos, balas) == 1) {

			enemigosMuertos++;
			Assets.reproducirExplosion();

			if (enemigosMuertos == this.enemigosParaGanar) {
				JOptionPane.showMessageDialog(null, "Ganaste");
				destruir();
			}
		}

		if (administrador.detectarColisionesConNave(listaEnemigos, jugador)) {
			JOptionPane.showMessageDialog(null, "Perdiste");
			destruir();
		}
	}

	private Vector2D generarSpawnAlejado(
		Vector2D centro,
		double min,
		double max
	) {
		Random random = new Random();
		double angulo = random.nextDouble() * Math.PI * 2;
		double distancia = min + (random.nextDouble() * (max - min));
		double x = centro.getX() + Math.cos(angulo) * distancia;
		double y = centro.getY() + Math.sin(angulo) * distancia;
		return new Vector2D(x, y);
	}

	protected void oleada (
		double porcentajeFaciles,
		double porcentajeMedios,
		double porcentajeDificiles,
		int distanciaMin,
		int distanciaMax
	) {
		int cantidadFaciles = (int) (enemigosPorOleada * porcentajeFaciles);
		int cantidadMedios  = enemigosPorOleada - cantidadFaciles;

		Vector2D posJugador = jugador.getTransform().getPosicion();

		// === ENEMIGOS FÁCILES ===
		for (int i = 0; i < cantidadFaciles; i++) {
			Vector2D spawn = generarSpawnAlejado(posJugador, distanciaMin, distanciaMax);
			listaEnemigos.add(new EnemigoFacil(spawn, jugador));
		}

		// === ENEMIGOS MEDIOS ===
		for (int i = 0; i < cantidadMedios; i++) {
			Vector2D spawn = generarSpawnAlejado(posJugador, distanciaMin, distanciaMax);
			listaEnemigos.add(new EnemigoMedio(spawn, jugador));
		}
	}

	// Abstractos
	public abstract void generarEnemigos();
}
