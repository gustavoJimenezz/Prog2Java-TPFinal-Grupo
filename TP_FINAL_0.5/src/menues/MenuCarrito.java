package menues;

import java.util.Scanner;
import contenedores.ContenedorArticulos;
import contenedores.ContenedorCarrito;
import descuento.DescuentoArticulo;
import herramientas.Validador;
import models.Articulo;
import models.Usuario;

public class MenuCarrito {

	private boolean iniciar;
	private double compraMayorDescuento;
	private double porcentajeDescuento;
	private int stockDeseado;
	private Scanner sc;
	private ContenedorArticulos listaArticulos;
	private ContenedorCarrito myCarrito;
	private DescuentoArticulo gestorDeDescuento;

	public MenuCarrito(Scanner sc, ContenedorArticulos listaArticulos) {
		this.compraMayorDescuento = 12000;
		this.porcentajeDescuento = 15;
		this.stockDeseado = 10;
		this.sc = sc;
		this.listaArticulos = listaArticulos;
		myCarrito = new ContenedorCarrito();
		gestorDeDescuento = new DescuentoArticulo();
	}

	public void iniciar(Usuario clienteActual) {
		this.iniciar();
		while (this.isIniciar()) {
			System.out.println();
			System.out.println(" ** Carrito ** ");
			System.out.println("Importe actual : " + this.importeTotalFInal(clienteActual) + " $");
			
			System.out.println("1- Agregar articulo");
			System.out.println("2- Mostrar carrito");
			System.out.println("3- Finalizar compra");
			System.out.println("0- Salir");
			int opcionElegida = Validador.pedirEntero(this.sc, "Ingrese : ");

			switch (opcionElegida) {
			case 1:
				this.agregarArticulo(clienteActual);
				break;
			case 2:
				this.mostrarCarrito(clienteActual);
				break;
			case 3:
				this.finalizarCompra(clienteActual);
				break;
			case 0:
				this.finalizar();
				break;
			default:
				System.out.println("Opcion incorrecta !");
				break;
			}

		}

	}

	private void agregarArticulo(Usuario clienteActual) {
		this.listaArticulos.mostrar();
		int codigoABuscar = Validador.pedirEntero(this.sc, "Ingrese Codigo de Articulo : ");

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
		double totalFinal = this.importeTotalFInal(clienteActual);
		System.out.println();
		System.out.println("Total : " + totalFinal + " $");
		System.out.println("Saldo : " + clienteActual.getSaldo() + " $");

		System.out.print("Confirmar transaccion ? (si/no) : ");
		String respuesta = this.sc.next();

		if (respuesta.equalsIgnoreCase("si")) {
			this.facturar(totalFinal, clienteActual);
		} else {
			System.out.println("No se completo la transaccion !");
		}
	}
	
	private double importeTotalFInal(Usuario clienteActual) {
		return this.aplicarDescuentos(clienteActual);
	}

	private double aplicarDescuentos(Usuario clienteActual) {
		double subTotal = this.aplicarDescuentoPorTipo(clienteActual);
		double total = this.aplicarDescuentoPorCompraMayor(subTotal);
		return total;
	}

	private double aplicarDescuentoPorTipo(Usuario clienteActual) {
		double total = 0;
		for (Articulo articulo : this.myCarrito.getElementos()) {
//			1- subsidiados 2- pordemanda 3- simples
			double tipoDeDescuento = articulo.getTipoDeDescuento();
			if (tipoDeDescuento == 1) {
				total += gestorDeDescuento.aplicarDescuentoSubsidiados(articulo);
			} else if (tipoDeDescuento == 2) {
				System.out.println(total);
				total += gestorDeDescuento.aplicarDescuentoPorDemanda(articulo, this.stockDeseado);
				System.out.println(total);
			} else if (tipoDeDescuento == 3) {
				total += articulo.getPrecioArticulo();
			}
		}
		return total;
	}

	private double aplicarDescuentoPorCompraMayor(double subTotal) {
		double total = subTotal;
		if (subTotal > this.compraMayorDescuento) {
			total = gestorDeDescuento.descontarPorcentaje(subTotal, this.porcentajeDescuento);
		}
		return total;
	}


	private void facturar(double totalFinal, Usuario clienteActual) {
		if (this.clienteConSaldo(totalFinal, clienteActual)) {
//			se debera mostrar por pantalla los articulos comprados, el subtotal, el importe total descontado, y el total final
			this.mostrarCarrito(clienteActual);
			System.out.println("Subtotal : " + this.myCarrito.totalNeto());
			System.out.println("Total FInal : " + this.importeTotalFInal(clienteActual));
			System.out.println("Total descontado : " + this.importeDescontado(clienteActual));

			clienteActual.descontarSaldo(this.importeTotalFInal(clienteActual));
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

	private double importeDescontado(Usuario clienteActual) {
		return this.myCarrito.totalNeto() - this.importeTotalFInal(clienteActual);
	}

	public boolean isIniciar() {
		return this.iniciar;
	}

	public void finalizar() {
		this.iniciar = false;
	}
	
	public void iniciar() {
		this.iniciar = true;
	}
}
