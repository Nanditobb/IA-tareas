package Tiktaktoe;

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
        // Usara las variables de clase
        return actual;
    }
    
    public Nodo realizarBusquedaEnProfundidad(String objetivo){
        java.util.Stack<Nodo> pila = new java.util.Stack<>();
        HashSet<String> visitados = new HashSet<>();
        pila.push(raiz);
        visitados.add(raiz.estado);
        Nodo actual = null;
        boolean encontrado = false;
        int profundidadMaxima = 50; // Puedes ajustar este valor
        while (!encontrado && !pila.isEmpty()) {
            actual = pila.pop();
            int profundidadActual = 0;
            Nodo temp = actual;
            while (temp.padre != null) {
                profundidadActual++;
                temp = temp.padre;
            }
            if (profundidadActual > profundidadMaxima) continue; // Limita la profundidad

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
            if (profundidadActual > limite) continue; // Limita la profundidad

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
        // Nodo con costo
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
                int costoSucesor = costoActual + 1; // Aquí puedes cambiar el costo según tu problema
                cola.add(new NodoCosto(new Nodo(sucesor, nodoActual), costoSucesor));
                visitados.add(sucesor);
            }
        }
        return null;
    }
    
    public Nodo busquedaConHeuristica(String objetivo) {
        // Revisar esquinas paralelas
        boolean esquina1 = raiz.estado.charAt(0) == objetivo.charAt(0) && raiz.estado.charAt(8) == objetivo.charAt(8);
        boolean esquina2 = raiz.estado.charAt(2) == objetivo.charAt(2) && raiz.estado.charAt(6) == objetivo.charAt(6);

        if (esquina1 || esquina2) {
            System.err.println("Heurística: Esquinas paralelas ya están bien colocadas.");
            return raiz;
        } else {
            System.err.println("Heurística: Esquinas no están bien, se continúa con búsqueda en profundidad.");
            return realizarBusquedaEnProfundidad(objetivo);
        }
    }
}
