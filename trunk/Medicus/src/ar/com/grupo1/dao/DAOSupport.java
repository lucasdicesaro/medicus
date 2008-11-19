package ar.com.grupo1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ar.com.grupo1.commons.InitialContext;
import ar.com.grupo1.exceptions.SystemException;

public abstract class DAOSupport {
	
	private static final Logger logger = Logger.getLogger(DAOSupport.class);
	
	/**
	 * Inicializa el contexto
	 */
	static {
		InitialContext.init();
	}
	
	/**
	 * Abstraccion para realizar una consulta en la base
	 * @param findeable
	 * @return
	 */
	protected Object find(Findeable findeable) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Object resultado = null;
		try {
			con = abrirRecursos();			
			stmt = con.createStatement();			
			String query = findeable.getQuery();			
			rs = stmt.executeQuery(query);	
			resultado = findeable.handleResultSet(rs);			
		} catch (SQLException e) {
			logger.error(e, e);
			throw new SystemException(e);
		}
		finally {
			try {
				cerrarRecursos(con, stmt, rs);
			} catch (SQLException e) {
				logger.error(e, e);
				throw new SystemException(e);
			}
		}
		return resultado;
	}
	
	/**
	 * Abstraccion para realizar inserciones y eliminaciones en la base
	 * @param executable
	 * @return
	 */
	protected Long save(Executable executable) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Long id = null;
		try {
			con = abrirRecursos();			
			stmt = con.createStatement();
			String query = executable.getQuery();			
			stmt.execute(query);
			rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");	
			if (rs.next()) {
				id = rs.getLong(1);
			}			
		} catch (SQLException e) {
			logger.error(e, e);
			throw new SystemException(e);
		}
		finally {
			try {
				cerrarRecursos(con, stmt, rs);
			} catch (SQLException e) {
				logger.error(e, e);
				throw new SystemException(e);
			}
		}
		return id;
	}

	/**
	 * Abre una conexion a la base
	 * @return
	 * @throws SQLException
	 */
	private Connection abrirRecursos() throws SQLException {
		// Apertura de la conexión
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/medicus","root", "admin");
		return conexion;
	}
	
	/**
	 * Cierra la conexion, el statement y el resoult set si lo hubiere
	 * @param con
	 * @param stmt
	 * @param rs
	 * @throws SQLException
	 */
	private void cerrarRecursos(Connection con, Statement stmt, ResultSet rs)
			throws SQLException {
		// Cierre de recursos
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (con != null)		
			con.close();
	}
}
