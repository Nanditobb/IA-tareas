package TiktaktoeTarea4;

import java.util.Scanner;

public class App {

    public static void main(String a[]){
        String estadoInicial ="7245 6831";
        String estadoFinal =" 12345678";

        Arbol arbol = new Arbol(new Nodo(estadoInicial, null));
        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            System.out.println("¿Que metodo de busqueda se usara?");
            System.out.println("1. Anchura");
            System.out.println("2. Profundidad");
            System.out.println("3. Profundidad Limitada");
            System.out.println("4. Profundidad Iterativa");
            System.out.print("Elige 1, 2, 3 o 4: ");
            int opcion = sc.nextInt();

            Nodo resultado = null;
            if (opcion == 1) {
                resultado = arbol.realizarBusquedaEnAnchura(estadoFinal);
                System.err.println("Camino de solucion (Anchura): ");
            } else if (opcion == 2) {
                resultado = arbol.realizarBusquedaEnProfundidad(estadoFinal);
                System.err.println("Camino de solucion (Profundidad): ");
            } else if (opcion == 3) {
                System.out.print("Ingresa el limite de profundidad: ");
                int limite = sc.nextInt();
                resultado = arbol.realizarBusquedaEnProfundidadLimitada(estadoFinal, limite);
                System.err.println("Camino de solucion (Profundidad Limitada): ");
            } else if (opcion == 4) {
                System.out.print("Ingresa el limite maximo de profundidad para la busqueda iterativa: ");
                int limiteMaximo = sc.nextInt();
                resultado = arbol.realizarBusquedaEnProfundidadIterativa(estadoFinal, limiteMaximo);
                System.err.println("Camino de solucion (Profundidad Iterativa): ");
            } else {
                System.out.println("Opcion no valida.");
                return;
            }

            // Guardar el camino en un stack
            java.util.Stack<Nodo> camino = new java.util.Stack<>();
            Nodo temp = resultado;
            int costoUniforme = 0;
            while (temp != null) {
                camino.push(temp);
                temp = temp.padre;
            }

            // Imprimir el stack desde el estado inicial al final
            while (!camino.isEmpty()) {
                imprimirMatriz(camino.pop().estado);
                costoUniforme++;
            }

            // Imprime el costo (número de pasos) para cualquier método
            System.out.println("Costo total: " + (costoUniforme - 1));

            System.out.print("¿Quieres realizar otra busqueda? (s/n): ");
            char respuesta = sc.next().charAt(0);
            continuar = (respuesta == 's' || respuesta == 'S');
        }

        System.out.println("Busquedas Finalizadas.");
    }

    public static void imprimirMatriz(String estado) {
        for (int i = 0; i < 9; i++) {
            char c = estado.charAt(i) == ' ' ? ' ' : estado.charAt(i);
            System.out.print("| " + c + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println("|");
                if (i < 8) System.out.println("-------------");
            }
        }
        System.out.println();
    }

}
