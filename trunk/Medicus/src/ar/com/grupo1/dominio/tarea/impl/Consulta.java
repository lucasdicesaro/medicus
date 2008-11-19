package ar.com.grupo1.dominio.tarea.impl;

import ar.com.grupo1.dominio.tarea.Tarea;

/**
 * La Consulta es una de las tareas que realiza el Doctor
 * 
 * @author Lucas
 * 
 */
public class Consulta extends Tarea {
	
	/**
	 * Valor de cada consulta
	 */
	public static final Double MONTO_CONSULTA = new Double(10);

	/**
	 * Se obtiene el valor de cada consulta
	 */
	public Double getMonto() {
		return MONTO_CONSULTA;
	}
}
