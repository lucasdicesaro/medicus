package ar.com.grupo1.exceptions;

/**
 * Se utiliza para indicar que hay errores de datos.
 * Se utiliza con datos que generalmente ingresa el usuario.
 * @author Lucas Dic&eacute;saro
 *
 */
public class ValoresInvalidosException extends Exception {

	public ValoresInvalidosException() {
		super();
	}
	public ValoresInvalidosException(String mensaje) {
		super(mensaje);
	}
}
