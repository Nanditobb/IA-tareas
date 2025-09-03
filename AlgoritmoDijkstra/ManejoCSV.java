package AlgoritmoDijkstra;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ManejoCSV {

    int lastconection = 0;

    public void readCSV(Graph graph) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("AlgoritmoDijkstra/Caminos.csv"));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idOrigen = Integer.parseInt(parts[0]);
                int idDestino = Integer.parseInt(parts[1]);
                int km = Integer.parseInt(parts[2]);

                Node origen = graph.getNodeById(idOrigen);
                if (origen == null) {
                    origen = new Node(idOrigen);
                    graph.addNode(origen);
                }

                Node destino = graph.getNodeById(idDestino);
                if (destino == null) {
                    destino = new Node(idDestino);
                    graph.addNode(destino);
                }

                origen.addDestination(destino, km);
                destino.addDestination(origen, km);

                lastconection = idDestino;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getLastconection() {
        return lastconection;
    }

}
