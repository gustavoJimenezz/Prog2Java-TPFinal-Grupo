package menues;

import java.util.Scanner;
import contenedores.ContenedorArticulos;
import contenedores.ContenedorUsuarios;
import exceptions.ExcepcionDeIngresoDeDatos;
import models.Cliente;

public class MenuCliente extends Menu {

	ContenedorArticulos listaArticulos;
	Cliente clienteActual;
	private ContenedorUsuarios listaUsuarios;
	private MenuSaldo menuSaldo;
	private MenuCarrito menuCarrito;

	public MenuCliente(Scanner sc, ContenedorArticulos listaArticulos, ContenedorUsuarios listaUsuarios, Cliente cliente) {
		super(sc);
		this.listaArticulos = listaArticulos;
		clienteActual = cliente;
	}

	@Override
	public String opciones(){
		System.out.println();
		System.out.println(" ** Menu Cliente **");
		System.out.println("1- Modulo de saldo");
		System.out.println("2- Carrito");
		System.out.println("0- Salir");
		System.out.println("Ingrese : ");
		return super.getSc().next();
	}
	
	@Override
	public void procesarOpcion(String opcionElegida) throws ExcepcionDeIngresoDeDatos{
		switch (opcionElegida) {
		case "1":
			this.menuSaldo();
			break;
		case "2":
			this.menuCarrito();
			break;
		case "0":
			this.finalizar();
			break;
		default:
			System.out.println("Opcion incorrecta !");
			break;
		}
	}

	private void menuSaldo() {
		menuSaldo = new MenuSaldo(super.getSc(), clienteActual, listaUsuarios);
		menuSaldo.iniciar();
	}	

	private void menuCarrito() {
		menuCarrito = new MenuCarrito(super.getSc(), listaArticulos, clienteActual);
		this.menuCarrito.iniciar();		
	}
}
