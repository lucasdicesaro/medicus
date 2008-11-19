package ar.com.grupo1.negocio;

import org.apache.log4j.Logger;

import ar.com.grupo1.dominio.empleado.Empleado;

/**
 * Se responsabiliza de realizar las liquidaciones de los sueldos de los
 * empleados
 * 
 * @author Lucas
 * 
 */
public class Liquidador {

	private static Logger logger = Logger.getLogger(Liquidador.class);

	/**
	 * Recorre todos los empleados y muestra el nombre y el sueldo de cada uno
	 * de ellos
	 * 
	 * @param empleados
	 */
	public static void liquidarSueldos(Empleado[] empleados) throws Exception {
		for (int i = 0; i < empleados.length; i++) {
			logger.info(" ================ DETALLE DEL EMPLEADO: " + 
					empleados[i].getNombre() + "\t $" + empleados[i].getSueldo() + " ===================");
		}
	}
}
