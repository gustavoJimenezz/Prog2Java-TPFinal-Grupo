package main;

import java.util.Scanner;

import contenedores.ContenedorArticulos;
import contenedores.ContenedorUsuarios;
import menues.MenuLogin;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ContenedorUsuarios listaUsuarios = new ContenedorUsuarios();
		ContenedorArticulos listaArticulos = new ContenedorArticulos();
		MenuLogin mLogin = new MenuLogin(sc, listaUsuarios, listaArticulos);
	
		System.out.println("Bienvenido al programa!!!");
		mLogin.iniciar();
		sc.close();
	}
}