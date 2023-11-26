package menues;

import java.util.Scanner;

import contenedores.ContenedorUsuarios;
import exceptions.excepcionDeIngresoDeDatos;
import models.Usuario;

public class MenuSaldo extends Menu{
	Usuario clienteActual;
	ContenedorUsuarios listaUsuarios;
	
	public MenuSaldo (Scanner sc, Usuario cliente, ContenedorUsuarios usuarios) {
		super(sc);
		this.clienteActual = cliente;
		listaUsuarios = usuarios;
	}
	
	@Override
	public String opciones() throws excepcionDeIngresoDeDatos {
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
	public void procesarOpcion(String opcionElegida) throws excepcionDeIngresoDeDatos{
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
        	//volver al menu principal
        	break;
        	default :
        		System.out.println("Opcion incorrecta");
        		break;
        	
        }
	}

	private void trasnferirSaldo(Usuario clienteActual) {
		System.out.println("Ingrese el nombre del usuario destinatario");
		String nombreDestinatario = super.getSc().next();
		Usuario destinatario = listaUsuarios.buscarUsuarioPorNombre(nombreDestinatario);
		if(destinatario !=null) {
			System.out.println("Ingrese el monto a transferir: ");
			double monto = super.getSc().nextDouble();
			clienteActual.transferirSaldo(monto, destinatario);
			System.out.println("Saldo transferido con exito. Nuevo saldo : "+clienteActual.getSaldo());
			}else {
				System.out.println("Usuario no encontrado");
			}
	}

	private void retirarSaldo(Usuario clienteActual) {
		System.out.println("Ingrese el monto a retirar: ");
		double monto = super.getSc().nextDouble();
		clienteActual.retirarSaldo(monto);
		System.out.println("Saldo retirado. Nuevo saldo"+clienteActual.getSaldo());
		
	}

	private void agregarSaldo(Usuario clienteActual) {
		System.out.println("Ingrese el monto que desea agregar:");
		double monto = super.getSc().nextDouble();
		clienteActual.agregarSaldo(monto);
		System.out.println("Saldo agregado. Nuevo saldo"+clienteActual.getSaldo());
		}
	
}
