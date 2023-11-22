package contenedores;
import models.Articulo;

public class ContenedorCarrito extends Contenedor<Articulo> {
	public double totalNeto() {
		double total = 0;
		for (Articulo articulo : this.elementos) {
			total += articulo.getPrecioArticulo();
		}
		return total;
	}

}
