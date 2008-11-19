package ar.com.grupo1.dao.obrasocial;

import junit.framework.TestCase;
import ar.com.grupo1.dominio.obrasocial.ObraSocial;
import ar.com.grupo1.dominio.obrasocial.impl.Medife;
import ar.com.grupo1.dominio.obrasocial.impl.Osde;
import ar.com.grupo1.dominio.obrasocial.impl.SwissMedical;

public class ObraSocialDAOTest extends TestCase {

	private ObraSocialDAO obraSocialDAO; 
	
	@Override
	protected void setUp() throws Exception {
		obraSocialDAO = new ObraSocialDAO();
	}

	public void testFindAll() {
		System.out.println(obraSocialDAO.findAll());
	}

	public void testRetreive() {		
		System.out.println(obraSocialDAO.retreive((long)1));
	}
	
	public void testRetreiveByCodigo() {		
		System.out.println(obraSocialDAO.retreiveByCodigo(Osde.OSDE));
	}

	public void testSave() {
		ObraSocial osde = new Osde();
		osde.setNombre("Obra Social de Empresas");
		osde.setMonto(204.6);
		obraSocialDAO.save(osde);
		
		ObraSocial swiss = new SwissMedical();
		swiss.setNombre("Swiss Medical Group");
		swiss.setMonto(187.6);
		obraSocialDAO.save(swiss);
		
		ObraSocial medife = new Medife();
		medife.setNombre("Medife S.A.");
		medife.setMonto(87.6);
		obraSocialDAO.save(medife);
	}
	
	public void testRemove() {
		obraSocialDAO.remove(new Osde());
//		obraSocialDAO.remove(new Medife());
//		obraSocialDAO.remove(new SwissMedical());
	}
}
