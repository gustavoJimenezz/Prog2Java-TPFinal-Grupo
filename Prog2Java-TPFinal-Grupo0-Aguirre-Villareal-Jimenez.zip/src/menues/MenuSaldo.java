package menues;

import java.util.Scanner;
import contenedores.ContenedorUsuarios;
import exceptions.ExcepcionDeIngresoDeDatos;
import models.Cliente;

public class MenuSaldo extends Menu {
	Cliente clienteActual;
	ContenedorUsuarios listaUsuarios;

	public MenuSaldo(Scanner sc, Cliente cliente, ContenedorUsuarios usuarios) {
		super(sc);
		this.clienteActual = cliente;
		listaUsuarios = usuarios;
	}

	@Override
	public String opciones() {
		System.out.println();
		System.out.println(" ** Modulo de Saldo **");
		System.out.println("1- Agregar Saldo");
		System.out.println("2- Retirar Saldo");
		System.out.println("3- Transferir Saldo");
		System.out.println("0- Volver al Menú Principal");
		System.out.println("Ingrese : ");
		return super.getSc().next();
	}

	@Override
	public void procesarOpcion(String opcionElegida) throws ExcepcionDeIngresoDeDatos {
		switch (opcionElegida) {
		case "1":
			this.agregarSaldo(clienteActual);
			break;
		case "2":
			this.retirarSaldo(clienteActual);
			break;
		case "3":
			this.trasnferirSaldo(clienteActual);
			break;
		case "0":
			super.finalizar();
			break;
		default:
			System.out.println("Opcion incorrecta");
			break;

		}
	}

	private void agregarSaldo(Cliente clienteActual) throws ExcepcionDeIngresoDeDatos {
		try {
			System.out.println("Ingrese el monto que desea agregar:");
			double monto = super.getSc().nextDouble();
			clienteActual.agregarSaldo(monto);
			System.out.println("Saldo agregado. Nuevo saldo : " + clienteActual.getSaldo());
		} catch (Exception e) {
			throw new ExcepcionDeIngresoDeDatos("Error al agregar saldo !");
		}

	}
	
	private void trasnferirSaldo(Cliente clienteActual) throws ExcepcionDeIngresoDeDatos {
		try {
			System.out.println("Ingrese el nombre del usuario destinatario");
			String nombreDestinatario = super.getSc().next();
			Cliente destinatario = (Cliente) listaUsuarios.buscarUsuarioPorNombre(nombreDestinatario);
			if (destinatario != null) {
				System.out.println("Ingrese el monto a transferir: ");
				double monto = super.getSc().nextDouble();
				clienteActual.transferirSaldo(monto, destinatario);
				System.out.println("Saldo transferido con exito. Nuevo saldo : " + clienteActual.getSaldo());
			} else {
				System.out.println("Usuario no encontrado");
			}
		} catch (Exception e) {
			throw new ExcepcionDeIngresoDeDatos("Error al transferir saldo !");
		}
	}

	private void retirarSaldo(Cliente clienteActual) throws ExcepcionDeIngresoDeDatos {
		try {
			System.out.println("Ingrese el monto a retirar: ");
			double monto = super.getSc().nextDouble();
			clienteActual.retirarSaldo(monto);
			System.out.println("Saldo retirado. Nuevo saldo : " + clienteActual.getSaldo());
		} catch (Exception e) {
			throw new ExcepcionDeIngresoDeDatos("Error al retirar saldo !");
		}
	}



}
