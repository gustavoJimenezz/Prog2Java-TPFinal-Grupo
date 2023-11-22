package herramientas;

import java.util.Scanner;

public class Validador {
    private Validador() {
    }
    public static int pedirEntero(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingrese un número entero válido : ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
