package descuento;

import models.Articulo;

public class DescuentoArticulo {
	public double aplicarDescuentoSubsidiados(Articulo art) {
		char rubro = Character.toUpperCase(art.getRubroArticulo());
		double precio = art.getPrecioArticulo();

		if (rubro == 'A') {
			precio = this.descontarPorcentaje(precio, 30);
		} else if (rubro == 'E') {
			precio = this.descontarPorcentaje(precio, 24);
		} else if (rubro == 'L') {
			precio = this.descontarPorcentaje(precio, 15);
		}
		return precio;
	}

	public double aplicarDescuentoPorDemanda(Articulo art, int stockDeseado) {
//		tienen un stock deseado, cuando se encuentran por arriba de este, deben
//		aplicar un descuento basado en el porcentaje por el que se encuentran excedidos
//		con un maximo del 50 porciento
		double total = art.getPrecioArticulo();
		int stockArticulo = art.getStockArticulo();
		if (stockArticulo >= stockDeseado) {
			double porcentajeEnExeso = this.calcularPorcentajeExedido(stockArticulo, stockDeseado);
			total = this.descontarPorcentaje(total, porcentajeEnExeso);
		}
		return total;
	}

	private double calcularPorcentajeExedido(int sotckArticulo, int stockDeseado) {
//		maximo 50 porciento
		double porcentajeExcedente = ((double) (sotckArticulo - stockDeseado) / stockDeseado) * 100;
		return Math.min(porcentajeExcedente, 50);
	}

	public double descontarPorcentaje(double importe, double porcentajeDeDescuento) {
		double descuento = (porcentajeDeDescuento / 100) * importe;
		return importe - descuento;
	}
}
