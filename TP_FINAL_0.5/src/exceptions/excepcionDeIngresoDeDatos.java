package exceptions;

public class excepcionDeIngresoDeDatos extends Exception {

	private static final long serialVersionUID = 93961415774598480L;

	public excepcionDeIngresoDeDatos() {
	}

	public excepcionDeIngresoDeDatos(String message) {
		super(message);
	}

	public excepcionDeIngresoDeDatos(Throwable cause) {
		super(cause);
	}

	public excepcionDeIngresoDeDatos(String message, Throwable cause) {
		super(message, cause);
	}

	public excepcionDeIngresoDeDatos(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
