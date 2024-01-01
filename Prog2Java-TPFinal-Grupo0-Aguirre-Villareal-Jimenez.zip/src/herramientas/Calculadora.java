package herramientas;

public class Calculadora {

	public static double descontarPorcentaje(double importe, double porcentajeDeDescuento) {
		double descuento = (porcentajeDeDescuento / 100) * importe;
		return importe - descuento;
	}
}
