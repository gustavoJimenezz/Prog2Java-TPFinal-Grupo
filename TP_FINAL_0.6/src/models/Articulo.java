package models;

public class Articulo {
	private int idArticulo;
	private String nombreArticulo;
	private double precioArticulo;
	private char rubroArticulo;
	private int stockArticulo;

	public Articulo(String nombreArticulo, double precioArticulo, int stockArticulo, char rubroArticulo) {
		super();
		this.nombreArticulo = nombreArticulo;
		this.precioArticulo = precioArticulo;
		this.stockArticulo = stockArticulo;
		this.rubroArticulo = rubroArticulo;
	}
	
	public String getNombreArticulo() {
		return this.nombreArticulo + " " + "("+this.getRubroArticulo()+")";
	}

	public double getPrecioConDescuento() {
		return this.precioArticulo;
	}
	
	public int getIdArticulo() {
		return idArticulo;
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

	public void disminuirStock() {
		this.stockArticulo--;
	}

	public boolean hayStock() {
		return this.stockArticulo > 0;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

//	@Override
//	public String toString() {
//		return "idArticulo : " + this.getIdArticulo() 
//				+ ", nombreArticulo : " + this.getNombreArticulo()
//				+ ", precioArticulo=" + precioArticulo + ", stockArticulo=" + stockArticulo + ", rubroArticulo="
//				+ rubroArticulo + ", tipo de descuento="
//						+ this.tipoDeDescuento + "]";
//	}

}
