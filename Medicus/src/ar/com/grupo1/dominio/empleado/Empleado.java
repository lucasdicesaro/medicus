package ar.com.grupo1.dominio.empleado;

import ar.com.grupo1.dominio.tarea.Tarea;
import ar.com.grupo1.exceptions.ValoresInvalidosException;

/**
 * Un Empleado reune el comportamiento de todos los tipos de empleados de la
 * clinica
 * 
 * @author Lucas
 * 
 */
public abstract class Empleado {

	/**
	 * Nombre del empleado
	 */
	private String nombre;
	
	/**
	 * Representa las tareas del empleado
	 */
	protected Tarea[] tareas;

	/**
	 * Aloja las tareas del empleado
	 * 
	 * @return
	 */
	public Tarea[] getTareas() {
		return tareas;
	}

	/**
	 * 
	 * @param tareas
	 */
	public void setTareas(Tarea[] tareas) {
		this.tareas = tareas;
	}

	/**
	 * Nombre del empleado
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Debe realizarse la implementacion en cada clase concreta
	 * @return
	 * @throws ValoresInvalidosException
	 */
	public abstract Double getSueldo() throws ValoresInvalidosException;
	
	public void validarTareas() throws ValoresInvalidosException{
		if (tareas == null || tareas.length <= 0) {
			throw new ValoresInvalidosException("El empleado no tiene tareas para calcular su sueldo");
		}
	}
}
