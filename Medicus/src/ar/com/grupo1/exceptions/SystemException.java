package ar.com.grupo1.exceptions;

/**
 * Responsable de manejar todos los errores del sistema.
 * @author Lucas
 *
 */
public class SystemException extends RuntimeException {

	public SystemException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SystemException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
