package ar.com.grupo1.dominio.jornada;

import java.util.Date;

public class Jornada {

	/**
	 * Indica el valor de la multa por cada hora no trabajada
	 */
	public static final int VALOR_MULTA = 10;
	/**
	 * Indica si la Jornada es dentro del fin de semana
	 */
	private boolean finDeSemana;

	public Jornada() {
	}

	/**
	 * Para crear una hornada con horas de ausencia
	 * @param horasNoTrabajadas
	 */
	public Jornada(int horasNoTrabajadas) {
		this.horasNoTrabajadas = horasNoTrabajadas;
	}
	
	/**
	 * Para crear una hornada indicando si es fin de semana
	 * @param horasNoTrabajadas
	 */
	public Jornada(boolean finDeSemana) {
		this.finDeSemana = finDeSemana;
	}

	/**
	 * Indica la cantidad de horas no trabajas
	 */
	private int horasNoTrabajadas;
	
	/**
	 * Indica la fecha a la que hace mencion la jornada
	 */
	private Date fecha;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getHorasNoTrabajadas() {
		return horasNoTrabajadas;
	}

	public void setHorasNoTrabajadas(int horasNoTrabajadas) {
		this.horasNoTrabajadas = horasNoTrabajadas;
	}

	public boolean isFinDeSemana() {
		return finDeSemana;
	}

	public void setFinDeSemana(boolean finDeSemana) {
		this.finDeSemana = finDeSemana;
	}
	/**
	 * Realiza el producto del total de las horas no trabajadas en el dia, por el valor de la multa 
	 * @return
	 */
	public int getMultaDiaria() {
		return horasNoTrabajadas * VALOR_MULTA;
	}
}