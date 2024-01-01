package contenedores;

import java.util.HashMap;
import java.util.Map;
import herramientas.Calculadora;
import herramientas.FormatearDecimal;
import models.Articulo;

public class ContenedorCarrito {
	private double compraMayorDescuento = 12000;
	private double porcentajeDescuento = 15;
	private Map<Articulo, Integer> carrito = new HashMap<>();

	public Map<Articulo, Integer> getCarrito() {
		return carrito;
	}

	public double totalNeto() {
		double total = 0;
		for (Map.Entry<Articulo, Integer> entry : carrito.entrySet()) {
			Articulo art = entry.getKey();
			int cant = entry.getValue();
			total = art.getPrecioArticulo() * cant;
		}
		return FormatearDecimal.formatearConTresDecimales(total);
	}

	public double totalConDescuento() {
		double total = 0;
		for (Map.Entry<Articulo, Integer> entry : carrito.entrySet()) {
			Articulo art = entry.getKey();
			int cant = entry.getValue();
			total = art.getPrecioConDescuento() * cant;
		}
		return total;
	}

	public double totalFinal() {
		double subtotal = this.totalConDescuento();
		subtotal = aplicarDescuentoPorCompraMayor(subtotal);

		return subtotal;
	}

	private double aplicarDescuentoPorCompraMayor(double subTotal) {
		double total = subTotal;
		if (subTotal > this.compraMayorDescuento) {
			System.out.println();
			System.out.println("Descuento " + this.porcentajeDescuento + " %" + " por compra mayor a "
					+ this.compraMayorDescuento + " $");
			total = Calculadora.descontarPorcentaje(total, this.porcentajeDescuento);
		}
		return total;
	}

	public void mostrarCarrito() {
		for (Map.Entry<Articulo, Integer> entry : carrito.entrySet()) {
			Articulo art = entry.getKey();
			int cant = entry.getValue();
			this.mostrarArticulo(art, cant);
		}
	}

	public void mostrarArticulo(Articulo art, int cant) {
		System.out.println();
		System.out.println(art);
		System.out.println("Cantidad : " + cant);
		System.out.println("Total : " + this.totalPorArticulo(art, cant));
	}

	public double totalPorArticulo(Articulo art, int cant) {
		double total = art.getPrecioConDescuento() * cant;
		return FormatearDecimal.formatearConTresDecimales(total);
	}

	public void agregarAlCarrito(Articulo articulo, int cantidad) {
		if (carrito.containsKey(articulo)) {
			int cantidadExistente = carrito.get(articulo);
			carrito.put(articulo, cantidadExistente + cantidad);
		} else {
			carrito.put(articulo, cantidad);
		}
	}

	public boolean carritoVacio() {
		return carrito.isEmpty();
	}

	public void vaciar() {
		this.carrito.clear();
	}

	public void remover(Articulo art) {
		this.carrito.remove(art);
	}

	public Articulo buscarPorId(int codigoABuscar) {
		Articulo artAux = null;
		for (Articulo art : carrito.keySet()) {
			if (art.getId() == codigoABuscar) {
				artAux = art;
			}
		}
		return artAux;
	}
	
	public int obtenerCantDeArticulo(Articulo art) {
		return carrito.get(art);
	}
}
