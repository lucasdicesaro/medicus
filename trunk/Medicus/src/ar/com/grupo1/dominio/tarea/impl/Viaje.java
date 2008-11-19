package ar.com.grupo1.dominio.tarea.impl;

import org.apache.log4j.Logger;

import ar.com.grupo1.dominio.jornada.Jornada;
import ar.com.grupo1.dominio.paciente.Paciente;
import ar.com.grupo1.dominio.tarea.Tarea;
import ar.com.grupo1.exceptions.ValoresInvalidosException;


/**
 * Representa los viajes de los camilleros
 * 
 * @author Lucas
 * 
 */
public class Viaje extends Tarea {
	private static Logger logger = Logger.getLogger(Viaje.class);
	/**
	 * Valor por cada viaje realizado
	 */
	public static final Double BONO_POR_VIAJE = new Double(2);
	/**
	 * Valor que se multiplica por cada Km recorrido
	 */
	public static final Double BONO_POR_KM = new Double(1);
	/**
	 * Valor que se adiciona por llevar acompaniantes
	 */
	public static final Double BONO_POR_ACOMPANIANTE = new Double(12);
	/**
	 * Valor que se multiplica por el monto, por ser fin de semana
	 */
	public static final Double BONO_POR_FIN_DE_SEMANA = new Double(2);
	/**
	 * Jornada en que se produce el Viaje
	 */
	private Jornada jornada;
	/**
	 * Son los familiares o conocidos del paciente que viaja en la ambulancia.
	 * Puede no tener ninguno.
	 */
	private Paciente[] acompaniantes;
	/**
	 * Indica la distancia recorrida en el Viaje
	 */
	private Long distancia; 

	/**
	 * Se obtiene le valor de cada viaje realizado
	 */
	public Double getMonto() throws ValoresInvalidosException{
		logger.debug("Calculando monto del viaje...");
		if (distancia == null || distancia <= 0) { 
			throw new ValoresInvalidosException("El viaje debe tener una distancia");
		}
		if (jornada == null) { 
			throw new ValoresInvalidosException("El viaje debe tener una jornada");
		}
		Double monto = BONO_POR_VIAJE + (BONO_POR_KM * distancia);
		if (tieneAcompaniantes()) {
			logger.info("PLUS - El Paciente viene con acompaniantes");
			monto += BONO_POR_ACOMPANIANTE; 
		}
		if (jornada.isFinDeSemana()) {
			logger.info("PLUS - El Viaje se produce un fin de semana");
			monto += BONO_POR_FIN_DE_SEMANA;
		}
		return monto;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

	public Paciente[] getAcompaniantes() {
		return acompaniantes;
	}

	public void setAcompaniantes(Paciente[] acompaniantes) {
		this.acompaniantes = acompaniantes;
	}

	public Long getDistancia() {
		return distancia;
	}

	public void setDistancia(Long distancia) {
		this.distancia = distancia;
	}

	public boolean tieneAcompaniantes() {
		return acompaniantes != null && acompaniantes.length > 0;
	}
}
