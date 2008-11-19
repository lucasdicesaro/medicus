package ar.com.grupo1.dominio.obrasocial;

/**
 * ObraSocial define el contrato y comportamiento para toda obra social
 * @author Lucas
 *
 */
public abstract class ObraSocial {
	
	private Long id;
	private String nombre;
	private Double monto;
	
	/**
	 * Permite obtener el valor del bono correspondiente a la obra social
	 * @return
	 */
	public abstract Double getValorBono();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public abstract String getCodigo();
}
