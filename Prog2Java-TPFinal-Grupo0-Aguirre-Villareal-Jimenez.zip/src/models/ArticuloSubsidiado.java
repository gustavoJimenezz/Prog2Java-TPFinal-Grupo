package models;

import herramientas.Calculadora;

public class ArticuloSubsidiado extends Articulo {

	public ArticuloSubsidiado(int id, String nombreArticulo, double precioArticulo, int stockArticulo, char rubroArticulo) {
		super(id, nombreArticulo, precioArticulo, stockArticulo, rubroArticulo);
	}
	
	@Override
	public String getNombreArticulo() {
		//Subsidiados : Estos articulos se debe mostrar con la leyenda "(S)" al final de su nombre
		return super.getNombreArticulo() + " " + "(S)";
	}
	
	@Override
	public double getPrecioConDescuento() {
		return this.aplicarDescuento();
	}
	
	public double aplicarDescuento() {
		char rubro = Character.toUpperCase(super.getRubroArticulo());
		double precio = super.getPrecioArticulo();

		if (rubro == 'A') {
			precio = Calculadora.descontarPorcentaje(precio, 30);
		} else if (rubro == 'E') {
			precio = Calculadora.descontarPorcentaje(precio, 24);
		} else if (rubro == 'L') {
			precio = Calculadora.descontarPorcentaje(precio, 15);
		}
		return precio;
	}

}
