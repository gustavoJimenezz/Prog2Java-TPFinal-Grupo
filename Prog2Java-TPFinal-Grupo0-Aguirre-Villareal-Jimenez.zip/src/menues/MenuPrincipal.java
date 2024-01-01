package menues;

import java.util.Scanner;

import contenedores.ContenedorArticulos;
import contenedores.ContenedorUsuarios;
import exceptions.ExcepcionDeIngresoDeDatos;
import models.Cliente;
import models.Empleado;
import models.Usuario;

public class MenuPrincipal extends Menu {
	private ContenedorUsuarios listaUsuarios;
	private ContenedorArticulos listaArticulos;
	private MenuEmpleado mEmpleado;
	private MenuCliente mCliente;
	Usuario usuarioActual = null;

	public MenuPrincipal(Scanner sc, ContenedorUsuarios usuarios, ContenedorArticulos articulos) {
		super(sc);
		this.listaArticulos = articulos;
		this.listaUsuarios = usuarios;
	}

	@Override
	public String opciones() {
		System.out.println();
		System.out.println(" ** Menu Principal **");
		System.out.println("1- Registrar Usuario");
		System.out.println("2- Inicia sesion");
		System.out.println("3- Cerrar Sesion");
		System.out.println("0- Salir del Programa");
		System.out.print("Ingrese : ");
		return super.getSc().next();
	}

	@Override
	public void procesarOpcion(String opcionElegida) throws ExcepcionDeIngresoDeDatos {
		switch (opcionElegida) {
		case "1":
			registarUsuario();
			break;
		case "2":
			iniciarSesion();
			break;
		case "3":
			cerrarSesionUsuario();
			break;
		case "0":
			super.finalizar();
			break;
		default:
			System.out.println("Opcion invalida.");
			break;
		}
	}

	private void registarUsuario() {
		MenuRegistro registro = new MenuRegistro(super.getSc(), listaUsuarios);
		registro.iniciar();
	}

	private void cerrarSesionUsuario() {
		if (usuarioActual != null) {
			System.out.println("Saliendo de " + usuarioActual.getNombreUsuario());
			usuarioActual = null;
		} else {
			System.out.println("No hay usuario actual para salir.");
		}

	}

	private void iniciarSesion() throws ExcepcionDeIngresoDeDatos {
		try {
			Usuario usr = login();
			if (usr != null) {
				System.out.println("Bienvenido " + usr.getNombreUsuario());
				this.iniciarSesion(usr);
			} else {
				System.out.println("Nombre y/o contrasenia incorrecta/s.");
			}

		} catch (Exception e) {
			throw new ExcepcionDeIngresoDeDatos("Erro al iniciar sesion ! ");
		}
	}

	private Usuario login() {
		System.out.println("Ingrese nombre de usuario: ");
		String nombre = super.getSc().next();
		System.out.println("Ingrese contrasenia de usuario");
		String contrasenia = super.getSc().next();
		return listaUsuarios.buscarUsuarioPorCredenciales(nombre, contrasenia);
	}

	private void iniciarSesion(Usuario usr) {
		if (usr instanceof Empleado) {
			mEmpleado = new MenuEmpleado(super.getSc(), listaArticulos);
			mEmpleado.iniciar();
		} else if (usr instanceof Cliente) {
			mCliente = new MenuCliente(super.getSc(), listaArticulos, listaUsuarios, (Cliente) usr);
			mCliente.iniciar();

		}
	}
}
