package ar.com.grupo1.dominio.tarea.impl;

import ar.com.grupo1.dominio.tarea.Tarea;

/**
 * La Cirugia es una de las tareas que realiza el Doctor
 * 
 * @author Lucas
 * 
 */
public class Cirugia extends Tarea {
	
	/**
	 * Valor de cada cirugia
	 */
	public static final Double MONTO_CIRUGIA = new Double(20);

	/**
	 * Se obtiene el valor de cada cirugia
	 */
	public Double getMonto() {
		return MONTO_CIRUGIA;
	}
}
