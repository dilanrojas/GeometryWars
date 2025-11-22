package juego;

/**
 * @author Dilan Rojas
 * @date Nov 18, 2025
 * @version 1.0
 * @description description
 */

/**
 * Clase de configuración global del motor de juego.
 * <p>
 * {@code Conf} actúa como contenedor de constantes que definen parámetros generales
 * del sistema, como dimensiones de pantalla, límites del mundo o ajustes por defecto.
 * </p>
 * 
 * <p>
 * Estas constantes se utilizan en distintas partes del motor (por ejemplo en
 * {@link Lienzo} o {@link Game}) para inicializar correctamente la ventana
 * y establecer el tamaño base del área de juego.
 * </p>
 * 
 * <p>
 * Es recomendable mantener esta clase libre de lógica y centrada únicamente
 * en valores inmutables, lo que facilita la lectura, el mantenimiento y la
 * modificación de configuraciones globales.
 * </p>
 */
public class Config {

    /**
     * Ancho predeterminado de la ventana o área de juego (en píxeles).
     * <p>
     * Este valor se utiliza al crear la instancia de {@link Lienzo} para definir
     * la resolución base del juego.
     * </p>
     */
    public static final int WIDTH = 1366;

    /**
     * Alto predeterminado de la ventana o área de juego (en píxeles).
     * <p>
     * Junto con {@link #WIDTH}, determina el tamaño inicial del lienzo y la
     * proporción de aspecto del juego.
     * </p>
     */
    public static final int HEIGHT = 768;

    public static final String TITLE = "PRUEBAS PARA MEJORAR EL MOTOR V7";
}


