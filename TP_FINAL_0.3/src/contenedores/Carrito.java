package contenedores;

import java.util.ArrayList;
import models.Articulo;

public class Carrito {
	ArrayList<Articulo> articulos;

	public Carrito() {
		this.articulos = new ArrayList<Articulo>();
	}

	public ArrayList<Articulo> getArticulos() {
		return this.articulos;
	}

	public void agregarArticulo(Articulo art) {
		this.articulos.add(art);
	}

	public void sacarArticulo(Articulo art) {
		this.articulos.remove(art);
	}

	public void mostrarArticulosDelCarrito() {
		for (Articulo articulo : articulos) {
			System.out.println(articulo);
		}
	}

	public double totalNeto() {
		double total = 0;
		for (Articulo articulo : articulos) {
			total += articulo.getPrecioArticulo();
		}
		return total;
	}

	public void limpiarCarrito() {
		this.articulos.clear();
	}
}
