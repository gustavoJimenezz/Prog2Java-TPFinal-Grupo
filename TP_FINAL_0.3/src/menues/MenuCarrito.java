package menues;

import java.util.ArrayList;
import java.util.Scanner;

import contenedores.Carrito;
import descuento.DescuentoArticulo;
import models.Articulo;
import models.Usuario;

public class MenuCarrito {

	private ArrayList<Articulo> listaArticulos;
	private Scanner sc;
	private boolean iniciar;
	private double compraMayorDescuento;
	private double porcentajeDescuento;
	private int stockDeseado;
	private Carrito myCarrito;
	private DescuentoArticulo gestorDeDescuento;

	public MenuCarrito(Scanner sc, ArrayList<Articulo> lista) {
		this.listaArticulos = lista;
		this.sc = sc;
		this.iniciar = true;
		this.compraMayorDescuento = 12000;
		this.porcentajeDescuento = 15;
		this.stockDeseado = 80;
		myCarrito = new Carrito();
		gestorDeDescuento = new DescuentoArticulo();
	}

	public void iniciar(Usuario clienteActual) {
		System.out.println(" ** Carrito ** ");
		while (this.isIniciar()) {
			System.out.println("1- Agregar articulo");
			System.out.println("2- Mostrar carrito");
			System.out.println("3- Finalizar compra");
			System.out.println("0- Salir");
			System.out.print("Ingrese : ");
			int opcionElegida = this.sc.nextInt();

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
		this.mostrarCarrito(clienteActual);
		System.out.print("Ingrese Codigo de Articulo : ");
		int codigoABuscar = this.sc.nextInt();

		Articulo articuloBuscado = this.buscarArticulo(codigoABuscar);

		if (articuloBuscado != null) {
			this.agregarArticuloSiTieneStock(articuloBuscado, clienteActual);
		} else {
			System.out.println("No se encontro el Articulo !");
		}

	}

	private void mostrarCarrito(Usuario clienteActual) {
		this.myCarrito.mostrarArticulosDelCarrito();
	}

	private void agregarArticuloSiTieneStock(Articulo art, Usuario cliente) {
		if (art.sinStock()) {
			this.myCarrito.agregarArticulo(art);
//			probar si disminuye el stock del array de lista
			art.disminuirStock();
		} else {
			System.out.println("Articulo Sin Stock");
		}
	}

	private void finalizarCompra(Usuario clienteActual) {
//		cuando el usuario desee finalizar, se debera mostrar al usuario cuanto se va a gastar y su saldo 
		double totalFinal = this.importeTotalFInal(clienteActual);
		System.out.println("Total : " + totalFinal);
		System.out.println("Saldo : " + clienteActual.getSaldo());

		System.out.print("Confirmar transaccion ? (si/no) : ");
		String respuesta = this.sc.next();

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
			System.out.println("Total FInal : " + this.importeTotalFInal(clienteActual));
			System.out.println("Total descontado : " + this.importeDescontado(clienteActual));

			clienteActual.descontarSaldo(this.importeTotalFInal(clienteActual));
			this.myCarrito.limpiarCarrito();
		}
	}

	private boolean clienteConSaldo(double totalFinal, Usuario clienteActual) {
		boolean respuesta = true;
//		si el usuario no tiene saldo suficiente, no podra concretar la compra 
		if (clienteActual.getSaldo() < totalFinal) {
			
			System.out.println("Saldo insuficiente");
			respuesta = false;
		}
		return respuesta;
	}

	private double importeDescontado(Usuario clienteActual) {
		return this.myCarrito.totalNeto() - this.importeTotalFInal(clienteActual);
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
		for (Articulo articulo : this.myCarrito.getArticulos()) {
//			1- subsidiados 2- pordemanda 3- simples
			double tipoDeDescuento = articulo.getTipoDeDescuento();
			if (tipoDeDescuento == 1) {
				total += gestorDeDescuento.aplicarDescuentoSubsidiados(articulo);
			} else if (tipoDeDescuento == 2) {
				total += gestorDeDescuento.aplicarDescuentoPorDemanda(articulo, this.stockDeseado);
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

	private Articulo buscarArticulo(int codigo) {
		Articulo artAux = null;
		for (Articulo articulo : listaArticulos) {
			if (articulo.getIdArticulo() == codigo) {
				artAux = articulo;
				break;
			}
		}
		return artAux;
	}

	public boolean isIniciar() {
		return this.iniciar;
	}

	public void finalizar() {
		this.iniciar = false;
	}
}
