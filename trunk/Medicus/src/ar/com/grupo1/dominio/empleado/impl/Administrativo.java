package ar.com.grupo1.dominio.empleado.impl;

import org.apache.log4j.Logger;

import ar.com.grupo1.dominio.empleado.Empleado;
import ar.com.grupo1.dominio.jornada.Jornada;
import ar.com.grupo1.dominio.paciente.Paciente;
import ar.com.grupo1.exceptions.ValoresInvalidosException;

/**
 * Se responsabiliza de todo lo que tenga que ver con el Administrativo
 * 
 * @author Lucas
 */
public class Administrativo extends Empleado {
	private static Logger logger = Logger.getLogger(Administrativo.class);
	/**
	 * Indica el valor del sueldo mensual
	 */
	public static final Double SUELDO_FIJO = new Double(1500);

	/**
	 * Valor que se adiciona por el calculo de costo de cada Paciente, menor de
	 * 18 y sin prepaga
	 */
	public static final Double BONO_CALCULO_COSTO = new Double(5);

	/**
	 * Contenedor de jornadas. Representa cada dia habil
	 */
	private Jornada[] jornadas;

	public Jornada[] getJornadas() {
		return jornadas;
	}

	public void setJornadas(Jornada[] jornadas) {
		this.jornadas = jornadas;
	}

	/**
	 * Realiza la diferencia entre el sueldo fijo y las horas no trabajadas
	 */
	@Override
	public Double getSueldo() throws ValoresInvalidosException {
		logger.debug("Calculando el sueldo del Administrativo...");
		Double multaMenual = new Double(0);
		if (jornadas == null) {
			throw new ValoresInvalidosException("No existen jornadas del Administrativo");
		}
		logger.debug("Calculando presentismo...");
		for (int i = 0; i < jornadas.length; i++) {
			multaMenual += jornadas[i].getMultaDiaria();
		}
		logger.info("Multa Menual del administratvo: " + multaMenual);
		Double monto = SUELDO_FIJO - multaMenual;
		logger.debug("Calculando tareas realizadas...");
		validarTareas();
		for (int i = 0; i < tareas.length; i++) {
			if (tareas[i] == null) {
				throw new ValoresInvalidosException("El administrativo, no tiene tareas para hacer");
			}
			if (tareas[i].getPaciente() == null) {
				throw new ValoresInvalidosException("La tarea del administratvo, no involucra a un Paciente");
			}
			Paciente paciente = tareas[i].getPaciente();
			logger.debug("El paciente " + paciente.getLegajo() + ", menor: " + paciente.isMenor()
					+ " - Obra Social: " + paciente.tienePrepaga());
			if (paciente.isMenor() && !paciente.tienePrepaga()) {
				logger.info("PLUS - El Paciente " + paciente.getLegajo() + " es menor y no tiene Prepaga");
				monto += BONO_CALCULO_COSTO;
			}
		}
		return monto;
	}
}
