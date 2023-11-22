		package main;

import java.util.Scanner;
import contenedores.ContenedorUsuarios;
import menues.MenuLogin;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ContenedorUsuarios listaUsuarios = new ContenedorUsuarios();
		MenuLogin mLogin = new MenuLogin(sc, listaUsuarios);
	
		System.out.println("Bienvenido al programa!!!");
		mLogin.iniciar();
		sc.close();
	}
}