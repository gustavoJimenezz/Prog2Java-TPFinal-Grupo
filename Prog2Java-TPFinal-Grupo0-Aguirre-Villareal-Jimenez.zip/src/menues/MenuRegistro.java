package menues;

import java.util.Scanner;

import contenedores.ContenedorUsuarios;
import exceptions.ExcepcionDeIngresoDeDatos;
import models.Cliente;
import models.Empleado;
import models.Usuario;

public class MenuRegistro extends Menu {
	public static String CLAVE_EMPLEADO = "pepepiola123";
	private ContenedorUsuarios listaUsuarios;

	public MenuRegistro(Scanner sc, ContenedorUsuarios usuarios) {
		super(sc);
		listaUsuarios = usuarios;
	}

	@Override
	public String opciones() {
		System.out.println();
		System.out.println(" ** Registrar Usuario ** ");
		System.out.println("1. Cliente");
		System.out.println("2. Empleado");
		System.out.println("0. Volver Atras");
		System.out.print("Ingrese : ");
		return super.getSc().next();
	}

	@Override
	public void procesarOpcion(String opcionElegida) throws ExcepcionDeIngresoDeDatos {

		switch (opcionElegida) {
		case "1":
			registrarCliente();
			break;
		case "2":
			registrarEmpleado();
			break;
		case "0":
			super.finalizar();
			break;
		default:
			System.out.println("Opcion invalida.");
			break;
		}
	}

	private void registrarCliente() throws ExcepcionDeIngresoDeDatos {
		try {

			String nuevoNombre = pedirNombreUsuario();
			String nuevaContra = pedirContra();
			int id = listaUsuarios.proximoId();

			if (nombreUsuarioValido(nuevoNombre) && confirmarContra(nuevaContra)) {
				Cliente nuevoCliente = new Cliente(id, nuevoNombre, nuevaContra);
				listaUsuarios.agregar(nuevoCliente);
				System.out.println("Cliente agregado !");
			} else {
				System.out.println("No se registro Cliente ");
			}
			super.finalizar();
		} catch (Exception e) {
			throw new ExcepcionDeIngresoDeDatos("Error al registrar ! ");
		}

	}

	private void registrarEmpleado() throws ExcepcionDeIngresoDeDatos {
		try {
			if(pedirClaveEmpleado()) {
				String nuevoNombre = pedirNombreUsuario();
				String nuevaContra = pedirContra();
				int id = listaUsuarios.proximoId();

				if (nombreUsuarioValido(nuevoNombre) && confirmarContra(nuevaContra)) {
					Empleado nuevoEmpleado = new Empleado(id, nuevoNombre, nuevaContra);
					listaUsuarios.agregar(nuevoEmpleado);
					System.out.println("Empleado agregado !");
				} else {
					System.out.println("No se registro empleado");
				}
			}
			super.finalizar();
		} catch (Exception e) {
			throw new ExcepcionDeIngresoDeDatos("Error al iniciar sesion ! ");
		}

	}
	
	private String pedirContra() {
		System.out.println("Ingrese Contrasenia: ");
		return super.getSc().next();
	}

	private String pedirNombreUsuario() {
		System.out.println("Ingrese Nombre de Usuario:");
		return super.getSc().next();
	}

	private boolean confirmarContra(String contra) {
		boolean contraConfirmada = false;
		System.out.println("Ingrese nuevamente su Contrasenia");
		String contraConfirmar = super.getSc().next();
		if (contra.equals(contraConfirmar)) {
			contraConfirmada = true;
		}else {
			System.out.println("Las contraseña no confirmada");
		}
		return contraConfirmada;
	}

	private boolean nombreUsuarioValido(String nombre) {
		boolean valido = true;

		if (!nombre.isEmpty()) {
			for (Usuario usr : listaUsuarios.getElementos()) {
				if (usr.getNombreUsuario().equals(nombre)) {
					valido = false;
				}
			}
		} else {
			System.out.println("No se ingreso nombre ! ");
		}

		if (!valido) {
			System.out.println("Nombre ya registrado ! ");
		}

		return valido;
	}
	
	private boolean pedirClaveEmpleado() {
		boolean claveValido = false;
		System.out.println("Ingrese Clave para registrar empleado : ");
		String claveExtra = super.getSc().next();
		if (claveExtra.equals(CLAVE_EMPLEADO)) {
			claveValido = true;
		}
		return claveValido;
	}

}
