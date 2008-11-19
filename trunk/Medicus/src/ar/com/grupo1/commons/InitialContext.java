package ar.com.grupo1.commons;

import org.apache.log4j.Logger;

public class InitialContext {
	private static Logger logger = Logger.getLogger(InitialContext.class);
	private static boolean inicializado = false;
	
	/**
	 * Registra el driver de la base de datos MySQL
	 */
	public static void init() {
		try {
			// Registro del driver
//			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			if (!inicializado) {
				Class.forName("org.gjt.mm.mysql.Driver");				
				inicializado = true;
				logger.debug("Driver registrado...");
			}
		} catch (ClassNotFoundException e) {
			logger.error(e, e);
		}
	}	
}
