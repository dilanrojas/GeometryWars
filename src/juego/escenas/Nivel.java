package juego.escenas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JOptionPane;

import controlador.NivelesControlador;
import juego.Assets;
import juego.Config;
import juego.entidades.AdministradorDeColisiones;
import juego.entidades.Controles;
import juego.entidades.Nave;
import juego.entidades.enemigos.EnemigoDificil;
import juego.entidades.enemigos.EnemigoFacil;
import juego.entidades.enemigos.EnemigoMedio;
import motor.Scene;
import motor.entidades.ListaEntidades;
import motor.entidades.Sprite;
import motor.input.Key;
import motor.util.Vector2D;

/**
 * @author AnaGonzalezC5F593
 * @date 21 nov 2025
 * @version 1.0
 * @description Plantilla para los niveles
 */

public abstract class Nivel extends Scene {
	// Componentes
	private NivelesControlador controlador;
	
	private Nave jugador;
	private ListaEntidades balas;
	private ListaEntidades listaEnemigos;
	private AdministradorDeColisiones administrador;
	private boolean primeraOrda = true;
	private double tiempoOrdaInicial = 1.0;
	private int nivelActual;

	public double tiempoEntreOrdas;
	private int enemigosPorOleada;
	private int enemigosParaGanar;
	private int enemigosMuertos = 0;

	private double contador = 0;
	private double tiempoJugado = 0;
	
	private Sprite fondo;
	
	private Vector2D centroPantalla = new Vector2D((Config.WIDTH / 2) - (Assets.textura_nave.getWidth() / 2), (Config.HEIGHT / 2) - (Assets.textura_nave.getHeight() / 2));

	public Nivel(double tiempoEntreOrdas, int enemigosPorOleada, int enemigosParaGanar) {
		this.listaEnemigos = new ListaEntidades();
		this.administrador = new AdministradorDeColisiones();
		Controles controles = new Controles(Key.W, Key.A, Key.D, Key.SPACE);
		this.jugador = new Nave(Assets.textura_nave, centroPantalla, controles);
		this.balas = new ListaEntidades();
		this.fondo = new Sprite(Assets.textura_fondo, Vector2D.ZERO);
		
		this.tiempoEntreOrdas = tiempoEntreOrdas;
		this.enemigosPorOleada = (int) Math.round(enemigosPorOleada * Config.DIFICULTAD);
		this.enemigosParaGanar = (int) Math.round(enemigosParaGanar * Config.DIFICULTAD);
	}

	@Override
	public final void actualizar() {
		if (jugador != null) jugador.actualizar();
		disparar();

		balas.actualizar();
		listaEnemigos.actualizar();

		controlarOleadas();
		controlarEnemigos();
		destruir();
		
		tiempoJugado += motor.GameLoop.deltaTimeSeconds;
	}

	@Override
	public void dibujar(Graphics g) {
		fondo.dibujar(g);		
		if (jugador != null) jugador.dibujar(g);
		if (balas != null) balas.dibujar(g);
		if (listaEnemigos != null) listaEnemigos.dibujar(g);
		g.setFont(new Font("Arial", Font.BOLD, 26));
		g.setColor(Color.WHITE);
		if (jugador != null) g.drawString("Vidas: " + jugador.getVidas(), 20, 40);
		g.drawString("Puntos: " + enemigosMuertos, 20, 80);
		g.drawString("Objetivo: " + enemigosParaGanar, 20, 120);
	}

	@Override
	public void destruir() {
		if (jugador != null && !jugador.estaViva()) jugador = null;
		if (balas !=null) balas.destruir();
		if (listaEnemigos != null) listaEnemigos.destruir();
	}

	// Disparos del jugador
	private void disparar() {
		if (jugador != null && jugador.quiereDisparar()) {
			balas.add(jugador.disparar());
			Assets.reproducirDisparo();
		}
	}
	
	// Generación de enemigos
	private void controlarOleadas() {
		contador += motor.GameLoop.deltaTimeSeconds;
		if (primeraOrda && contador > tiempoOrdaInicial) {
			generarEnemigos();
			contador = 0;
			primeraOrda = false;

		} else if (!primeraOrda && contador > tiempoEntreOrdas) {
			generarEnemigos();
			contador = 0;

		}
	}

	// Colisiones
	private void controlarEnemigos() {
		// Balas --> Enemigos
		if (administrador.detectarColisionesConBalas(listaEnemigos, balas) == 1) {
			enemigosMuertos++;
			Assets.reproducirExplosion();

			if (enemigosMuertos == this.enemigosParaGanar) {
				JOptionPane.showMessageDialog(null, "¡Nivel completado!");
				completado(true );
			}
		}

		// Jugador --> Enemigos
		if (administrador.detectarColisionesConNave(listaEnemigos, jugador)) {
			if (jugador.getVidas() > 0) {
				reaparecer();
				return;
			} else {
				JOptionPane.showMessageDialog(null, "¡Te quedaste sin vidas!");
				completado(false);				
			}
		}
	}

	// Controlar reaparición de la nave al colisionar
	public void reaparecer() {

	    listaEnemigos.destruirAll();
	    contador = tiempoEntreOrdas - 4; 
	    jugador.setVidas(jugador.getVidas() - 1);

	    jugador.posicionarloA(centroPantalla);
	    jugador.rotarloA(-90);

	    // 4 parpadeos de 0.4s = 1.6 segundos
	    jugador.iniciarParpadeo(1.6);
	}


	// Devolver al controladorNiveles la información de la partida
	public void completado(boolean gano) {
		controlador.cerrarJuego(gano, enemigosMuertos, nivelActual, (int) Math.round(tiempoJugado));
	}

	// Generar origen (spawn) de enemigos en un radio determinado
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

	// Metodo oleada, genera enemigos de diferente dificultad según porcentajes
	protected void oleada(
		double porcentajeFaciles,
		double porcentajeMedios,
		double porcentajeDificiles,
		int distanciaMin,
	    int distanciaMax
	) {
		// Cálculo de cantidades por tipo
		int cantidadFaciles  = (int) (enemigosPorOleada * porcentajeFaciles);
		int cantidadMedios   = (int) (enemigosPorOleada * porcentajeMedios);
		int cantidadDificiles = (int) (enemigosPorOleada * porcentajeDificiles);

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

		// === ENEMIGOS DIFÍCILES ===
		for (int i = 0; i < cantidadDificiles; i++) {
			Vector2D spawn = generarSpawnAlejado(posJugador, distanciaMin, distanciaMax);
			listaEnemigos.add(new EnemigoDificil(spawn, jugador));
		}
	}

	// Abstractos
	public abstract void generarEnemigos();

	// Getters & Setters
	public Nave getJugador() {
		return jugador;
	}

	public void setJugador(Nave jugador) {
		this.jugador = jugador;
	}

	public ListaEntidades getBalas() {
		return balas;
	}

	public void setBalas(ListaEntidades balas) {
		this.balas = balas;
	}

	public ListaEntidades getListaEnemigos() {
		return listaEnemigos;
	}

	public void setListaEnemigos(ListaEntidades listaEnemigos) {
		this.listaEnemigos = listaEnemigos;
	}

	public AdministradorDeColisiones getAdministrador() {
		return administrador;
	}

	public void setAdministrador(AdministradorDeColisiones administrador) {
		this.administrador = administrador;
	}

	public boolean isPrimeraOrda() {
		return primeraOrda;
	}

	public void setPrimeraOrda(boolean primeraOrda) {
		this.primeraOrda = primeraOrda;
	}

	public double getTiempoOrdaInicial() {
		return tiempoOrdaInicial;
	}

	public void setTiempoOrdaInicial(double tiempoOrdaInicial) {
		this.tiempoOrdaInicial = tiempoOrdaInicial;
	}

	public int getNivelActual() {
		return nivelActual;
	}

	public void setNivelActual(int nivelActual) {
		this.nivelActual = nivelActual;
	}

	public double getTiempoEntreOrdas() {
		return tiempoEntreOrdas;
	}

	public void setTiempoEntreOrdas(double tiempoEntreOrdas) {
		this.tiempoEntreOrdas = tiempoEntreOrdas;
	}

	public int getEnemigosPorOleada() {
		return enemigosPorOleada;
	}

	public void setEnemigosPorOleada(int enemigosPorOleada) {
		this.enemigosPorOleada = enemigosPorOleada;
	}

	public int getEnemigosParaGanar() {
		return enemigosParaGanar;
	}

	public void setEnemigosParaGanar(int enemigosParaGanar) {
		this.enemigosParaGanar = enemigosParaGanar;
	}

	public int getEnemigosMuertos() {
		return enemigosMuertos;
	}

	public void setEnemigosMuertos(int enemigosMuertos) {
		this.enemigosMuertos = enemigosMuertos;
	}

	public double getContador() {
		return contador;
	}

	public double getTiempoJugado() {
		return tiempoJugado;
	}

	public void setContador(double contador) {
		this.contador = contador;
	}

	public NivelesControlador getControlador() {
		return controlador;
	}
	
	public void setControlador(NivelesControlador controlador) {
	    this.controlador = controlador;
	}
}

