package ar.com.grupo1.dao.paciente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;

import ar.com.grupo1.dao.DAOSupport;
import ar.com.grupo1.dao.Executable;
import ar.com.grupo1.dao.Findeable;
import ar.com.grupo1.dao.obrasocial.ObraSocialDAO;
import ar.com.grupo1.dominio.obrasocial.ObraSocial;
import ar.com.grupo1.dominio.paciente.Paciente;
import ar.com.grupo1.exceptions.BusinessException;

public class PacienteDAO extends DAOSupport {

	private static Logger logger = Logger.getLogger(PacienteDAO.class);
	private static final String JOIN_OBRAS_SOCIALES = "SELECT pa.Id, pa.legajo, pa.edad, pa.obra_social_codigo, os.Id, os.nombre, os.monto, os.codigo "
			+ "FROM pacientes pa, obras_sociales os "
			+ "WHERE pa.obra_social_codigo = os.codigo";

	/**
	 * Retorna todos los pacientes
	 * @return
	 */
	public List findAll() {
		return (List) find(new Findeable() {
			public String getQuery() {
				return JOIN_OBRAS_SOCIALES;
			}

			public List<Paciente> handleResultSet(ResultSet rs)
					throws SQLException {
				List<Paciente> resultado = new ArrayList<Paciente>();
				Paciente paciente = null;
				while (rs.next()) {
					paciente = cargarPaciente(rs);
					resultado.add(paciente);
				}
				return resultado;
			}
		});
	}

	/**
	 * Retorna un paciente segun su id
	 * @param id
	 * @return
	 */
	public Paciente retreive(final Long id) {
		return (Paciente) find(new Findeable() {
			public String getQuery() {
				return JOIN_OBRAS_SOCIALES + " and pa.Id =" + id;
			}

			public Paciente handleResultSet(ResultSet rs) throws SQLException {
				Paciente paciente = null;
				if (rs.next()) {
					paciente = cargarPaciente(rs);
				} else {
					logger.debug("No se encontro un paciente con id=" + id);
				}
				return paciente;
			}
		});
	}

	/**
	 * Retorna un paciente segun su legajo
	 * @param legajo
	 * @return
	 */
	public Paciente retreiveByLegajo(final String legajo) {
		return (Paciente) find(new Findeable() {
			public String getQuery() {
				return JOIN_OBRAS_SOCIALES + " and pa.legajo = '" + legajo
						+ "'";
			}

			public Paciente handleResultSet(ResultSet rs) throws SQLException {
				Paciente paciente = null;
				if (rs.next()) {
					paciente = cargarPaciente(rs);
				} else {
					logger.debug("No se encontro un paciente con legajo = "
							+ legajo);
				}
				return paciente;
			}
		});
	}

	/**
	 * Guarda un paciente
	 * @param paciente
	 * @return
	 */
	public Long save(Paciente paciente) {
		Long pacienteId = null;
		if (paciente != null) {
			if (paciente.getObraSocial() != null) {
				ObraSocialDAO obraSocialDAO = new ObraSocialDAO();
				ObraSocial obraSocial = paciente.getObraSocial();
				obraSocialDAO.save(obraSocial);
				if (!existePaciente(paciente)) {

					final String legajo = paciente.getLegajo();
					final int edad = paciente.getEdad();
					final String obraSocialCodigo = paciente.getObraSocial()
							.getCodigo();

					pacienteId = save(new Executable() {
						public String getQuery() {
							return "INSERT INTO pacientes (legajo, edad, obra_social_codigo) VALUES "
									+ "('"
									+ legajo
									+ "',"
									+ edad
									+ ",'"
									+ obraSocialCodigo + "')";
						}
					});
					paciente.setId(pacienteId);
					logger.debug("Se dio de alta el Paciente con id: "
							+ paciente.getId());
				} else {
				logger.debug("El Paciente con legajo " + paciente.getLegajo()
						+ " ya existe, por lo que nada se hace");
				} 
			}
			else {
				String message = "El Paciente no posee Obra Social";
				logger.error(message);
				throw new BusinessException(message);
			} 
		} else {
			String message = "El Paciente es nulo";
			logger.error(message);
			throw new BusinessException(message);
		}
		return pacienteId;
	}

	/**
	 * Borra un paciente
	 * @param paciente
	 */
	public void remove(Paciente paciente) {
		if (paciente != null) {
			final String legajo = paciente.getLegajo();
			if (existePaciente(paciente)) {
				save(new Executable() {
					public String getQuery() {
						return "DELETE FROM pacientes WHERE legajo = '"
								+ legajo + "'";
					}
				});
				logger.debug("Se dio de baja el Paciente con legajo = "
						+ legajo);
			} else {
				String message = "El Paciente con legajo " + legajo
						+ " no existe. Imposible borrar";
				logger.error(message);
				throw new BusinessException(message);
			}

		} else {
			String message = "El Paciente es nulo";
			logger.error(message);
			throw new BusinessException(message);
		}
	}

	/**
	 * Carga de datos a un Paciente y su Obra Social
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static Paciente cargarPaciente(ResultSet rs) throws SQLException {
		Paciente paciente = new Paciente();
		paciente.setId(rs.getLong("pa.Id"));
		paciente.setLegajo(rs.getString("pa.legajo"));
		paciente.setEdad(rs.getInt("pa.edad"));
		ObraSocial obraSocial = ObraSocialDAO.getInstanciaObraSocial(rs
				.getString("os.codigo"));
		obraSocial.setId(rs.getLong("os.Id"));
		obraSocial.setNombre(rs.getString("os.nombre"));
		obraSocial.setMonto(rs.getDouble("os.monto"));
		paciente.setObraSocial(obraSocial);
		return paciente;
	}

	/**
	 * Verifica si existe el Paciente
	 * @param paciente
	 * @return
	 */
	public boolean existePaciente(Paciente paciente) {
		boolean existe = false;
		Paciente pacienteNuevo = null;
		if (paciente != null
				&& !GenericValidator.isBlankOrNull(paciente.getLegajo())) {
			pacienteNuevo = retreiveByLegajo(paciente.getLegajo());
			if (pacienteNuevo != null) {
				// Si el paciente por parametro no tiene Id, le seteo el id
				// obtenido de la Base
				paciente.setId(pacienteNuevo.getId());
				existe = true;
			}
		}
		return existe;
	}
}
