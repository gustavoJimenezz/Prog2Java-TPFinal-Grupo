package models;

import herramientas.Calculadora;

public class ArticuloPorDemanda extends Articulo {
	private int stockDeseado;
	public ArticuloPorDemanda(String nombreArticulo, double precioArticulo, int stockArticulo, char rubroArticulo, int stockDesdeado) {
		super(nombreArticulo, precioArticulo, stockArticulo, rubroArticulo);
		this.stockDeseado = stockDesdeado;
	}
	
	@Override
	public double getPrecioConDescuento() {
		return aplicarDescuento();
	}
	
	public double aplicarDescuento() {
//		tienen un stock deseado, cuando se encuentran por arriba de este, deben
//		aplicar un descuento basado en el porcentaje por el que se encuentran excedidos
//		con un maximo del 50 porciento
		double total = super.getPrecioArticulo();
		int stockArticulo = super.getStockArticulo();
		if (stockArticulo >= this.stockDeseado) {
			double porcentajeEnExeso = this.calcularPorcentajeExedido(stockArticulo, stockDeseado);
			total = Calculadora.descontarPorcentaje(total, porcentajeEnExeso);
		}
		return total;
	}

	private double calcularPorcentajeExedido(int sotckArticulo, int stockDeseado) {
//		maximo 50 porciento
		double porcentajeExcedente = ((double) (sotckArticulo - stockDeseado) / stockDeseado) * 100;
		return Math.min(porcentajeExcedente, 50);
	}

	public int getStockDesdeado() {
		return stockDeseado;
	}
}
