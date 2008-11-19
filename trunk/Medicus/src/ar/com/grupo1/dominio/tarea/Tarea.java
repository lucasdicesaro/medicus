package ar.com.grupo1.dominio.tarea;

import ar.com.grupo1.dominio.paciente.Paciente;
import ar.com.grupo1.exceptions.ValoresInvalidosException;

/**
 * Representa acciones que puede llevar a cabo un Empleado
 * 
 * @author Lucas
 * 
 */
public abstract class Tarea {

	private Paciente paciente;

	/**
	 * Obtiene el paciente que esta involucrado con la Tarea
	 * @return
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	/**
	 * Obliga a que cada tarea defina una forma de calcular su valor en
	 * unidades. La clase que extienda de Tarea debe definir este metodo.
	 * 
	 * Antes se calculaba el sueldo del Doctor a partir de Cirugias y Consultas 
	 * Ahora se calcula mediante la Obra Social del Paciente
	 * 
	 * Este metodo solo sirve para los Camilleros y sus Viajes
	 * @return
	 * @throws ValoresInvalidosException
	 */
	public abstract Double getMonto() throws ValoresInvalidosException;
}
