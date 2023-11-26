package menues;

import java.util.Scanner;
import contenedores.ContenedorArticulos;
import exceptions.ExcepcionDeIngresoDeDatos;
import herramientas.GestorIdArticulos;
import models.ArticuloPorDemanda;
import models.Articulo;
import models.ArticuloSubsidiado;

public class MenuEmpleado extends Menu {
	private ContenedorArticulos listaArticulos;
	int idArticulo = 1;

	public MenuEmpleado(Scanner sc, ContenedorArticulos listaArticulos) {
		super(sc);
		this.listaArticulos = listaArticulos;
	}

	@Override
	public String opciones(){
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
	public void procesarOpcion(String opcionElegida) throws ExcepcionDeIngresoDeDatos {
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
		if (listaArticulos.estaVacio()) {
			System.out.println("La lista de Articulos est� vacia.");
		} else {
			for (Articulo articulo : listaArticulos.getElementos()) {
				System.out.println(articulo);
			}
		}
	}

	private void cargarArticulo() throws ExcepcionDeIngresoDeDatos {
		System.out.println();
		System.out.println(" * Cargar Articulo *");
		System.out.print("Ingresar el nombre del Articulo : ");
		String nombreArticulo = super.getSc().next();
		System.out.print("Ingrese el precio Neto : ");
		double precioArticulo = super.getSc().nextDouble();
		System.out.print("Ingrese el Stock : ");
		int stockArticulo = super.getSc().nextInt();

		char rubro;
		do {
			System.out.println("Ingrese el Rubro (A / E / L)");
			System.out.println("Alimentos o Electrodomesticos o Limpieza");
			rubro = super.getSc().next().charAt(0);
			rubro = Character.toUpperCase(rubro);
		} while (rubro != 'A' && rubro != 'E' && rubro != 'L');


		System.out.println("Tipo de Aticulo");
		System.out.println("1- Subsidiados");
		System.out.println("2- Por demanda");
		System.out.println("3- Simples");
		System.out.print("Ingrese : ");
		String tipoArticulo = super.getSc().next();
		
		Articulo nuevoArticulo = null;
		switch (tipoArticulo) {
		case "1":
			nuevoArticulo = new ArticuloSubsidiado(nombreArticulo, precioArticulo, stockArticulo, rubro);
			break;
		case "2":
			System.out.print("Ingrese stock deaseado : ");
			int stockDeaseado = super.getSc().nextInt();
			nuevoArticulo = new ArticuloPorDemanda(nombreArticulo, precioArticulo, stockArticulo, rubro, stockDeaseado);
			break;
		case "3":
			nuevoArticulo = new Articulo(nombreArticulo, precioArticulo, stockArticulo, rubro);
			break;
		default:
			System.out.println("Opcion incorrecta !");
			break;
		}
		GestorIdArticulos.asignarID(nuevoArticulo);
		listaArticulos.agregar(nuevoArticulo);
		System.out.println("Articulo Agregado con �xito.");
		
		throw new ExcepcionDeIngresoDeDatos("Datos Incorrectos !");
	}
}
