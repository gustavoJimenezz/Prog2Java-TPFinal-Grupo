package menues;

import java.util.Scanner;
import contenedores.ContenedorArticulos;
import contenedores.ContenedorUsuarios;
import exceptions.excepcionDeIngresoDeDatos;
import models.Usuario;

public class MenuCliente extends Menu {

	ContenedorArticulos listaArticulos;
	Usuario clienteActual;
	private ContenedorUsuarios listaUsuarios;
	private MenuSaldo menuSaldo;
	private MenuCarrito menuCarrito;

	public MenuCliente(Scanner sc, ContenedorArticulos listaArticulos, ContenedorUsuarios listaUsuarios, Usuario cliente) {
		super(sc);
		this.listaArticulos = listaArticulos;
		clienteActual = cliente;
	}

	@Override
	public String opciones() throws excepcionDeIngresoDeDatos {
		System.out.println();
		System.out.println(" ** Menu Cliente **");
		System.out.println("1- Modulo de saldo");
		System.out.println("2- Carrito");
		System.out.println("0- Salir");
		System.out.println("Ingrese : ");
		return super.getSc().next();
	}
	
	@Override
	public void procesarOpcion(String opcionElegida) throws excepcionDeIngresoDeDatos{
		switch (opcionElegida) {
		case "1":
			menuSaldo = new MenuSaldo(super.getSc(), clienteActual, listaUsuarios);
			menuSaldo.iniciar();
			break;
		case "2":
			menuCarrito = new MenuCarrito(super.getSc(), listaArticulos, clienteActual);
			this.menuCarrito.iniciar();
			break;
		case "0":
			this.finalizar();
			break;
		default:
			System.out.println("Opcion incorrecta !");
			break;
		}
	}	
}
