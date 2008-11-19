package ar.com.grupo1.dao;

/**
 * Brinda una especificacion para utilizar los servicios de modificacion (save)
 * de DAOSupport.
 * 
 * @author Lucas
 * 
 */
public interface Executable {
	/**
	 * Le dice a DAOSupport que Query a ejecutar
	 * @return
	 */
	public String getQuery();
}
