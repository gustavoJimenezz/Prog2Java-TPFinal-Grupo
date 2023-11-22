package menues;

import java.util.ArrayList;
import java.util.Scanner;

import models.Articulo;

public class MenuEmpleado {
	private Scanner sc;
	private ArrayList<Articulo> listaArticulos;
	
	int idArticulo = 1;
	public Scanner getSc() {
		return sc;
	}
	public void setSc(Scanner sc) {
		this.sc = sc;
	}
	public ArrayList<Articulo> getListaArticulos() {
		return listaArticulos;
	}
	public void setListaArticulos(ArrayList<Articulo> listaArticulos) {
		this.listaArticulos = listaArticulos;
	}
	public MenuEmpleado(Scanner sc, ArrayList<Articulo> listaArticulos) {
		super();
		this.sc = sc;
		this.listaArticulos = listaArticulos;
	}
	public void iniciar() {
		
		boolean continuar = true;
		
		while (continuar) {
			System.out.println("1. Cargar Articulo");
			System.out.println("2. Editar Articulo");
			System.out.println("3. Eliminar Articulo");
			System.out.println("4. Ver Lista Completa de Articulos.");
			System.out.println("5. Ver Cantidad de Stock por articulo");
			System.out.println("6. Editar Cantidad de Stock por articulo");
			System.out.println("0. Salir");
			int respuesta = sc.nextInt();
			
			switch (respuesta) {
			case 1:
				cargarArticulo();
				break;
			case 4:
				mostrarUno();
			case 0:
				continuar = false;
				break;
			default:
				break;
			}
		}
		
	}
	private void mostrarUno() {
		if(listaArticulos.isEmpty()) {
			System.out.println("La lista de Articulos est� vacia.");
		}else {
			for (Articulo articulo : listaArticulos) {
				System.out.println(articulo);
			}
		}
	}
	private void cargarArticulo() {
	    System.out.println("Ingresar el nombre del Articulo: ");
	    String nombreArticulo = sc.next();
	    System.out.println("Ingrese el precio Neto: ");
	    double precioArticulo = sc.nextDouble();
	    System.out.println("Ingrese el Stock: ");
	    int stockArticulo = sc.nextInt();
	    
//	    agregue, pedir tipo de descuento
//		1- subsidiados 2- pordemanda 3- simples
	    System.out.println("Tipo de descuentos");
	    System.out.println("1- subsidiados");
	    System.out.println("2- por demanda");
	    System.out.println("3- simples");
	    System.out.print("Ingrese tipo de descuento : ");
	    int tipoDeDescuento = sc.nextInt();
	    
	    char rubroArticuloFull = 0;

	    while (rubroArticuloFull != 'A' && rubroArticuloFull != 'E') {
	        System.out.println("Ingrese el Rubro (A o E)");
	        System.out.println("Alimentos o Electrodomesticos");
	        char rubroArticulo = sc.next().charAt(0);
	        rubroArticuloFull = Character.toUpperCase(rubroArticulo);
	    }

	    listaArticulos.add(new Articulo(idArticulo, nombreArticulo, precioArticulo, stockArticulo, rubroArticuloFull, tipoDeDescuento));
	    System.out.println("Articulo Agregado con �xito.");
	}

}
