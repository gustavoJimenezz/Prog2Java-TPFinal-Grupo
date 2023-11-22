package menues;

import java.util.ArrayList;
import java.util.Scanner;

import models.Articulo;
import models.Usuario;

public class MenuLogin {
	private Scanner sc;
	private ArrayList<Usuario> listaUsuarios;

	ArrayList<Articulo> listaArticulo;
	MenuEmpleado mEmpleado;
	MenuCliente mCliente;

	public MenuLogin(Scanner sc, ArrayList<Usuario> listaUsuarios) {
		super();
		this.sc = sc;
		this.listaUsuarios = listaUsuarios;
		listaArticulo = new ArrayList<Articulo>();
		mEmpleado = new MenuEmpleado(sc, listaArticulo);
		mCliente = new MenuCliente(sc, listaArticulo, listaUsuarios);
		
	}

	public Scanner getSc() {
		return sc;
	}

	public void setSc(Scanner sc) {
		this.sc = sc;
	}

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public void iniciar() {

		boolean continuar = true;

		while (continuar) {
			System.out.println("1. Registrar Usuario");
			System.out.println("2. Inicia sesi�n");
			System.out.println("0. Salir del Programa");
			int respuesta = sc.nextInt();

			switch (respuesta) {
			case 1:
				registarUsuario();
				break;
			case 2:
				iniciarSesionUsuario();
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

	private void iniciarSesionUsuario() {
		System.out.println("Ingrese nombre de usuario: ");
		String nombre = sc.next();
		System.out.println("Ingrese contrase�a de usuario");
		String contrasenia = sc.next();
		for (Usuario usuario : listaUsuarios) {
			if (nombre.equals(usuario.getNombreUsuario()) && contrasenia.equals(usuario.getContraseniaUsuario())) {
				System.out.println("Usted es: ");
				System.out.println(usuario.getTipoUsuario());
				if (usuario.getTipoUsuario() == 'E') {
					mEmpleado.iniciar();
				}
				if (usuario.getTipoUsuario() == 'C') {
					mCliente.iniciar(usuario);

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
			int respuesta = sc.nextInt();

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
		String nombre = sc.next();
		while (continuar) {
			System.out.println("Ingrese Clave Extra: ");
			String claveExtra = sc.next();
			if (claveExtra.equals("pepepiola123")) {
				System.out.println("Ingrese Contrase�a: ");
				String contrasenia = sc.next();
				System.out.println("Ingrese nuevamente su Contrase�a");
				String contraseniaConfirmar = sc.next();
				if (contrasenia.equals(contraseniaConfirmar)) {
					listaUsuarios.add(new Usuario(nombre, contrasenia, tipoUsuario));
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
		String nombre = sc.next();
		while (continuar) {
			System.out.println("Ingrese Contrase�a: ");
			String contrasenia = sc.next();
			System.out.println("Ingrese nuevamente su Contrase�a");
			String contraseniaConfirmar = sc.next();
			if (contrasenia.equals(contraseniaConfirmar)) {
				listaUsuarios.add(new Usuario(nombre, contrasenia, tipoUsuario));
				System.out.println("Usuario registrado con �xito.");
				break;

			} else {
				System.out.println("Error. Las Contrase�as no coinciden. Intentalo de nuevo.");
			}
		}

	}
}
