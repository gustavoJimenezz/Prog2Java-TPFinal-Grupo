package models;

public class Usuario {

	private int id;
	private String nombreUsuario;
	private String contraseniaUsuario;

	public Usuario(int id, String nombreUsuario, String contraseniaUsuario) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.contraseniaUsuario = contraseniaUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setContraseniaUsuario(String contraseniaUsuario) {
		this.contraseniaUsuario = contraseniaUsuario;
	}

	public String getContraseniaUsuario() {
		// no se deberia poder devolver la contrasenia
		return contraseniaUsuario;
	}

//	aca agregue login para no usar un get de contraseña	
	public boolean login(String nombre, String contra) {
		return this.nombreUsuario.equals(nombre) && this.contraseniaUsuario.equals(contra);
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Usuario [nombreUsuario=" + nombreUsuario + ", contraseniaUsuario=" + contraseniaUsuario + "]";
	}
}
