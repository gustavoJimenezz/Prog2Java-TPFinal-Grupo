package menues;

import java.util.Scanner;
import contenedores.ContenedorArticulos;
import contenedores.ContenedorUsuarios;
import models.Usuario;

public class MenuCliente {
	
//	private ArrayList<Articulo> listaArticulos;
	ContenedorArticulos listaArticulos;
	private ContenedorUsuarios listaUsuarios;
	private Scanner sc;
	private boolean iniciar;
	private MenuCarrito menuCarrito;

	public MenuCliente(Scanner sc, ContenedorArticulos listaArticulos, ContenedorUsuarios  listaUsuarios) {
		this.sc = sc;
		this.listaArticulos = listaArticulos;
		this.iniciar = true; 
		menuCarrito = new MenuCarrito(this.sc, listaArticulos);
	}



	public void iniciar(Usuario clienteActual) {
		while(this.isIniciar()) {
			System.out.println();
			System.out.println(" ** Menu Cliente **");
			System.out.println("1- Modulo de saldo");
			System.out.println("2- Carrito");
			System.out.println("0- Salir");
			System.out.print("Ingrese : ");
			
			int opcionElegida = this.sc.nextInt();
			switch (opcionElegida) {
			case 1:
				this.moduloSaldo(clienteActual);
				break;
			case 2:
				System.out.println();
				this.menuCarrito.iniciar(clienteActual);
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
	
	 private void moduloSaldo(Usuario clienteActual) {
	        System.out.println(" ** Modulo de Saldo **");
	        System.out.println("1- Agregar Saldo");
	        System.out.println("2- Retirar Saldo");
	        System.out.println("3- Transferir Saldo");
	        System.out.println("0- Volver al Menú Principal");
	        
	        int opcionSaldo = this.sc.nextInt();
	        switch (opcionSaldo) {
	        case 1:
	        	this.agregarSaldo(clienteActual);
	        	break;
	        case 2:
	        	this.retirarSaldo(clienteActual);
	        	break;
	        case 3:
	        	this.trasnferirSaldo(clienteActual);
	        	break;
	        case 0:
	        	//volver al menu principal
	        	break;
	        	default :
	        		System.out.println("Opcion incorrecta");
	        		break;
	        	
	        }
	        
	 }
	
	
	private void trasnferirSaldo(Usuario clienteActual) {
		System.out.println("Ingrese el nombre del usuario destinatario");
		String nombreDestinatario = this.sc.next();
		Usuario destinatario = listaUsuarios.buscarUsuarioPorNombre(nombreDestinatario);
		if(destinatario !=null) {
			System.out.println("Ingrese el monto a transferir: ");
			double monto = this.sc.nextDouble();
			clienteActual.transferirSaldo(monto, destinatario);
			System.out.println("Saldo transferido con exito. Nuevo saldo : "+clienteActual.getSaldo());
			}else {
				System.out.println("Usuario no encontrado");
			}
	}

	private void retirarSaldo(Usuario clienteActual) {
		System.out.println("Ingrese el monto a retirar: ");
		double monto = this.sc.nextDouble();
		clienteActual.retirarSaldo(monto);
		System.out.println("Saldo retirado. Nuevo saldo"+clienteActual.getSaldo());
		
	}

	private void agregarSaldo(Usuario clienteActual) {
		System.out.println("Ingrese el monto que desea agregar:");
		double monto = this.sc.nextDouble();
		clienteActual.agregarSaldo(monto);
		System.out.println("Saldo agregado. Nuevo saldo"+clienteActual.getSaldo());
		}
	
	public void finalizar() {
		this.iniciar = false;
	}
	public boolean isIniciar() {
		return this.iniciar;
	}
}
