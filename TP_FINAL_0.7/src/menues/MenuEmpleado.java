package menues;

import java.util.Scanner;
import contenedores.ContenedorArticulos;
import exceptions.excepcionDeIngresoDeDatos;
import models.Articulo;

public class MenuEmpleado extends Menu{
	private ContenedorArticulos listaArticulos;
	int idArticulo = 1;
	
	public MenuEmpleado(Scanner sc, ContenedorArticulos listaArticulos) {
		super(sc);
		this.listaArticulos = listaArticulos;
	}
	
	@Override
	public String opciones() throws excepcionDeIngresoDeDatos {
		System.out.println();
		System.out.println(" ** Menu Empleado **");
		System.out.println("1. Cargar Articulo");
		System.out.println("2. Editar Articulo");
		System.out.println("3. Eliminar Articulo");
		System.out.println("4. Ver Lista Completa de Articulos.");
		System.out.println("5. Ver Cantidad de Stock por articulo");
		System.out.println("6. Editar Cantidad de Stock por articulo");
		System.out.println("0. Salir");
		System.out.println("Ingrese : ");
		return super.getSc().next();
	}
	
	@Override
	public void procesarOpcion(String opcionElegida) throws excepcionDeIngresoDeDatos{
		switch (opcionElegida) {
		case "1":
			cargarArticulo();
			break;
		case "4":
			mostrarUno();
		case "0":
			super.finalizar();
			break;
		default:
			break;
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
	    String nombreArticulo = super.getSc().next();
	    System.out.println("Ingrese el precio Neto: ");
	    double precioArticulo = super.getSc().nextDouble();
	    System.out.println("Ingrese el Stock: ");
	    int stockArticulo = super.getSc().nextInt();
	    
//	    agregue, pedir tipo de descuento (ACA corregir)
//		1- subsidiados 2- pordemanda 3- simples
	    System.out.println("Tipo de descuentos");
	    System.out.println("1- subsidiados");
	    System.out.println("2- por demanda");
	    System.out.println("3- simples");
	    System.out.print("Ingrese tipo de descuento : ");
	    int tipoDeDescuento = super.getSc().nextInt();
//	    
	    char rubroArticuloFull = 0;

	    while (rubroArticuloFull != 'A' && rubroArticuloFull != 'E') {
	        System.out.println("Ingrese el Rubro (A o E)");
	        System.out.println("Alimentos o Electrodomesticos");
	        char rubroArticulo = super.getSc().next().charAt(0);
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
