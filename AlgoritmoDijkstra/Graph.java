package AlgoritmoDijkstra;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {

    public Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Node getNodeById(int id) {
        for (Node node : nodes) {
            if (node.getName() == id) {
                return node;
            }
        }
        return null;
    }

    // getter and Setter

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public static void printGraph(Graph grafo, Node source) {
        System.out.println("Tabla de Caminos Más Cortos:");
        System.out.println("Nodo Origen\t|\tNodo Destino\t|\tCamino Más Corto\t|\tDistancia");

        for (Node node : grafo.getNodes()) {
            System.out.print("    " + source.getName() + "\t\t|\t");
            System.out.print("    " + node.getName() + "\t\t|\t");

            List<Node> shortestPath = node.getShortestPath();

            for (Node pathNode : shortestPath) {
                System.out.print(pathNode.getName() + " -> ");
            }
            System.out.print(node.getName());

            System.out.print("\t|\t" + node.getDistance());
            System.out.println();
        }
    }
}