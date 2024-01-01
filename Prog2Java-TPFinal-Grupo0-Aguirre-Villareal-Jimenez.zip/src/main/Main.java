package main;

import java.util.Scanner;

import contenedores.ContenedorArticulos;
import contenedores.ContenedorUsuarios;
import menues.MenuPrincipal;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ContenedorUsuarios listaUsuarios = new ContenedorUsuarios();
		ContenedorArticulos listaArticulos = new ContenedorArticulos();
		MenuPrincipal mLogin = new MenuPrincipal(sc, listaUsuarios, listaArticulos);
		
		mLogin.iniciar();
		System.out.println("Programa finalizado!!!");
		sc.close();
	}
}