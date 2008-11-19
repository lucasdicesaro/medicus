package ar.com.grupo1.dominio.obrasocial.impl;

import org.apache.commons.lang.builder.ToStringBuilder;

import ar.com.grupo1.dominio.obrasocial.ObraSocial;

public class Osde extends ObraSocial {
	
	public static final String OSDE = "osde";


	/**
	 * Determina el valor del bono
	 * @deprecated Ahora se utiliza el monto tomado de la base
	 */
	public static final Double VALOR_BONO = new Double(75);

	/**
	 * Retorna el valor del bono correspondiente a la obra social
	 */
	public Double getValorBono() {
//		return VALOR_BONO;
		return getMonto();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public String getCodigo() {
		return OSDE;
	}
	
}
