package contenedores;
import models.Articulo;

public class ContenedorArticulos extends Contenedor<Articulo>{
	public Articulo buscarArticulo(int codigo) {
		Articulo artAux = null;
		for (Articulo articulo : this.elementos) {
			if (articulo.getIdArticulo() == codigo) {
				artAux = articulo;
				break;
			}
		}
		return artAux;
	}
}
