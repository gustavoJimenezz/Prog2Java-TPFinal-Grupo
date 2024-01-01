package contenedores;
import models.Articulo;

public class ContenedorArticulos extends Contenedor<Articulo>{
	
	public Articulo buscarArticulo(int codigo) {
		Articulo artAux = null;
		for (Articulo articulo : this.elementos) {
			if (articulo.getId() == codigo) {
				artAux = articulo;
				break;
			}
		}
		return artAux;
	}
	
	@Override
	public void agregar(Articulo art) {
		super.agregar(art);
	}

	public int proximoId() {
		return elementos.size() + 1;
	}
}
