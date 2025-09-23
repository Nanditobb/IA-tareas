package TiktaktoeTarea4;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Arbol {
    Nodo raiz;

    public Arbol(Nodo raiz){
        this.raiz = raiz;
    }
    public Nodo realizarBusquedaEnAnchura(String objetivo){
        Queue<Nodo> cola = new LinkedList<Nodo>();
        HashSet<String> visitados = new HashSet<String>();
        cola.add(raiz);
        visitados.add(raiz.estado);
        boolean encontrado  = false;
        Nodo actual = null;
        while (!encontrado && cola.size() > 0) {
            actual = cola.poll();
            System.err.println("Procesando => " + actual.estado);
            //Funcion objetivo
            if (actual.estado.equals(objetivo)) {
                encontrado = true;
            } else {
                List<String> sucesores = actual.obtenerSucesores();
                for (String sucesor : sucesores) {
                    // si ya fue procesado, ignorar.
                    if (visitados.contains(sucesor)) {
                        continue;        
                    }
                    System.err.println("Agregando a cola => " + sucesor);
                    cola.add(new Nodo(sucesor, actual));
                    visitados.add(sucesor);
                }
            }
        }
        return actual;
    }
    
    public Nodo realizarBusquedaEnProfundidad(String objetivo){
        java.util.Stack<Nodo> pila = new java.util.Stack<>();
        HashSet<String> visitados = new HashSet<>();
        pila.push(raiz);
        visitados.add(raiz.estado);
        Nodo actual = null;
        boolean encontrado = false;
        int profundidadMaxima = 50;
        while (!encontrado && !pila.isEmpty()) {
            actual = pila.pop();
            int profundidadActual = 0;
            Nodo temp = actual;
            while (temp.padre != null) {
                profundidadActual++;
                temp = temp.padre;
            }
            if (profundidadActual > profundidadMaxima) continue; // Pregunta el maximo de profundidad

            System.err.println("Procesando => " + actual.estado);
            if (actual.estado.equals(objetivo)) {
                encontrado = true;
            } else {
                for (String sucesor : actual.obtenerSucesores()) {
                    if (visitados.contains(sucesor)) continue;
                    pila.push(new Nodo(sucesor, actual));
                    visitados.add(sucesor);
                }
            }
        }
        return actual;
    }
    
    public Nodo realizarBusquedaEnProfundidadLimitada(String objetivo, int limite){
        java.util.Stack<Nodo> pila = new java.util.Stack<>();
        HashSet<String> visitados = new HashSet<>();
        pila.push(raiz);
        visitados.add(raiz.estado);
        Nodo actual = null;
        boolean encontrado = false;
        while (!encontrado && !pila.isEmpty()) {
            actual = pila.pop();
            int profundidadActual = 0;
            Nodo temp = actual;
            while (temp.padre != null) {
                profundidadActual++;
                temp = temp.padre;
            }
            if (profundidadActual > limite) continue; // Pregunta el maximo de profundidad

            System.err.println("Procesando => " + actual.estado);
            if (actual.estado.equals(objetivo)) {
                encontrado = true;
            } else {
                for (String sucesor : actual.obtenerSucesores()) {
                    if (visitados.contains(sucesor)) continue;
                    pila.push(new Nodo(sucesor, actual));
                    visitados.add(sucesor);
                }
            }
        }
        return actual;
    }
    
    public Nodo realizarBusquedaEnProfundidadIterativa(String objetivo, int limiteMaximo) {
        for (int limite = 0; limite <= limiteMaximo; limite++) {
            System.err.println("Buscando con límite de profundidad: " + limite);
            Nodo resultado = realizarBusquedaEnProfundidadLimitada(objetivo, limite);
            if (resultado != null && resultado.estado.equals(objetivo)) {
                return resultado;
            }
        }
        return null; 
    }
    
    public Nodo realizarBusquedaCostoUniforme(String objetivo) {
        class NodoCosto {
            Nodo nodo;
            int costo;
            NodoCosto(Nodo nodo, int costo) {
                this.nodo = nodo;
                this.costo = costo;
            }
        }

        PriorityQueue<NodoCosto> cola = new PriorityQueue<>(Comparator.comparingInt(nc -> nc.costo));
        HashSet<String> visitados = new HashSet<>();
        cola.add(new NodoCosto(raiz, 0));
        visitados.add(raiz.estado);

        while (!cola.isEmpty()) {
            NodoCosto actual = cola.poll();
            Nodo nodoActual = actual.nodo;
            int costoActual = actual.costo;

            System.err.println("Procesando => " + nodoActual.estado + " | Costo: " + costoActual);

            if (nodoActual.estado.equals(objetivo)) {
                return nodoActual;
            }

            for (String sucesor : nodoActual.obtenerSucesores()) {
                if (visitados.contains(sucesor)) continue;
                int costoSucesor = costoActual + 1; 
                cola.add(new NodoCosto(new Nodo(sucesor, nodoActual), costoSucesor));
                visitados.add(sucesor);
            }
        }
        return null;
    }
}
