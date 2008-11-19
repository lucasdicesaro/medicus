package ar.com.grupo1.negocio;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import ar.com.grupo1.dominio.empleado.Empleado;
import ar.com.grupo1.dominio.empleado.impl.Administrativo;
import ar.com.grupo1.dominio.empleado.impl.Camillero;
import ar.com.grupo1.dominio.empleado.impl.Doctor;
import ar.com.grupo1.dominio.jornada.Jornada;
import ar.com.grupo1.dominio.obrasocial.ObraSocial;
import ar.com.grupo1.dominio.obrasocial.impl.Medife;
import ar.com.grupo1.dominio.obrasocial.impl.Osde;
import ar.com.grupo1.dominio.obrasocial.impl.SwissMedical;
import ar.com.grupo1.dominio.paciente.Paciente;
import ar.com.grupo1.dominio.tarea.Tarea;
import ar.com.grupo1.dominio.tarea.impl.Cirugia;
import ar.com.grupo1.dominio.tarea.impl.Consulta;
import ar.com.grupo1.dominio.tarea.impl.Viaje;

/**
 * Realiza las pruebas de liquidaciones
 * 
 * @author Lucas
 * 
 */
public class LiquidadorTest extends TestCase {

	private static Logger logger = Logger.getLogger(LiquidadorTest.class);
	/**
	 * Realiza la prueba para liquidar sueldos
	 */
	public void testLiquidarSueldos() {
		Empleado[] empleados = new Empleado[3];

		// *********** Datos del doctor **************//
		logger.debug("============ Cargando datos del doctor... ============ ");
		Doctor doctor1 = new Doctor();
		doctor1.setNombre("Schwaizer");
		// Instancio las consultas
		logger.debug("Creando las consultas...");
		Consulta[] consultas = new Consulta[200];
		for (int i = 0; i < consultas.length; i++) {
			consultas[i] = (Consulta) cargarDatosDelPaciente(i, new Consulta());
		}
		// Instancio las cirugias		
		logger.debug("Creando las cirugias...");
		Cirugia[] cirugias = new Cirugia[15];
		for (int i = 0; i < cirugias.length; i++) {
			cirugias[i] = (Cirugia) cargarDatosDelPaciente(i, new Cirugia());
		}
		logger.debug("Cargando las consultas y cirugias en las Tareas del Doctor ...");
		// Cargo las consultas y las cirugias en un array de Tareas
		Tarea[] tareasDoctor1 = new Tarea[consultas.length + cirugias.length];
		int indice = 0;
		for (int i = 0; i < consultas.length; i++) {
			tareasDoctor1[indice++] = consultas[i];
		}
		for (int i = 0; i < cirugias.length; i++) {
			tareasDoctor1[indice++] = cirugias[i];
		}
		logger.debug("Cargando Tareas al Doctor ...");
		// Cargo las tareas en el doctor
		doctor1.setTareas(tareasDoctor1);
		
		
		

		logger.debug("============  Cargando datos del camillero... ============ ");
		// ***********  Datos del camillero *********** 
		Camillero camillero1 = new Camillero();
		camillero1.setNombre("Ramirez");
		Viaje[] viajes = new Viaje[230];
		logger.debug("Creando los Viajes del Camillero ...");
		for (int i = 0; i < viajes.length; i++) {
			Viaje viaje = (Viaje)cargarDatosDelPaciente(i, new Viaje());;
			// Cada 6 y 7 dias es fin de semana
			Jornada jornada = new Jornada((i % 6 == 0) || (i % 7 == 0));
			viaje.setJornada(jornada);// Para forzar una Exception, comentar esta sentencia
			if (i % 5  == 0) { // Un numero al azar: "Cada tanto el paciente viene con acompañantes"
				Paciente[] acompaniantes = new Paciente[1];
				Paciente paciente = new Paciente();
				acompaniantes[0] = paciente;
				viaje.setAcompaniantes(acompaniantes);
			}
			viaje.setDistancia(new Long(20 + (i % 50)));// Para forzar una Exception, comentar esta sentencia
			viajes[i] = viaje;
		}
		logger.debug("Cargando los Viajes a las tareas del Camillero ...");
		Tarea[] tareasCamillero1 = new Tarea[viajes.length];
		for (int i = 0; i < viajes.length; i++) {
			tareasCamillero1[i] = viajes[i];
		}
		logger.debug("Cargando las Tareas al Camillero ...");
		camillero1.setTareas(tareasCamillero1);

		
		logger.debug("============ Cargando datos del administrativo... ============ ");
		// ***********  Datos del administrativo *********** 
		Administrativo administrativo1 = new Administrativo();
		administrativo1.setNombre("Valeria");
		Jornada[] jornadasAdm = new Jornada[22];
		logger.debug("Creando las jornadas del Administrativo...");
		for (int i = 0; i < jornadasAdm.length; i++) {
			// Cada cuatro dias tiene una hora de ausencia
			jornadasAdm[i] = (i % 4 == 0) ? new Jornada(1) : new Jornada();
		}
		logger.debug("Cargando las Jornadas al Administrativo...");
		administrativo1.setJornadas(jornadasAdm);
		// Le asigno al Administrativo las mismas Tareas que tiene el Doctor ya que 
		// el Paciente, antes de ser atendido por el Doctor, debe registrarse
		// en la Administracion
		logger.debug("Le asigno al Administrativo las mismas Tareas que tiene el Doctor ya que " +
				"el Paciente, antes de ser atendido por el Doctor, debe registrarse" +
				" en la Administracion...");
		administrativo1.setTareas(tareasDoctor1);

		logger.debug("Cargo los empleados... ");
		empleados[0] = doctor1;
		empleados[1] = camillero1;
		empleados[2] = administrativo1;

		try {
			logger.debug("Proceso el calculo de sueldos... ");
			Liquidador.liquidarSueldos(empleados);
		} catch (Exception e) {
			logger.error(e, e);
		}
	}

	/**
	 * Carga una tarea con datos del paciente y obra social aleatorios
	 * @param i
	 * @param tarea
	 * @return
	 */
	private Tarea cargarDatosDelPaciente(int i, Tarea tarea) {
		Paciente paciente = new Paciente();
		ObraSocial obraSocial = null;
		// Si sale 3 el, el paciente no tiene Prepaga
		obraSocial = (i % 4 == 0) ? new Medife() : null;
		obraSocial = (i % 4 == 1) ? new SwissMedical() : null;
		obraSocial = (i % 4 == 2) ? new Osde() : null;		
		paciente.setEdad((i % 80)+1); // Para forzar una Exception, quita el +1 de esta sentencia		
		paciente.setLegajo("Paciente" + i);
		paciente.setObraSocial(obraSocial);
		tarea.setPaciente(paciente);
		return tarea;
	}

}
