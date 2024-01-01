package contenedores;

import models.Usuario;

public class ContenedorUsuarios extends Contenedor<Usuario> {
	public Usuario buscarUsuarioPorCredenciales(String nombre, String contra) {
		Usuario usuarioAux = null;
		for (Usuario usuario : elementos) {
			if (usuario.login(nombre, contra)) {
				usuarioAux = usuario;
				break;
			}
		}
		return usuarioAux;
	}

	public Usuario buscarUsuarioPorNombre(String nombreUsuario) {
		for (Usuario usuario : elementos) {
			if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario))
				;
			{
				return usuario;
			}
		}
		return null;
	}
	
	@Override
	public void agregar(Usuario usr) {
		super.agregar(usr);
	}
	
	public int proximoId() {
		return elementos.size() + 1;
	}
}
