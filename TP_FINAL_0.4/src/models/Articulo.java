package models;

public class Articulo {
	private int idArticulo;
	private String nombreArticulo;
	private double precioArticulo;
	private int stockArticulo;
	private char rubroArticulo;

//	Agregue atributo tipoDeDescuento, 1- subsidiados 2- pordemanda 3- simples
	private int tipoDeDescuento;

	public Articulo(int idArticulo, String nombreArticulo, double precioArticulo, int stockArticulo, char rubroArticulo,
			int tipoDeDescuento) {
		super();
		this.idArticulo = idArticulo;
		this.nombreArticulo = nombreArticulo;
		this.precioArticulo = precioArticulo;
		this.stockArticulo = stockArticulo;
		this.rubroArticulo = rubroArticulo;
		this.tipoDeDescuento = tipoDeDescuento;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getNombreArticulo() {
//		Subsidiados : Estos articulos se debe mostrar con la leyenda "(S)" al final de su nombre
		return this.tipoDeDescuento == 1 ? nombreArticulo + " (S) " : nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public double getPrecioArticulo() {
		return precioArticulo;
	}

	public void setPrecioArticulo(double precioArticulo) {
		this.precioArticulo = precioArticulo;
	}

	public int getStockArticulo() {
		return stockArticulo;
	}

	public void setStockArticulo(int stockArticulo) {
		this.stockArticulo = stockArticulo;
	}

	public char getRubroArticulo() {
		return rubroArticulo;
	}

	public void setRubroArticulo(char rubroArticulo) {
		this.rubroArticulo = rubroArticulo;
	}

	public int getTipoDeDescuento() {
		return tipoDeDescuento;
	}

	public void setTipoDeDescuento(int descuento) {
		this.tipoDeDescuento = descuento;
	}

	public void disminuirStock() {
		this.stockArticulo--;
	}

	public boolean hayStock() {
		return this.stockArticulo > 0;
	}

	@Override
	public String toString() {
		return "Articulos [idArticulo=" + idArticulo + ", nombreArticulo=" + this.getNombreArticulo()
				+ ", precioArticulo=" + precioArticulo + ", stockArticulo=" + stockArticulo + ", rubroArticulo="
				+ rubroArticulo + ", tipo de descuento="
						+ this.tipoDeDescuento + "]";
	}

}
