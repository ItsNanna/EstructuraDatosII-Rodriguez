import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reto: "Catálogo inteligente".
 * BST de productos por código. Se prueba con los mismos 15 productos
 * insertados en orden aleatorio y en orden ascendente, y se compara la altura.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // 15 productos; el orden de esta lista es el orden ALEATORIO de inserción.
        List<Producto> aleatorio = new ArrayList<>();
        aleatorio.add(new Producto(1148, "Cable HDMI",        18000));
        aleatorio.add(new Producto(1042, "Mouse inalambrico", 45000));
        aleatorio.add(new Producto(1255, "Router WiFi 6",    190000));
        aleatorio.add(new Producto(1090, "Hub USB",           35000));
        aleatorio.add(new Producto(1190, "Disco SSD 500GB",  220000));
        aleatorio.add(new Producto(1015, "Teclado mecanico", 135000));
        aleatorio.add(new Producto(1301, "Impresora laser",  690000));
        aleatorio.add(new Producto(1120, "Cargador USB-C",    60000));
        aleatorio.add(new Producto(1033, "Audifonos",         85000));
        aleatorio.add(new Producto(1230, "Monitor 24 pulg",  780000));
        aleatorio.add(new Producto(1077, "Memoria USB 64GB",  38000));
        aleatorio.add(new Producto(1210, "Parlante Bluetooth",120000));
        aleatorio.add(new Producto(1002, "Mousepad",          15000));
        aleatorio.add(new Producto(1275, "Tablet 10 pulg",  1250000));
        aleatorio.add(new Producto(1108, "Webcam HD",        120000));

        // Los mismos productos, ordenados por código (selección simple, sin librerías).
        List<Producto> ordenado = new ArrayList<>(aleatorio);
        for (int i = 0; i < ordenado.size() - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < ordenado.size(); j++) {
                if (ordenado.get(j).getCodigo() < ordenado.get(menor).getCodigo()) menor = j;
            }
            Producto temp = ordenado.get(i);
            ordenado.set(i, ordenado.get(menor));
            ordenado.set(menor, temp);
        }

        ArbolBST bstAleatorio = construir(aleatorio);
        ArbolBST bstOrdenado = construir(ordenado);

        System.out.println("=== CATALOGO INTELIGENTE ===");
        System.out.print("Orden aleatorio de insercion: ");
        imprimirCodigos(aleatorio);
        System.out.print("Orden ascendente de insercion: ");
        imprimirCodigos(ordenado);

        System.out.println("\n=== BST ALEATORIO ===");
        bstAleatorio.imprimirArbol();
        System.out.println("Altura: " + bstAleatorio.altura());

        System.out.println("\n=== BST ORDENADO ===");
        bstOrdenado.imprimirArbol();
        System.out.println("Altura: " + bstOrdenado.altura());

        // Cinco búsquedas: cuatro existen y una no.
        int[] consultas = {1002, 1120, 1230, 1301, 1200};

        System.out.println("\n=== BUSQUEDAS EN BST ALEATORIO ===");
        int compAleatorio = probarBusquedas(bstAleatorio, consultas);

        System.out.println("\n=== BUSQUEDAS EN BST ORDENADO ===");
        int compOrdenado = probarBusquedas(bstOrdenado, consultas);

        // Tabla comparativa
        double promAleatorio = compAleatorio / (double) consultas.length;
        double promOrdenado = compOrdenado / (double) consultas.length;

        System.out.println("\n=== TABLA COMPARATIVA ===");
        System.out.println("Escenario   | Productos | Altura | Comparaciones (5 busquedas) | Promedio");
        System.out.printf("Aleatorio   | %9d | %6d | %27d | %.2f%n",
                aleatorio.size(), bstAleatorio.altura(), compAleatorio, promAleatorio);
        System.out.printf("Ordenado    | %9d | %6d | %27d | %.2f%n",
                ordenado.size(), bstOrdenado.altura(), compOrdenado, promOrdenado);

        // Prueba extra: código repetido
        System.out.println("\n=== PRUEBA: CODIGO REPETIDO ===");
        boolean insertado = bstAleatorio.insertar(new Producto(1042, "Otro mouse", 1000));
        System.out.println("¿Se insertó el código 1042 otra vez? " + insertado);

        // Catálogo ordenado por código (recorrido inorden)
        System.out.println("\n=== CATALOGO ORDENADO POR CODIGO ===");
        bstAleatorio.mostrarCatalogo();

        // Archivos de evidencia
        try (FileWriter fw = new FileWriter("comparacion_catalogo.csv")) {
            fw.write("Escenario,Productos,Altura (aristas),Comparaciones totales,Promedio en 5 busquedas\n");
            fw.write("Aleatorio," + aleatorio.size() + "," + bstAleatorio.altura() + ","
                    + compAleatorio + "," + String.format("%.2f", promAleatorio).replace(',', '.') + "\n");
            fw.write("Ordenado," + ordenado.size() + "," + bstOrdenado.altura() + ","
                    + compOrdenado + "," + String.format("%.2f", promOrdenado).replace(',', '.') + "\n");
        }
        guardar("bst_aleatorio.dot", bstAleatorio.aDot("Catalogo - insercion aleatoria"));
        guardar("bst_ordenado.dot", bstOrdenado.aDot("Catalogo - insercion ordenada"));
    }

    private static ArbolBST construir(List<Producto> productos) {
        ArbolBST arbol = new ArbolBST();
        for (Producto p : productos) arbol.insertar(p);
        return arbol;
    }

    private static void imprimirCodigos(List<Producto> productos) {
        for (Producto p : productos) System.out.print(p.getCodigo() + " ");
        System.out.println();
    }

    private static int probarBusquedas(ArbolBST arbol, int[] consultas) {
        int total = 0;
        for (int codigo : consultas) {
            ResultadoBusqueda r = arbol.buscarConTraza(codigo);
            System.out.println("Buscar " + codigo + " -> " + r
                    + (r.isEncontrado() ? "  [" + r.getProducto().getNombre() + "]" : ""));
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
