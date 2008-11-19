package ar.com.grupo1.exceptions;

/**
 * Responsable de manejar todos los errores funcionales
 * @author Lucas
 *
 */
public class BusinessException extends RuntimeException {

	public BusinessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
