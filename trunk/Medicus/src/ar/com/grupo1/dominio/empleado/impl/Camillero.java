package ar.com.grupo1.dominio.empleado.impl;

import org.apache.log4j.Logger;

import ar.com.grupo1.dominio.empleado.Empleado;
import ar.com.grupo1.exceptions.ValoresInvalidosException;

/**
 * Se responsabiliza de todo lo que tenga que ver con el Camillero
 * 
 * @author Lucas
 * 
 */
public class Camillero extends Empleado {

	private static Logger logger = Logger.getLogger(Camillero.class);
	/**
	 * Indica el valor del sueldo mensual
	 */
	public static final Double SUELDO_FIJO = new Double(600);

	/**
	 * Recorre todas las tareas (para el camillero, solo viajes) y obtiene la suma del valor de cada una de ellas.
	 * Por ultimo, suma lo acumulado al sueldo fijo
	 */
	@Override
	public Double getSueldo() throws ValoresInvalidosException {
		Double totalViajes = new Double(0);
		logger.debug("Calculando el sueldo del Camillero");
		validarTareas();
		for (int i = 0; i < tareas.length; i++) {
			totalViajes += tareas[i].getMonto();
		}
		return SUELDO_FIJO + totalViajes;
	}

}
