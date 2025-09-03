package IA;
import java.util.*;

class Arista {
    int destino;
    int peso;

    Arista(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}

public class grafo {
    private Map<Integer, List<Arista>> adj;

    public grafo() {
        adj = new HashMap<>();
    }

    public void agregarArista(int origen, int destino, int peso) {
        adj.putIfAbsent(origen, new ArrayList<>());
        adj.get(origen).add(new Arista(destino, peso));
    }

    public List<Arista> obtenerAristas(int nodo) {
        return adj.getOrDefault(nodo, new ArrayList<>());
    }
}
