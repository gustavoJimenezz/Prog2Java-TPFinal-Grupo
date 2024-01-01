package menues;

import java.util.Scanner;

import exceptions.ExcepcionDeIngresoDeDatos;
import models.Articulo;

public class MenuEditarArticulo extends Menu {
	private Articulo artAModificar;
	
	public MenuEditarArticulo(Scanner sc, Articulo art) {
		super(sc);
		artAModificar = art;
	}

	@Override
	public String opciones() {
		System.out.println(" * Menu  Edicion Articulo * ");
		System.out.println("1- Nombre");
		System.out.println("2- Precio");
		System.out.println("3- Stock");
		System.out.println("0- Salir del Menu");
		return super.getSc().next();
	}

	@Override
	public void procesarOpcion(String opcionElegida) throws ExcepcionDeIngresoDeDatos {
		switch (opcionElegida) {
		case "1":
			editarNombreArt();
			break;
		case "2":
			editarPrecioArt();
			break;
		case "3":
			editarStockArt();
			break;
		case "0":
			super.finalizar();
			break;
		default:
			System.out.println("Opcion invalida.");
			break;
		}

	}
	
	private void editarStockArt() {
		System.out.println("Stock Actual: " + artAModificar.getStockArticulo());
		System.out.println("Cuanto Stock le quiere poner? ");
		int rta = super.getSc().nextInt();
		artAModificar.setStockArticulo(rta);
		System.out.println("Articulo modificado con exito!");
	}
	
	private void editarPrecioArt() {
		System.out.println("Precio Actual: " + artAModificar.getPrecioArticulo());
		System.out.println("Que precio le quiere poner? ");
		int rta = super.getSc().nextInt();
		artAModificar.setPrecioArticulo(rta);
		System.out.println("Articulo modificado con exito!");
	}
	
	private void editarNombreArt() {
		System.out.println("Nombre Actual: " + artAModificar.getNombreArticulo());
		System.out.println("Que nombre le quiere poner? ");
		String rta = super.getSc().next();
		artAModificar.setNombreArticulo(rta);
		System.out.println("Articulo modificado con exito!");
	}

}
