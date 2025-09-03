package IA;

public class Nodo {

    private int valor;
    private Nodo izquierda;
    private Nodo derecha;

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public void setValor(int valor){
        this.valor = valor;
    }
    
    public int getValor(){
        return 0;
    }

    public Nodo getIzquierda(){
        return null;
    }

    public Nodo getDerecha(){
        return null;
    }
}
