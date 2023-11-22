package menues;

import java.util.ArrayList;
import java.util.Scanner;

import models.Articulo;
import models.Usuario;

public class MenuCliente {
	
	private ArrayList<Articulo> listaArticulos;
	private ArrayList<Usuario> listaUsuarios;
	private Scanner sc;
	private boolean iniciar;
	private MenuCarrito menuCarrito;

	public MenuCliente(Scanner sc, ArrayList<Articulo> listaArticulos, ArrayList<Usuario> listaUsuarios) {
		this.sc = sc;
		this.listaArticulos = listaArticulos;
		this.iniciar = true; 
		menuCarrito = new MenuCarrito(this.sc, this.listaArticulos);
	}



	public void iniciar(Usuario clienteActual) {
		System.out.println(" ** Menu Cliente **");
		while(this.isIniciar()) {
			System.out.println("1- Modulo de saldo");
			System.out.println("2- Carrito");
			System.out.println("0- Salir");
			
			int opcionElegida = this.sc.nextInt();
			switch (opcionElegida) {
			case 1:
				this.moduloSaldo(clienteActual);
				break;
			case 2:
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
		Usuario destinatario = this.buscarUsuarioPorNombre(nombreDestinatario);
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
	
	private Usuario buscarUsuarioPorNombre(String nombreUsuario) {
		for (Usuario usuario : listaUsuarios) {
			if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario));{
				return usuario;
				}
			}
		
		return null;
	}
	

	public void finalizar() {
		this.iniciar = false;
	}
	public boolean isIniciar() {
		return this.iniciar;
	}
}
