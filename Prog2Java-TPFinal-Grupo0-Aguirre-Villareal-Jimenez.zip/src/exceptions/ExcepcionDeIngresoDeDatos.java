package exceptions;

public class ExcepcionDeIngresoDeDatos extends Exception {

	private static final long serialVersionUID = 93961415774598480L;

	public ExcepcionDeIngresoDeDatos() {
	}

	public ExcepcionDeIngresoDeDatos(String message) {
		super(message);
	}

	public ExcepcionDeIngresoDeDatos(Throwable cause) {
		super(cause);
	}

	public ExcepcionDeIngresoDeDatos(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcepcionDeIngresoDeDatos(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
