package menues;

import java.util.Scanner;
import contenedores.ContenedorArticulos;
import exceptions.ExcepcionDeIngresoDeDatos;
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
	public String opciones() {
		System.out.println();
		System.out.println(" ** Menu Empleado **");
		System.out.println("1. Cargar Articulo");
		System.out.println("2. Editar Articulo");
		System.out.println("3. Eliminar Articulo");
		System.out.println("4. Ver Lista Completa de Articulos.");
		System.out.println("5. Ver Cantidad de Stock por articulo");
		System.out.println("0. Salir");
		System.out.print("Ingrese : ");
		return super.getSc().next();
	}

	@Override
	public void procesarOpcion(String opcionElegida) throws ExcepcionDeIngresoDeDatos {
		switch (opcionElegida) {
		case "1":
			cargarArticulo();
			break;
		case "2":
			editarArticulo();
			break;
		case "3":
			eliminarArticulo();
			break;
		case "4":
			mostrarArticulos();
			break;
		case "5":
			verCantidadStockPorArticulo();
			break;
		case "0":
			super.finalizar();
			break;
		default:
			break;
		}
	}
	
	private void cargarArticulo() throws ExcepcionDeIngresoDeDatos {
		try {
			Articulo nuevoArticulo = crearArticulo();
			nuevoArticulo = this.aplicarCategoriaArticulo(nuevoArticulo);

			if (nuevoArticulo != null) {
				listaArticulos.agregar(nuevoArticulo);
				System.out.println("Articulo Agregado con exito.");
			} else {
				System.out.println("No se agregar el Articulo");
			}

		} catch (Exception e) {
			throw new ExcepcionDeIngresoDeDatos("Error al registrar articulo ! ");
		}
	}
	
	private Articulo aplicarCategoriaArticulo(Articulo art) {
		Articulo artConCategoria = null;
		if(art != null) {
			System.out.println("Tipo de Aticulo");
			System.out.println("1- Subsidiados");
			System.out.println("2- Por demanda");
			System.out.println("3- Simples");
			System.out.print("Ingrese : ");

			switch (super.getSc().next()) {
			case "1":
				artConCategoria = (ArticuloSubsidiado) art;
				break;
			case "2":
				artConCategoria = (ArticuloPorDemanda) art;
				System.out.print("Ingrese stock deaseado : ");
				int stockDeaseado = super.getSc().nextInt();
				((ArticuloPorDemanda) artConCategoria).setStockDeseado(stockDeaseado);
				break;
			case "3":
				artConCategoria = art;
				break;
			default:
				System.out.println("Opcion incorrecta !");
				break;
			}
		}
		return artConCategoria;
	}
	
	private Articulo crearArticulo() {
		System.out.println();
		System.out.println(" * Cargar Articulo *");
		System.out.print("Nombre: ");
		String nombreArticulo = super.getSc().next();
		System.out.print("Precio Neto : ");
		double precioArticulo = super.getSc().nextDouble();
		System.out.print("Stock : ");
		int stockArticulo = super.getSc().nextInt();
		char rubro = this.ingresarRubro();
		
		int id = listaArticulos.proximoId();

		Articulo art = new Articulo(id, nombreArticulo, precioArticulo, stockArticulo, rubro);

		return art;
	}
	
	private char ingresarRubro() {
		char rubro;
		do {
			System.out.println("Ingrese el Rubro");
			System.out.println("A - Alimentos");
			System.out.println("E - Electrodomesticos");
			System.out.println("L - Limpieza");
			System.out.println("Ingrese : ");
			rubro = super.getSc().next().charAt(0);
			rubro = Character.toUpperCase(rubro);
		} while (rubro != 'A' && rubro != 'E' && rubro != 'L');
		return rubro;
	}

	private void editarArticulo() throws ExcepcionDeIngresoDeDatos {
		try {
			listaArticulos.mostrar();
			System.out.println("----------------");
			System.out.println("Ingrese el ID del Articulo para modificar: ");
			int respuesta = super.getSc().nextInt();
			Articulo articuloparaModificar = listaArticulos.buscarArticulo(respuesta);
			if(articuloparaModificar != null ) {
				MenuEditarArticulo editar = new MenuEditarArticulo(super.getSc(), articuloparaModificar);
				editar.iniciar();
			}else {
				System.out.println();
				System.out.println("No se encontro el articulo a modificar");
			}

		} catch (Exception e) {
			throw new ExcepcionDeIngresoDeDatos("Error! El id ingresado no coincide con ningun articulo.");
		}
	}
	
	private void eliminarArticulo() throws ExcepcionDeIngresoDeDatos {
		try {
			listaArticulos.mostrar();
			System.out.println("----------------");
			System.out.println("Ingrese el ID del Articulo para eliminar: ");
			int respuesta = super.getSc().nextInt();
			Articulo articuloparaEliminar = listaArticulos.buscarArticulo(respuesta);
			listaArticulos.remover(articuloparaEliminar);
			System.out.println("Articulo eliminado con exito!");
		} catch (Exception e) {
			throw new ExcepcionDeIngresoDeDatos("Error! El id ingresado no coincide con ningun articulo.");
		}
	}

	private void mostrarArticulos() {
		if (listaArticulos.estaVacio()) {
			System.out.println("Sin articulo para mostrar.");
		} else {
			for (Articulo articulo : listaArticulos.getElementos()) {
				System.out.println(articulo);
			}
		}
	}


	private void verCantidadStockPorArticulo() {
		for (Articulo articulo : listaArticulos.getElementos()) {
			System.out.println(articulo.getNombreArticulo());
			System.out.println(articulo.getStockArticulo());
			System.out.println("-----------");
		}

	}
}
