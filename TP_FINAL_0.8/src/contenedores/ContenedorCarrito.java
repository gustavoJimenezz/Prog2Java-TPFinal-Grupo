package contenedores;
import herramientas.Calculadora;
import models.Articulo;

public class ContenedorCarrito extends Contenedor<Articulo> {
	private double compraMayorDescuento = 12000;
	private double porcentajeDescuento = 15;
	
	public double totalNeto() {
		double total = 0;
		for (Articulo articulo : this.elementos) {
			total += articulo.getPrecioArticulo();
		}
		return total;
	}
	
	public double totalConDescuento() {
		double total = 0;
		for (Articulo articulo : this.elementos) {
			total += articulo.getPrecioConDescuento();
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
			System.out.println("Descuento " + this.porcentajeDescuento + " %" + " por compra mayor a " + this.compraMayorDescuento + " $");
			total = Calculadora.descontarPorcentaje(total, this.porcentajeDescuento);
		}
		return total;
	}
	

}
