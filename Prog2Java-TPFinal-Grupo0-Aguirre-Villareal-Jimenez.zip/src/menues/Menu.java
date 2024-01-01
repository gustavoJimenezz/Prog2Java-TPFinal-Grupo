package menues;

import java.util.Scanner;

import exceptions.ExcepcionDeIngresoDeDatos;

public abstract class Menu {
	private boolean iniciar;
	private Scanner sc;
	
	public Menu(Scanner sc) {
		this.sc = sc;
	}

	public void iniciar() {
		this.iniciarApp();
		while (this.isIniciar()) {
			try {
				String opcionElegida = opciones();
				procesarOpcion(opcionElegida);
			} catch (ExcepcionDeIngresoDeDatos e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public abstract String opciones();

	public abstract void procesarOpcion(String opcionElegida) throws ExcepcionDeIngresoDeDatos;


	public void iniciarApp() {
		iniciar = true;
	}

	public boolean isIniciar() {
		return iniciar;
	}

	public boolean finalizar() {
		return iniciar = false;
	}

	public Scanner getSc() {
		return sc;
	}
}
