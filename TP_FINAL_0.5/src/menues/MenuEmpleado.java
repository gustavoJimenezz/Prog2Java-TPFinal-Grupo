package menues;

import java.util.Scanner;
import contenedores.ContenedorArticulos;
import models.Articulo;

public class MenuEmpleado {
	private Scanner sc;
	private ContenedorArticulos listaArticulos;
	
	int idArticulo = 1;
	public Scanner getSc() {
		return sc;
	}
	public void setSc(Scanner sc) {
		this.sc = sc;
	}
	
	public MenuEmpleado(Scanner sc, ContenedorArticulos listaArticulos) {
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
		if(listaArticulos.estaVacio()) {
			System.out.println("La lista de Articulos est� vacia.");
		}else {
			for (Articulo articulo : listaArticulos.getElementos()) {
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
//	    
	    char rubroArticuloFull = 0;

	    while (rubroArticuloFull != 'A' && rubroArticuloFull != 'E') {
	        System.out.println("Ingrese el Rubro (A o E)");
	        System.out.println("Alimentos o Electrodomesticos");
	        char rubroArticulo = sc.next().charAt(0);
	        rubroArticuloFull = Character.toUpperCase(rubroArticulo);
	    }
	    
//	    String nombres[] = {"teclado", "mause", "auriculares", "monitor", "Pendrive"};
//	    double precios[] = {12, 234, 13000, 89, 50};
//	    int tiposDeDescuentos[] = {1,2,3,1,2};
//	    for (int i = 0; i < tiposDeDescuentos.length; i++) { 
//			
//	    	listaArticulos.agregar(new Articulo(i, nombres[i], precios[i], 15, rubroArticuloFull, tiposDeDescuentos[i]));
//		}
	    listaArticulos.agregar(new Articulo(idArticulo, nombreArticulo, precioArticulo, stockArticulo, rubroArticuloFull, tipoDeDescuento));

	    System.out.println("Articulo Agregado con �xito.");
	}

}
