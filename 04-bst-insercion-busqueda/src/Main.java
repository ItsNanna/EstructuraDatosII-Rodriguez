import java.io.FileWriter;
import java.io.IOException;

/**
 * Guía 4. BST I: inserción y búsqueda.
 * Compara un BST con datos en orden aleatorio vs. uno con datos ordenados.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // ---------- EJERCICIO 1: 50,30,70,20,40,60,80 ----------
        System.out.println("=== BST DE 7 NODOS ===");
        ArbolBST bst7 = new ArbolBST();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) bst7.insertar(v);

        bst7.imprimirArbol();
        System.out.print("\nInorden: ");
        bst7.mostrarInorden();

        // ---------- EJERCICIO 2: un valor que existe y uno que no ----------
        System.out.println("\nBuscar 60 (existe):   " + bst7.buscarConTraza(60));
        System.out.println("Buscar 65 (no existe): " + bst7.buscarConTraza(65));

        // ---------- 15 códigos de productos ----------
        // Mismos 15 códigos en dos órdenes distintos.
        int[] aleatorios = {450, 780, 130, 610, 320, 950, 60, 520,
                            210, 840, 390, 170, 700, 280, 890};
        int[] ordenados  = {60, 130, 170, 210, 280, 320, 390, 450,
                            520, 610, 700, 780, 840, 890, 950};

        ArbolBST bstAleatorio = new ArbolBST();
        for (int v : aleatorios) bstAleatorio.insertar(v);

        ArbolBST bstOrdenado = new ArbolBST();
        for (int v : ordenados) bstOrdenado.insertar(v);

        // Cinco búsquedas: cuatro existen y una no.
        int[] consultas = {60, 390, 700, 950, 1000};

        System.out.println("\n=== INSERCIÓN ALEATORIA ===");
        bstAleatorio.imprimirArbol();
        int compAleatorio = probarBusquedas(bstAleatorio, consultas);

        System.out.println("\n=== INSERCIÓN ORDENADA ===");
        bstOrdenado.imprimirArbol();
        int compOrdenado = probarBusquedas(bstOrdenado, consultas);

        // ---------- COMPARACIÓN ----------
        double promAleatorio = compAleatorio / (double) consultas.length;
        double promOrdenado = compOrdenado / (double) consultas.length;

        System.out.println("\n=== COMPARACIÓN ===");
        System.out.println("Altura aleatorio: " + bstAleatorio.altura());
        System.out.println("Altura ordenado:  " + bstOrdenado.altura());
        System.out.println("Comparaciones aleatorio: " + compAleatorio);
        System.out.println("Comparaciones ordenado:  " + compOrdenado);
        System.out.printf("Promedio aleatorio: %.2f%n", promAleatorio);
        System.out.printf("Promedio ordenado:  %.2f%n", promOrdenado);

        // ---------- Archivos de evidencia (tabla y diagramas) ----------
        try (FileWriter fw = new FileWriter("comparacion_bst.csv")) {
            fw.write("Escenario,Nodos,Altura (aristas),Comparaciones totales,Promedio en 5 busquedas\n");
            fw.write("Aleatorio,15," + bstAleatorio.altura() + "," + compAleatorio + ","
                    + String.format("%.2f", promAleatorio).replace(',', '.') + "\n");
            fw.write("Ordenado,15," + bstOrdenado.altura() + "," + compOrdenado + ","
                    + String.format("%.2f", promOrdenado).replace(',', '.') + "\n");
        }
        guardar("bst7.dot", bst7.aDot("BST de 7 nodos"));
        guardar("bst_aleatorio.dot", bstAleatorio.aDot("Insercion aleatoria"));
        guardar("bst_ordenado.dot", bstOrdenado.aDot("Insercion ordenada"));
    }

    private static int probarBusquedas(ArbolBST arbol, int[] consultas) {
        int total = 0;
        for (int valor : consultas) {
            ResultadoBusqueda r = arbol.buscarConTraza(valor);
            System.out.println("Buscar " + valor + " -> " + r);
            total += r.getComparaciones();
        }
        return total;
    }

    private static void guardar(String archivo, String texto) throws IOException {
        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write(texto);
        }
    }
}
