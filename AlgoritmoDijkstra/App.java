package AlgoritmoDijkstra;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class App extends JFrame {

    static Graph graph = new Graph();
    static ManejoCSV csv = new ManejoCSV();
    private JTextField textFieldSourceNode;
    private JTable tabla;

    public App() {
        super("Shortest Path");
        HazInterfaz();
        HazEscuchas();
    }

    // EVENTOS -----------------------
    private void HazEscuchas() {
        JButton btnCalcular = new JButton("Calcular");

        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener el ID del nodo de origen ingresado por el usuario
                try {
                    csv.readCSV(graph);
                    int sourceNodeId = Integer.parseInt(textFieldSourceNode.getText());
                    // Obtener el nodo de origen desde el grafo
                    Node sourceNode = graph.getNodeById(sourceNodeId);

                    // Calcular la ruta más corta desde el nuevo nodo de origen
                    graph = Dijkstra.calculateShortestPathFromSource(graph, sourceNode);

                    Graph.printGraph(graph, sourceNode);

                    updateTable(sourceNode);
                    // Limpia el grafo
                    graph.getNodes().clear();
                } catch (Exception id) {
                    JOptionPane.showMessageDialog(null,
                            "Ingrese un valor valido, El grafo tiene: " + csv.getLastconection() + " Nodos");
                }
            }
        });

        // Crear un panel para los controles
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("ID del Nodo de Origen:"));
        textFieldSourceNode = new JTextField(10);
        controlPanel.add(textFieldSourceNode);
        controlPanel.add(btnCalcular);

        // Agregar el panel de control a la parte superior
        add(controlPanel, BorderLayout.NORTH);
    }

    // INTERFAZ ---------------------
    private void HazInterfaz() {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crear una tabla
        tabla = new JTable(new DefaultTableModel(0, 4)); // Inicializar

        // nombres de las columnas
        String[] nombresColumnas = { "NODO ORIGEN", "NODO DESTINO", "CAMINO MAS CORTO", "DISTANCIA (km)" };
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setColumnIdentifiers(nombresColumnas); // Establecer los nombres de las columnas

        JScrollPane scrollPane = new JScrollPane(tabla);

        // Agregar el JScrollPane al contenido principal
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // ACTUALIZAR LA TABLA
    private void updateTable(Node sourceNode) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        modelo.setRowCount(0); // Limpiar filas

        // Iterar a través de los nodos y agregar datos a la tabla
        for (Node node : graph.getNodes()) {
            List<Node> shortestPath = node.getShortestPath();
            StringBuilder pathText = new StringBuilder();

            for (Node pathNode : shortestPath) {
                pathText.append(pathNode.getName()).append("->");
            }

            // Agregar una fila con los datos a la tabla
            modelo.addRow(
                    new Object[] { sourceNode.getName(), node.getName(), pathText.toString() + node.getName(),
                            node.getDistance() });
        }

    }

    public static void main(String[] args) throws Exception {
        // Leer datos desde el archivo CSV
        new App();
    }
}
