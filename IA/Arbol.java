package IA;

public class Arbol {
    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    public void insertar(int valor) {
        raiz = insertarRam(raiz, valor);
    }

    public boolean vacio(Nodo raiz){
        if (raiz == null) {
            return true;
        }
        return false;
    }

    private Nodo buscarNodo(Nodo nodo, int valor) {
        if (nodo == null || nodo.getValor() == valor) {
            return nodo;
        }
        if (valor < nodo.getValor()) {
            return buscarNodo(nodo.getIzquierda(), valor);
        } else {
            return buscarNodo(nodo.getDerecha(), valor);
        }
    }

    private Nodo insertarRam(Nodo nodo, int valor) {
        if (nodo == null) {
            nodo = new Nodo();
            nodo.setValor(valor);
            return nodo;
        }
        if (valor < nodo.getValor()) {
            nodo.setIzquierda(insertarRam(nodo.getIzquierda(), valor));
        } else {
            nodo.setDerecha(insertarRam(nodo.getDerecha(), valor));
        }
        return nodo;
    }
}
