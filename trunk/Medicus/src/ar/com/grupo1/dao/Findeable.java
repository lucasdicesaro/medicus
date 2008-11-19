package ar.com.grupo1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Brinda una especificacion para utilizar los servicios de consulta (find) de
 * DAOSupport.
 * 
 * @author Lucas
 * 
 */
public interface Findeable {
	/**
	 * Le dice a DAOSupport que Query a ejecutar
	 * @return
	 */
	public String getQuery();
	
	/**
	 * Le dice a DAOSupport como manejar el resultado de la query ejecutada
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public Object handleResultSet(ResultSet rs) throws SQLException;
}
