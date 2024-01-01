package models;

public class Articulo {
	private int id;
	private String nombreArticulo;
	private double precioArticulo;
	private char rubroArticulo;
	private int stockArticulo;

	public Articulo(int idActual, String nombreArticulo, double precioArticulo, int stockArticulo, char rubroArticulo) {
		super();
		this.id = idActual;
		this.nombreArticulo = nombreArticulo;
		this.precioArticulo = precioArticulo;
		this.stockArticulo = stockArticulo;
		this.rubroArticulo = rubroArticulo;
	}

	public int getId() {
		return id;
	}

	public String getNombreArticulo() {
		return this.nombreArticulo + " " + "("+this.getRubroArticulo()+")";
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

	public void descontarStock(int stockAdescontar) {
		this.stockArticulo -= stockAdescontar;
	}

	public boolean hayStock(int stockSolicitado) {
		return this.stockArticulo >= stockSolicitado;
	}
	
	public double getPrecioConDescuento() {
		return this.precioArticulo;
	}
	
	@Override
	public String toString() {
		return 	 "Id Articulo = " + this.getId()
				+ "\n"
				+ "Nombre Articulo = " + this.getNombreArticulo()
				+ "\n"
				+ "Precio Articulo = " + this.getPrecioArticulo() 
				+ "\n"
				+ "Stock Articulo = " + this.getStockArticulo() 
				+ "\n"
				+ "Rubro Articulo = " + this.getRubroArticulo()
				+ "\n";
	}
}
