package menues;

import java.util.Scanner;
import contenedores.ContenedorArticulos;
import contenedores.ContenedorUsuarios;
import exceptions.excepcionDeIngresoDeDatos;
import models.Usuario;

public class MenuLogin extends Menu {
	private ContenedorUsuarios listaUsuarios;
	private ContenedorArticulos listaArticulos;
	private MenuEmpleado mEmpleado;
	private MenuCliente mCliente;

	public MenuLogin(Scanner sc, ContenedorUsuarios usuarios, ContenedorArticulos articulos) {
		super(sc);
		this.listaArticulos = articulos;
		this.listaUsuarios = usuarios;
		mEmpleado = new MenuEmpleado(super.getSc(), listaArticulos);
	}

	@Override
	public String opciones() throws excepcionDeIngresoDeDatos {
		System.out.println();
		System.out.println(" ** Menu Login **");
		System.out.println("1- Registrar Usuario");
		System.out.println("2- Inicia sesion");
		System.out.println("0- Salir del Programa");
		System.out.println("Ingrese : ");
		return super.getSc().next();
	}

	@Override
	public void procesarOpcion(String opcionElegida) throws excepcionDeIngresoDeDatos {
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
		default:
			System.out.println("Opcion invalida.");
			break;
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
				if (usuario.getTipoUsuario() == 'E') {
					mEmpleado.iniciar();
				}
				if (usuario.getTipoUsuario() == 'C') {
					mCliente = new MenuCliente(super.getSc(), listaArticulos, listaUsuarios, usuario);
					mCliente.iniciar();

				}
			} else {
				System.out.println("Nombre y/o contrase�a incorrecta/s.");
			}
		}

	}

	private void registarUsuario() {
		boolean continuar = true;

		while (continuar) {
			System.out.println("Que tipo de usuario desea registrar?");
			System.out.println("1. Cliente");
			System.out.println("2. Empleado");
			System.out.println("0. Volver Atr�s");
			int respuesta = super.getSc().nextInt();

			switch (respuesta) {
			case 1:
				registrarUsuarioCliente();
				break;
			case 2:
				registrarUsuarioEmpleado();
				break;
			case 0:
				continuar = false;
				break;

			default:
				System.out.println("Opci�n inv�lida.");
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
				System.out.println("Ingrese Contrase�a: ");
				String contrasenia = super.getSc().next();
				System.out.println("Ingrese nuevamente su Contrase�a");
				String contraseniaConfirmar = super.getSc().next();
				if (contrasenia.equals(contraseniaConfirmar)) {
					listaUsuarios.agregar(new Usuario(nombre, contrasenia, tipoUsuario));
					;
					System.out.println("Usuario registrado con �xito.");
					break;
				} else {
					System.out.println("Error. Las Contrase�as no coinciden. Intentalo de nuevo.");
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
			System.out.println("Ingrese Contrase�a: ");
			String contrasenia = super.getSc().next();
			System.out.println("Ingrese nuevamente su Contrase�a");
			String contraseniaConfirmar = super.getSc().next();
			if (contrasenia.equals(contraseniaConfirmar)) {
				listaUsuarios.agregar(new Usuario(nombre, contrasenia, tipoUsuario));
				System.out.println("Usuario registrado con �xito.");
				break;

			} else {
				System.out.println("Error. Las Contrase�as no coinciden. Intentalo de nuevo.");
			}
		}

	}
}
