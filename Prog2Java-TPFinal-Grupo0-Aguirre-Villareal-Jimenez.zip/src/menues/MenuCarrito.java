package menues;

import java.util.Scanner;
import contenedores.ContenedorArticulos;
import contenedores.ContenedorCarrito;
import exceptions.ExcepcionDeIngresoDeDatos;
import herramientas.FormatearDecimal;
import models.Articulo;
import models.Cliente;

public class MenuCarrito extends Menu {
	private Cliente clienteActual;
	private ContenedorArticulos listaArticulos;
	private ContenedorCarrito myCarrito;

	public MenuCarrito(Scanner sc, ContenedorArticulos listaArticulos, Cliente cliente) {
		super(sc);
		this.listaArticulos = listaArticulos;
		clienteActual = cliente;
		myCarrito = new ContenedorCarrito();
	}

	@Override
	public String opciones() {
		System.out.println();
		System.out.println(" ** Carrito ** ");
		System.out.println("Importe actual : " + this.importeTotalFInal() + " $");
		System.out.println("1- Agregar articulo");
		System.out.println("2- Agregar articulo");
		System.out.println("3- Mostrar carrito");
		System.out.println("4- Finalizar compra");
		System.out.println("0- Salir");
		System.out.println("Ingrese : ");
		return super.getSc().next();
	}

	@Override
	public void procesarOpcion(String opcionElegida) throws ExcepcionDeIngresoDeDatos {
		switch (opcionElegida) {
		case "1":
			this.agregarArticulo();
			break;
		case "2":
			this.sacarArticulo();
			break;
		case "3":
			this.mostrarCarrito();
			break;
		case "4":
			this.finalizarCompra();
			break;
		case "0":
			this.finalizar();
			break;
		default:
			System.out.println("Opcion incorrecta !");
			break;
		}
	}

	private void agregarArticulo() throws ExcepcionDeIngresoDeDatos {
		try {
			if (this.mostrarCarrito()) {
				System.out.print("Ingrese ID : ");
				int codigoABuscar = super.getSc().nextInt();

				Articulo articuloBuscado = this.listaArticulos.buscarArticulo(codigoABuscar);

				if (articuloBuscado != null) {
					System.out.print("Ingrese cantidad : ");
					int cantidadAComprar = super.getSc().nextInt();
					if (this.hayStock(articuloBuscado, cantidadAComprar)) {
						this.myCarrito.agregarAlCarrito(articuloBuscado, cantidadAComprar);
						System.out.println();
						System.out.println("Articulo agregado con exito !");
					}
				} else {
					System.out.println();
					System.out.println("No se encontro el Articulo !");
				}
			}
		} catch (Exception e) {
			throw new ExcepcionDeIngresoDeDatos("No se pudo agregar el articulo al carrito.");
		}

	}

	private boolean hayStock(Articulo art, int cantidadAComprar) {
		boolean tieneStock = true;
		if (!art.hayStock(cantidadAComprar)) {
			tieneStock = false;
			System.out.println("Stock insuficiente !");
		}
		return tieneStock;
	}

	private void sacarArticulo() throws ExcepcionDeIngresoDeDatos {
//		ACA descontar por cantidad 

		try {
			if (this.mostrarCarrito()) {
				System.out.print("Ingrese ID : ");
				int codigoABuscar = super.getSc().nextInt();
				Articulo articuloBuscado = this.myCarrito.buscarPorId(codigoABuscar);
				if (articuloBuscado != null) {
					this.myCarrito.remover(articuloBuscado);
				} else {
					System.out.println("No se encontro el ariculo.");
				}
			}
		} catch (Exception e) {
			throw new ExcepcionDeIngresoDeDatos("No se pudo sacar el articulo del carrito.");
		}
	}

	private boolean mostrarCarrito() {
		boolean carritoVacio;
		if (!this.myCarrito.carritoVacio()) {
			carritoVacio = true;
			System.out.println();
			System.out.println(" * Articulos de Carrito * ");
			this.myCarrito.mostrarCarrito();
		} else {
			carritoVacio = false;
			System.out.println("Sin articulos en el carrito.");
		}
		return carritoVacio;
	}

	private void finalizarCompra() {
//		cuando el usuario desee finalizar, se debera mostrar al usuario cuanto se va a gastar y su saldo 
		double totalFinal = this.importeTotalFInal();
		System.out.println();
		System.out.println("Total : " + totalFinal + " $");
		System.out.println("Saldo : " + this.clienteActual.getSaldo() + " $");

		System.out.print("Confirmar transaccion ? (si/no) : ");
		String respuesta = super.getSc().next();

		if (respuesta.equalsIgnoreCase("si")) {
			this.facturar(totalFinal);
		} else {
			System.out.println("No se completo la transaccion !");
		}
	}

	private void facturar(double totalFinal) {
		if (this.clienteConSaldo(totalFinal)) {
//			se debera mostrar por pantalla los articulos comprados, el subtotal, el importe total descontado, y el total final
			this.mostrarCarrito();
			System.out.println("Subtotal : " + this.myCarrito.totalNeto());
			System.out.println("Total FInal : " + this.importeTotalFInal());
			System.out.println("Total descontado : " + this.importeDescontado());

			this.descontarStock();
			this.clienteActual.descontarSaldo(this.importeTotalFInal());
			this.myCarrito.vaciar();
		}
	}

	private boolean clienteConSaldo(double totalFinal) {
		boolean respuesta = true;
//		si el usuario no tiene saldo suficiente, no podra concretar la compra 
		if (this.clienteActual.getSaldo() < totalFinal) {
			System.out.println();
			System.out.println("Saldo insuficiente");
			respuesta = false;
		}
		return respuesta;
	}

	private void descontarStock() {
		for (Articulo art : listaArticulos.getElementos()) {
			int stockAdescontar = myCarrito.obtenerCantDeArticulo(art);
			art.descontarStock(stockAdescontar);
		}
	}

	private double importeTotalFInal() {
		double totalFinal = this.myCarrito.totalFinal();
		return FormatearDecimal.formatearConTresDecimales(totalFinal);
	}

	private double importeDescontado() {
		return this.myCarrito.totalNeto() - this.importeTotalFInal();
	}
}
