package ar.com.grupo1.dominio.paciente;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import ar.com.grupo1.dominio.obrasocial.ObraSocial;
import ar.com.grupo1.exceptions.ValoresInvalidosException;

public class Paciente {
	
	private static Logger logger = Logger.getLogger(Paciente.class);
	
	/**
	 * Factor que es multiplicado por la edad si corresponde 
	 */
	public static final Double FACTOR_EDAD = new Double(0.75);
	/**
	 * Establece el limite de edad para ser menor
	 */
	public static final int LIMITE_EDAD = 10;
	
	private int edad;
	private ObraSocial obraSocial;
	private Long id;
	private String legajo;

	/**
	 * Retorna la edad del paciente
	 * @return
	 */
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Retorna la obra social del paciente
	 * @return
	 */
	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}
	
	/**
	 * Indica si el paciente tiene obra social
	 * @return
	 */
	public boolean tienePrepaga() {
		return obraSocial != null;
	}
	
	/**
	 * Indica si el paciente es menor
	 * @return
	 */
	public boolean isMenor() {
		return edad < LIMITE_EDAD;
	}
	
	/**
	 * Calcula el monto a pagar por el Paciente
	 * @return
	 */
	public Double getMontoAPagar() throws ValoresInvalidosException {
		logger.debug("Calculando el monto a pagar del paciente...");
		if (edad <= 0) {
			throw new ValoresInvalidosException("La edad no puede ser negativa. Paciente [" + legajo + "] Edad erronea: " + edad);
		}
		Double montoAPagar = new Double(0);
		if (tienePrepaga()) {
			logger.debug("El paciente tiene prepaga...");
			montoAPagar = obraSocial.getValorBono();
		}
		else {
			logger.debug("El paciente no tiene prepaga... Edad: " + edad);
			montoAPagar = edad * FACTOR_EDAD;
		}
		
		return montoAPagar;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
