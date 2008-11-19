package ar.com.grupo1.dao.paciente;

import ar.com.grupo1.dominio.obrasocial.ObraSocial;
import ar.com.grupo1.dominio.obrasocial.impl.Osde;
import ar.com.grupo1.dominio.paciente.Paciente;
import junit.framework.TestCase;

public class PacienteDAOTest extends TestCase {
	
	private PacienteDAO pacienteDAO; 
	
	@Override
	protected void setUp() throws Exception {
		pacienteDAO = new PacienteDAO();
	}

	public void testFindAll() {
		System.out.println(pacienteDAO.findAll());
	}

	public void testRetreive() {		
		System.out.println(pacienteDAO.retreive((long)1));
	}
	
	public void testRetreiveByLegajo() {		
		System.out.println(pacienteDAO.retreiveByLegajo("800608"));
	}

	public void testSave() {
		ObraSocial osde = new Osde();
		osde.setNombre("Obra Social de Empresas");
		osde.setMonto(204.6);
		
		Paciente paciente1 = new Paciente();
		paciente1.setLegajo("800608");
		paciente1.setEdad(43);
		paciente1.setObraSocial(osde);		
		pacienteDAO.save(paciente1);
		
		System.out.println(paciente1);
	}
	
	public void testRemove() {
		Paciente paciente1 = new Paciente();
		paciente1.setLegajo("800608");
		pacienteDAO.remove(paciente1);
	}	

}
