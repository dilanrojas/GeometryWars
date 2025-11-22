package juego;

/**
 * @author Dilan Rojas
 * @date Nov 18, 2025
 * @version 1.0
 * @description description
 */

import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import motor.util.Loader;

/**
 * Clase encargada de centralizar la carga de recursos (assets) del juego, tales
 * como imágenes, sonidos y fuentes.
 * <p>
 * {@code Assets} funciona como un contenedor global que almacena los recursos
 * necesarios durante la ejecución del motor de juego. Su objetivo es evitar
 * cargas repetidas de archivos y optimizar el rendimiento.
 * </p>
 *
 * <h2>Uso de la clase</h2>
 *
 * <ol>
 * <li><b>PASO 1:</b> Crear las variables estáticas de los recursos del
 * juego.<br>
 * Pueden ser imágenes, sonidos o fuentes:
 * 
 * <pre>{@code
 * public static BufferedImage[] fondoAnimado = new BufferedImage[26];
 * public static BufferedImage jugador;
 * public static Clip sonido;
 * public static Font fuente;
 * public static String texto;
 * }</pre>
 * 
 * </li>
 *
 * <li><b>PASO 2:</b> En el método {@link #cargados()} realizar la carga de los
 * recursos utilizando la clase {@link Loader} y las rutas de acceso de cada
 * archivo:
 * 
 * <pre>{@code
 * for (int i = 0; i < fondoAnimado.length; i++) {
 * 	fondoAnimado[i] = Loader.cargarImagen("/img/fondos/especial/" + (i + 1) + ".png");
 * }
 *
 * jugador = Loader.cargarImagen("/img/jugador1.png");
 * sonido = Loader.cargarSonido("/sound/space/backgroundMusic2.wav");
 * fuente = Loader.cargarFuente("/font/futureFont.ttf", 20);
 * texto = Loader.cargarTexto("/levels/level1.txt");
 * }</pre>
 * 
 * </li>
 * </ol>
 *
 * 
 * <p>
 * Es recomendable que {@code Assets.cargados()} se invoque una sola vez al
 * inicio del juego (por ejemplo, en el método {@code main()} o en la clase
 * principal).
 * </p>
 */
public class Assets {

	// ============================================================
	// SECCIÓN DE DECLARACIÓN DE RECURSOS
	// ============================================================

	// AQUI SE CREAN LAS CONSTANTES PARA LAS IMAGENES DEL JUEGO
	public static BufferedImage textura_bala;
	public static BufferedImage textura_nave;
	public static BufferedImage textura_fire;
	
	public static BufferedImage textura_enemigoFacil;
	public static BufferedImage textura_enemigoMedio;
	public static BufferedImage textura_enemigoDificil;

	public static BufferedImage textura_fondo;
	
	public static Clip musicaFondo;
	public static Clip efectoDisparar;
	public static Clip efectoExplosion;

	// AQUI SE CREAN LAS CONSTANTES PARA LAS FUENTES DEL JUEGO

	// AQUI SE CREAN LAS CONSTANTES PARA LOS SONIDOS DEL JUEGO

	/**
	 * Método principal encargado de inicializar y cargar todos los recursos
	 * necesarios del juego (imágenes, fuentes y sonidos).
	 * <p>
	 * Debe ejecutarse antes de iniciar el bucle principal del juego, ya que los
	 * demás sistemas del motor dependen de que estos recursos estén listos.
	 * </p>
	 *
	 * <p>
	 * <b>Ejemplo:</b>
	 * </p>
	 * 
	 * <pre>{@code
	 * public static void main(String[] args) {
	 * 	if (Assets.cargados()) {
	 * 		System.out.println("Recursos cargados correctamente.");
	 * 		// Iniciar juego
	 * 	}
	 * }
	 * }</pre>
	 *
	 * @return {@code true} si la carga de recursos fue exitosa.
	 */
	public static boolean cargados() {

		// AQUI SE REALIZA LA CARGA DE LAS IMAGENES DEL JUEGO
		textura_nave = Loader.cargarImagen("/asteroide/ship_blue.png");
		textura_bala = Loader.cargarImagen("/asteroide/laser_blue.png");
		textura_fire = Loader.cargarImagen("/asteroide/fire_blue.png");
		
		textura_enemigoFacil = Loader.cargarImagen("/enemigos/enemigoFacil.png");
		textura_enemigoMedio = Loader.cargarImagen("/enemigos/enemigoMedio.png");
		textura_enemigoDificil = Loader.cargarImagen("/enemigos/enemigoDificil.png");
		
		musicaFondo = Loader.cargarSonido("/musica/bgm.wav");
		efectoDisparar = Loader.cargarSonido("/sonidos/shoot2.wav");
		efectoExplosion = Loader.cargarSonido("/sonidos/explosion.wav");
		
		textura_fondo = Loader.cargarImagen("/fondos/bg_1.png");
		
		return true;
	}
	
	public static void reproducirMusicaFondo() {
	    if (musicaFondo == null) return;

	    musicaFondo.stop();
	    musicaFondo.setFramePosition(0);

	    // Control de volumen
	    FloatControl controlVolumen =
	    (FloatControl) musicaFondo.getControl(FloatControl.Type.MASTER_GAIN);
	    controlVolumen.setValue(-12.0f);

	    musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
	    musicaFondo.start();
	}


	public static void detenerMusicaFondo() {
		if (musicaFondo == null) return;
	    musicaFondo.stop();
	}
	
	public static void reproducirDisparo() {
		if (efectoDisparar == null) return;
		efectoDisparar.stop();
		efectoDisparar.setFramePosition(0);
		efectoDisparar.start();
	}
	
	public static void reproducirExplosion() {
		if (efectoExplosion == null) return;
		efectoExplosion.stop();
		efectoExplosion.setFramePosition(0);
		efectoExplosion.start();
	}

}
