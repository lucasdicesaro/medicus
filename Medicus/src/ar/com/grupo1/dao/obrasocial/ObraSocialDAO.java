package ar.com.grupo1.dao.obrasocial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;

import ar.com.grupo1.dao.DAOSupport;
import ar.com.grupo1.dao.Executable;
import ar.com.grupo1.dao.Findeable;
import ar.com.grupo1.dominio.obrasocial.ObraSocial;
import ar.com.grupo1.dominio.obrasocial.impl.Medife;
import ar.com.grupo1.dominio.obrasocial.impl.Osde;
import ar.com.grupo1.dominio.obrasocial.impl.SwissMedical;
import ar.com.grupo1.exceptions.BusinessException;

public class ObraSocialDAO extends DAOSupport {

	private static Logger logger = Logger.getLogger(ObraSocialDAO.class);

	/**
	 * Retorna todos las Obras Sociales
	 * @return
	 */
	public List findAll() {
		return (List) find(new Findeable() {
			public String getQuery() {
				return "SELECT Id, nombre, monto, codigo FROM obras_sociales";
			}

			public List<ObraSocial> handleResultSet(ResultSet rs)
					throws SQLException {
				List<ObraSocial> resultado = new ArrayList<ObraSocial>();
				ObraSocial obraSocial = null;
				while (rs.next()) {
					obraSocial = cargarObraSocial(rs);
					resultado.add(obraSocial);
				}
				return resultado;
			}
		});
	}

	/**
	 * Retorna un ObraSocial segun su id
	 * @param id
	 * @return
	 */
	public ObraSocial retreive(final Long id) {
		return (ObraSocial) find(new Findeable() {
			public String getQuery() {
				return "SELECT Id, nombre, monto, codigo FROM obras_sociales WHERE Id = "
						+ id;
			}

			public ObraSocial handleResultSet(ResultSet rs) throws SQLException {
				ObraSocial obraSocial = null;
				if (rs.next()) {
					obraSocial = cargarObraSocial(rs);
				} else {
					logger.debug("No se encontro una obra social con id=" + id);
				}
				return obraSocial;
			}
		});
	}

	/**
	 * Retorna un ObraSocial segun su codigo
	 * @param codigo
	 * @return
	 */
	public ObraSocial retreiveByCodigo(final String codigo) {
		return (ObraSocial) find(new Findeable() {
			public String getQuery() {
				return "SELECT Id, nombre, monto, codigo FROM obras_sociales WHERE codigo = '"
						+ codigo + "'";
			}

			public ObraSocial handleResultSet(ResultSet rs) throws SQLException {
				ObraSocial obraSocial = null;
				if (rs.next()) {
					obraSocial = cargarObraSocial(rs);
				} else {
					logger.debug("No se encontro una obra social con codigo ="
							+ codigo);
				}
				return obraSocial;
			}
		});
	}

	/**
	 * Guarda una obraSocial
	 * @param obraSocial
	 * @return
	 */
	public Long save(ObraSocial obraSocial) {
		Long obraSocialId = null;
		final String codigo = obraSocial.getCodigo();
		final String nombre = obraSocial.getNombre();
		final Double monto = obraSocial.getMonto();
		if (obraSocial != null && codigo != null) {
			if (!existeObraSocial(obraSocial)) {
				obraSocialId = save(new Executable() {
					public String getQuery() {
						return "INSERT INTO obras_sociales (nombre, monto, codigo) VALUES "
								+ "('"
								+ nombre
								+ "',"
								+ monto
								+ ",'"
								+ codigo
								+ "')";
					}
				});
				obraSocial.setId(obraSocialId);
				logger.debug("Se dio de alta la ObraSocial " + codigo
						+ " con id=" + obraSocial.getId());
			} else {
				logger.debug("La Obra Social " + codigo
						+ " ya existe, por lo que nada se hace");
			}
		} else {
			String message = "La Obra Social es nula o posee datos erroneos";
			logger.error(message);
			throw new BusinessException(message);
		}
		return obraSocialId;
	}

	/**
	 * Borra una obraSocial
	 * @param obraSocial
	 */
	public void remove(ObraSocial obraSocial) {
		if (obraSocial != null) {
			final String codigo = obraSocial.getCodigo();
			if (existeObraSocial(obraSocial)) {
				save(new Executable() {
					public String getQuery() {
						return "DELETE FROM obras_sociales WHERE codigo = '"
								+ codigo + "'";
					}
				});
				logger.debug("Se dio de baja la ObraSocial con codigo = "
						+ codigo);
			} else {
				String message = "La Obra Social con codigo " + codigo
						+ " no existe. Imposible borrar";
				logger.error(message);
				throw new BusinessException(message);
			}
		} else {
			String message = "La Obra Social es nula. Imposible borrar";
			logger.error(message);
			throw new BusinessException(message);
		}
	}

	/**
	 * Carga de datos a un Obra Social
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public ObraSocial cargarObraSocial(ResultSet rs) throws SQLException {
		String codigo = rs.getString("codigo");
		ObraSocial obraSocial = getInstanciaObraSocial(codigo);
		obraSocial.setId(rs.getLong("Id"));
		obraSocial.setNombre(rs.getString("nombre"));
		obraSocial.setMonto(rs.getDouble("monto"));
		return obraSocial;
	}

	/**
	 * Instancia una ObraSocial segun su codigo
	 * @param codigo
	 * @return
	 */
	public static synchronized ObraSocial getInstanciaObraSocial(String codigo) {
		ObraSocial obraSocial = null;
		if (Osde.OSDE.equals(codigo)) {
			obraSocial = new Osde();
		} else if (SwissMedical.SWISS_MEDICAL.equals(codigo)) {
			obraSocial = new SwissMedical();
		} else if (Medife.MEDIFE.equals(codigo)) {
			obraSocial = new Medife();
		} else {
			String message = "Obra Social no existente";
			logger.error(message);
			throw new BusinessException(message);
		}
		return obraSocial;
	}

	/**
	 * Verifica si existe la Obra Social
	 * @param obraSocial
	 * @return
	 */
	public boolean existeObraSocial(ObraSocial obraSocial) {
		boolean existe = false;
		ObraSocial obraSocialNueva = null;
		if (obraSocial != null
				&& !GenericValidator.isBlankOrNull(obraSocial.getCodigo())) {
			obraSocialNueva = retreiveByCodigo(obraSocial.getCodigo());
			if (obraSocialNueva != null) {
				// Si la obraSocial por parametro no tiene Id, le seteo el id
				// obtenido de la Base
				obraSocial.setId(obraSocialNueva.getId());
				existe = true;
			}
		}
		return existe;
	}
}
