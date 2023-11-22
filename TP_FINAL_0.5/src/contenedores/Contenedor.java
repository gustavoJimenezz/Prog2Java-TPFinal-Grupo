package contenedores;

import java.util.ArrayList;

public abstract class Contenedor<T>{

    protected ArrayList<T> elementos = new ArrayList<>();
    
    public ArrayList<T> getElementos(){
    	return this.elementos;
    }

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public void remover(T elemento) {
        elementos.remove(elemento);
    }

    public void mostrar() {
        for (T elemento : elementos) {
            System.out.println(elemento);
        }
        System.out.println();
    }

    public void vaciar() {
        elementos.clear();
    }
    
    public boolean estaVacio() {
    	return elementos.isEmpty();
    }
}
