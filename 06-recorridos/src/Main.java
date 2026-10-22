import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Guía 6. Recorridos en árboles binarios.
 * Ejecuta los cuatro recorridos sobre el mismo BST y compara resultados.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // ---------- PRUEBA 1: BST de 7 nodos (fácil de verificar a mano) ----------
        int[] siete = {50, 30, 70, 20, 40, 60, 80};
        ArbolBST arbol7 = construir(siete);

        System.out.println("=== PRUEBA 1: BST DE 7 NODOS ===");
        arbol7.imprimirArbol();
        mostrarRecorridos(arbol7);

        // ---------- PRUEBA 2: 10 códigos de productos ----------
        int[] codigos = {450, 710, 220, 590, 130, 880, 340, 60, 650, 270};
        ArbolBST arbol10 = construir(codigos);

        System.out.println("\n=== PRUEBA 2: 10 CÓDIGOS DE PRODUCTOS ===");
        System.out.print("Orden de inserción: ");
        for (int c : codigos) System.out.print(c + " ");
        System.out.println("\n");
        arbol10.imprimirArbol();
        mostrarRecorridos(arbol10);

        // ---------- Inorden vs lista ordenada ----------
        List<Integer> ordenada = new ArrayList<>();
        for (int c : codigos) ordenada.add(c);
        Collections.sort(ordenada);

        System.out.println("\nLista ordenada:   " + ordenada);
        System.out.println("Inorden del BST:  " + arbol10.inorden());
        System.out.println("¿Son iguales? " + ordenada.equals(arbol10.inorden()));

        // ---------- PRUEBA 3: árbol vacío ----------
        ArbolBST vacio = new ArbolBST();
        System.out.println("\n=== PRUEBA 3: ÁRBOL VACÍO ===");
        System.out.println("Preorden:   " + vacio.preorden());
        System.out.println("Inorden:    " + vacio.inorden());
        System.out.println("Postorden:  " + vacio.postorden());
        System.out.println("Por niveles: " + vacio.porNiveles());

        // ---------- Eliminar el árbol completo con POSTORDEN ----------
        System.out.println("\n=== ELIMINAR EL ÁRBOL COMPLETO (POSTORDEN) ===");
        arbol7.eliminarArbol();
        System.out.println("¿Árbol vacío? " + arbol7.estaVacio());

        // ---------- Archivos de evidencia ----------
        guardar("recorridos.csv",
                "Arbol,Recorrido,Secuencia\n"
                + fila("7 nodos", "Preorden", construir(siete).preorden())
                + fila("7 nodos", "Inorden", construir(siete).inorden())
                + fila("7 nodos", "Postorden", construir(siete).postorden())
                + fila("7 nodos", "Por niveles", construir(siete).porNiveles())
                + fila("10 codigos", "Preorden", arbol10.preorden())
                + fila("10 codigos", "Inorden", arbol10.inorden())
                + fila("10 codigos", "Postorden", arbol10.postorden())
                + fila("10 codigos", "Por niveles", arbol10.porNiveles()));
        guardar("bst7.dot", construir(siete).aDot("BST de 7 nodos"));
        guardar("bst10.dot", arbol10.aDot("BST de 10 codigos de productos"));
    }

    private static ArbolBST construir(int[] valores) {
        ArbolBST arbol = new ArbolBST();
        for (int v : valores) arbol.insertar(v);
        return arbol;
    }

    private static void mostrarRecorridos(ArbolBST arbol) {
        System.out.println("Preorden    (raíz, izq, der): " + arbol.preorden());
        System.out.println("Inorden     (izq, raíz, der): " + arbol.inorden());
        System.out.println("Postorden   (izq, der, raíz): " + arbol.postorden());
        System.out.println("Por niveles (con Queue):      " + arbol.porNiveles());
    }

    private static String fila(String arbol, String recorrido, List<Integer> secuencia) {
        return arbol + "," + recorrido + ",\""
                + secuencia.toString().replace("[", "").replace("]", "").replace(",", "") + "\"\n";
    }

    private static void guardar(String archivo, String texto) throws IOException {
        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write(texto);
        }
    }
}
