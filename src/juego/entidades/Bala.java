package juego.entidades;

import java.awt.Graphics;

import juego.Assets;
import motor.component.Renderer;
import motor.entidades.SpriteMovible;
import motor.entidades.interfaces.IColisionable;
import motor.util.Vector2D;

/**
 * @author Dilan Rojas
 * @date Nov 18, 2025
 * @version 1.0
 * @description description
 */

public class Bala extends SpriteMovible {

    private static final double DURACION_MAX = 2.0;      // segundos antes de desaparecer
	  private static final int OFFSET_ANGULAR = 90; // DESFACE EN GRADOS DE LAS TEXTURAS

    private double tiempoDeVida; // CRONOMETRO

    public Bala(Vector2D posicionInicial, double velocidadInicial, int anguloDireccion) {
        super(Assets.textura_bala, posicionInicial);

        tiempoDeVida = 0;
        		
        // Configurar movimiento inicial
        getMovement().setDireccion(anguloDireccion);
        getMovement().setVelocidad(velocidadInicial);

        // rotar la bala según su dirección
        getTransform().rotarloA(anguloDireccion + OFFSET_ANGULAR);
        
        escalarloA(0.5);// HACEMOS LA BALA PEQUENA. 50%
    }

    @Override
    public void actualizar() {
    	// Aplicar movimiento
        getMovement().mover(getTransform());
    	
    	super.actualizar(); // IMPORTANTE ACTUALIZAMOS TODOS LOS ATRIBUTOS DE LA SUPER CLASE
    }

    @Override
    public void dibujar(Graphics g) {
        super.dibujar(g);
        //Renderer.dibujarCollider(g, this);
    }

   
    @Override
    public void alColisionarCon(IColisionable otro) {
    	super.destruir();
    }
}



