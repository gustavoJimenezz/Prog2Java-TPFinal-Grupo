package menues;

import java.util.Scanner;
import contenedores.ContenedorArticulos;
import contenedores.ContenedorCarrito;
import exceptions.ExcepcionDeIngresoDeDatos;
import models.Articulo;
import models.Usuario;

public class MenuCarrito extends Menu{
	private double compraMayorDescuento;
	private double porcentajeDescuento;
	private Usuario clienteActual;
	private ContenedorArticulos listaArticulos;
	private ContenedorCarrito myCarrito;

	public MenuCarrito(Scanner sc, ContenedorArticulos listaArticulos, Usuario cliente) {
		super(sc);
		this.compraMayorDescuento = 12000;
		this.porcentajeDescuento = 15;
		this.listaArticulos = listaArticulos;
		clienteActual = cliente;
		myCarrito = new ContenedorCarrito();
	}
	
	@Override
	public String opciones() throws ExcepcionDeIngresoDeDatos {
		System.out.println();
		System.out.println(" ** Carrito ** ");
		System.out.println("Importe actual : " + this.importeTotalFInal() + " $");
		System.out.println("1- Agregar articulo");
		System.out.println("2- Mostrar carrito");
		System.out.println("3- Finalizar compra");
		System.out.println("0- Salir");
		System.out.println("Ingrese : ");
		return super.getSc().next();
	}
	
	@Override
	public void procesarOpcion(String opcionElegida) throws ExcepcionDeIngresoDeDatos{
		switch (opcionElegida) {
		case "1":
			this.agregarArticulo(clienteActual);
			break;
		case "2":
			this.mostrarCarrito(clienteActual);
			break;
		case "3":
			this.finalizarCompra(clienteActual);
			break;
		case "0":
			this.finalizar();
			break;
		default:
			System.out.println("Opcion incorrecta !");
			break;
		}
	}

	private void agregarArticulo(Usuario clienteActual) {
		this.listaArticulos.mostrar();
		int codigoABuscar = super.getSc().nextInt();

		Articulo articuloBuscado = this.listaArticulos.buscarArticulo(codigoABuscar);

		if (articuloBuscado != null && this.hayStock(articuloBuscado)) {
			this.myCarrito.agregar(articuloBuscado);
			articuloBuscado.disminuirStock();
			System.out.println();
			System.out.println("Articulo agregado con exito !");
		} else {
			System.out.println();
			System.out.println("No se encontro el Articulo !");
		}

	}

	private boolean hayStock(Articulo art) {
		boolean tieneStock = true;
		if (!art.hayStock()) {
			tieneStock = false;
			System.out.println("Articulo Sin Stock !");
		}
		return tieneStock;
	}

	private void mostrarCarrito(Usuario clienteActual) {
		System.out.println();
		this.myCarrito.mostrar();
	}

	private void finalizarCompra(Usuario clienteActual) {
//		cuando el usuario desee finalizar, se debera mostrar al usuario cuanto se va a gastar y su saldo 
		double totalFinal = this.importeTotalFInal();
		System.out.println();
		System.out.println("Total : " + totalFinal + " $");
		System.out.println("Saldo : " + clienteActual.getSaldo() + " $");

		System.out.print("Confirmar transaccion ? (si/no) : ");
		String respuesta = super.getSc().next();

		if (respuesta.equalsIgnoreCase("si")) {
			this.facturar(totalFinal, clienteActual);
		} else {
			System.out.println("No se completo la transaccion !");
		}
	}

	private void facturar(double totalFinal, Usuario clienteActual) {
		if (this.clienteConSaldo(totalFinal, clienteActual)) {
//			se debera mostrar por pantalla los articulos comprados, el subtotal, el importe total descontado, y el total final
			this.mostrarCarrito(clienteActual);
			System.out.println("Subtotal : " + this.myCarrito.totalNeto());
			System.out.println("Total FInal : " + this.importeTotalFInal());
			System.out.println("Total descontado : " + this.importeDescontado());

			clienteActual.descontarSaldo(this.importeTotalFInal());
			this.myCarrito.vaciar();
		}
	}

	private boolean clienteConSaldo(double totalFinal, Usuario clienteActual) {
		boolean respuesta = true;
//		si el usuario no tiene saldo suficiente, no podra concretar la compra 
		if (clienteActual.getSaldo() < totalFinal) {
			System.out.println();
			System.out.println("Saldo insuficiente");
			respuesta = false;
		}
		return respuesta;
	}
	
	private double importeTotalFInal() {
		return this.myCarrito.totalFinal();
	}
	
	private double importeDescontado() {
		return this.myCarrito.totalNeto() - this.importeTotalFInal();
	}
}
