package contenedores;

import models.Usuario;

public class ContenedorUsuarios extends Contenedor<Usuario> {
//	buscar por contra y usuario
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
}
