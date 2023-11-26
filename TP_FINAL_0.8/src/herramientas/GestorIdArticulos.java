package herramientas;

import models.Articulo;

public class GestorIdArticulos {
    private static int proximoID = 1;

    public static void asignarID(Articulo articulo) {
        articulo.setIdArticulo(proximoID++);
    }
}
