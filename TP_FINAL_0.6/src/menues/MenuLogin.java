package menues;

import java.util.Scanner;

import contenedores.ContenedorArticulos;
import contenedores.ContenedorUsuarios;
import exceptions.ExcepcionDeIngresoDeDatos;
import models.Usuario;

public class MenuLogin extends Menu {
	private ContenedorUsuarios listaUsuarios;
	private ContenedorArticulos listaArticulos;
	private MenuEmpleado mEmpleado;
	private MenuCliente mCliente;
	Usuario usuarioActual = null;

	public MenuLogin(Scanner sc, ContenedorUsuarios usuarios, ContenedorArticulos articulos) {
		super(sc);
		this.listaArticulos = articulos;
		this.listaUsuarios = usuarios;
		mEmpleado = new MenuEmpleado(super.getSc(), listaArticulos);
	}

	@Override
	public String opciones() throws ExcepcionDeIngresoDeDatos {
		System.out.println();
		System.out.println(" ** Menu Login **");
		System.out.println("1- Registrar Usuario");
		System.out.println("2- Inicia sesion");
		System.out.println("3- Cerrar Sesion");
		System.out.println("0- Salir del Programa");
		System.out.println("Ingrese : ");
		return super.getSc().next();
	}

	@Override
	public void procesarOpcion(String opcionElegida) throws ExcepcionDeIngresoDeDatos {
		switch (opcionElegida) {
		case "1":
			registarUsuario();
			break;
		case "2":
			iniciarSesionUsuario();
			break;
		case "0":
			super.finalizar();
			break;
		case "3":
			cerrarSesionUsuario();
			break;
		default:
			System.out.println("Opcion invalida.");
			break;
		}
	}

	private void cerrarSesionUsuario() {
		if(usuarioActual != null) {
			System.out.println("Saliendo de " + usuarioActual.getNombreUsuario() );
			usuarioActual = null;
		}else {
			System.out.println("No hay usuario actual para salir.");
		}
		
	}

	private void iniciarSesionUsuario() {
		System.out.println("Ingrese nombre de usuario: ");
		String nombre = super.getSc().next();
		System.out.println("Ingrese contrasenia de usuario");
		String contrasenia = super.getSc().next();
		for (Usuario usuario : listaUsuarios.getElementos()) {
			if (nombre.equals(usuario.getNombreUsuario()) && contrasenia.equals(usuario.getContraseniaUsuario())) {
				System.out.println("Usted es: ");
				System.out.println(usuario.getTipoUsuario());
				usuarioActual = usuario;
				if (usuario.getTipoUsuario() == 'E') {
					mEmpleado.iniciar();
				}
				if (usuario.getTipoUsuario() == 'C') {
					mCliente = new MenuCliente(super.getSc(), listaArticulos, listaUsuarios, usuario);
					mCliente.iniciar();

				}
			} else {
				System.out.println("Nombre y/o contrasenia incorrecta/s.");
			}
		}

	}

	private void registarUsuario() {
		boolean continuar = true;

		while (continuar) {
			System.out.println("Que tipo de usuario desea registrar?");
			System.out.println("1. Cliente");
			System.out.println("2. Empleado");
			System.out.println("0. Volver Atras");
			String respuesta = super.getSc().next();

			switch (respuesta) {
			case "1":
				registrarUsuarioCliente();
				break;
			case "2":
				registrarUsuarioEmpleado();
				break;
			case "0":
				continuar = false;
				break;

			default:
				System.out.println("Opcion invalida.");
				break;
			}
		}

	}

	private void registrarUsuarioEmpleado() {
		boolean continuar = true;
		char tipoUsuario = 'E';
		System.out.println("Ingrese Nombre de Usuario:");
		String nombre = super.getSc().next();
		while (continuar) {
			System.out.println("Ingrese Clave Extra: ");
			String claveExtra = super.getSc().next();
			if (claveExtra.equals("pepepiola123")) {
				System.out.println("Ingrese Contrasenia: ");
				String contrasenia = super.getSc().next();
				System.out.println("Ingrese nuevamente su Contrasenia");
				String contraseniaConfirmar = super.getSc().next();
				if (contrasenia.equals(contraseniaConfirmar)) {
					listaUsuarios.agregar(new Usuario(nombre, contrasenia, tipoUsuario));
					;
					System.out.println("Usuario registrado con exito.");
					break;
				} else {
					System.out.println("Error. Las Contrasenias no coinciden. Intentalo de nuevo.");
				}
			} else {
				System.out.println("Error. La Clave Extra no coincide.");
			}

		}

	}

	private void registrarUsuarioCliente() {
		boolean continuar = true;
		char tipoUsuario = 'C';
		System.out.println("Ingrese Nombre de Usuario:");
		String nombre = super.getSc().next();
		while (continuar) {
			System.out.println("Ingrese Contrasenia: ");
			String contrasenia = super.getSc().next();
			System.out.println("Ingrese nuevamente su Contrasenia");
			String contraseniaConfirmar = super.getSc().next();
			if (contrasenia.equals(contraseniaConfirmar)) {
				listaUsuarios.agregar(new Usuario(nombre, contrasenia, tipoUsuario));
				System.out.println("Usuario registrado con exito.");
				break;

			} else {
				System.out.println("Error. Las Contrasenias no coinciden. Intentalo de nuevo.");
			}
		}

	}
}
