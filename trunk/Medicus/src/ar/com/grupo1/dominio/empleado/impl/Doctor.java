package ar.com.grupo1.dominio.empleado.impl;

import org.apache.log4j.Logger;

import ar.com.grupo1.dominio.empleado.Empleado;
import ar.com.grupo1.exceptions.ValoresInvalidosException;

/**
 * Se responsabiliza de todo lo que tenga que ver con un Doctor
 * 
 * @author Lucas
 * 
 */
public class Doctor extends Empleado {
	private static Logger logger = Logger.getLogger(Doctor.class);

	/**
	 * Recorre todas las tareas y obtiene la suma del valor de cada una de ellas
	 */
	@Override
	public Double getSueldo() throws ValoresInvalidosException {
		logger.debug("Calculando sueldo del Doctor...");
		Double total = new Double(0);
		for (int i = 0; i < tareas.length; i++) {
			if (tareas[i].getPaciente() == null) {
				throw new ValoresInvalidosException("Cada tarea del Doctor debe tener al menos un Paciente");
			}
			Double monto = tareas[i].getPaciente().getMontoAPagar();
			if (monto == null) {
				throw new ValoresInvalidosException("Hubo un error al calcular el monto que debe pagar el Paciente: " + tareas[i].getPaciente().getLegajo());
			}
 			total += monto;
		}
		return total;
	}
}
