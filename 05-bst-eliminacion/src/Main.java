import java.io.FileWriter;
import java.io.IOException;

/**
 * Guía 5. BST II: eliminación de nodos.
 * Prueba los tres casos: hoja, un hijo y dos hijos (la raíz).
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // ---------- CASO 1: eliminar una HOJA (20) ----------
        ArbolBST a1 = arbolBase();
        probar("CASO 1: ELIMINAR NODO HOJA", a1, 20, "caso1_hoja", 30);

        // ---------- CASO 2: eliminar un nodo con UN HIJO (60, que tiene a 65) ----------
        ArbolBST a2 = arbolBase();
        a2.insertar(65);                      // 65 queda como único hijo de 60
        probar("CASO 2: ELIMINAR NODO CON UN HIJO", a2, 60, "caso2_un_hijo", 70);

        // ---------- CASO 3: eliminar un nodo con DOS HIJOS (la raíz 50) ----------
        ArbolBST a3 = arbolBase();
        System.out.println("Sucesor inorden de 50 = " + a3.minValor(a3.getRaiz().getDerecho()));
        probar("CASO 3: ELIMINAR NODO CON DOS HIJOS (RAIZ)", a3, 50, "caso3_dos_hijos", 60);

        // ---------- PRUEBAS EXTRA ----------
        System.out.println("=== EXTRA 1: SUCESOR CON HIJO DERECHO ===");
        ArbolBST a4 = new ArbolBST();
        for (int v : new int[]{50, 30, 70, 60, 65, 80}) a4.insertar(v);
        a4.imprimirArbol();
        a4.eliminar(50);                      // sucesor = 60, que tiene hijo derecho 65
        System.out.println("-- después de eliminar 50 --");
        a4.imprimirArbol();
        System.out.print("Inorden: ");
        a4.mostrarInorden();
        System.out.println("¿BST válido? " + a4.esBSTValido());

        System.out.println("\n=== EXTRA 2: ELIMINAR UN VALOR QUE NO EXISTE ===");
        ArbolBST a5 = arbolBase();
        System.out.println("Nodos antes: " + a5.contarNodos());
        a5.eliminar(999);
        System.out.println("Nodos después: " + a5.contarNodos());
        System.out.println("¿BST válido? " + a5.esBSTValido());
    }

    /** Elimina un valor, muestra el antes/después y guarda los diagramas. */
    private static void probar(String titulo, ArbolBST arbol, int valor,
                               String archivo, int nodoCambiado) throws IOException {
        System.out.println("=== " + titulo + " ===");
        System.out.println("Antes de eliminar " + valor + ":");
        arbol.imprimirArbol();
        System.out.print("Inorden antes: ");
        arbol.mostrarInorden();
        guardar(archivo + "_antes.dot", arbol.aDot("ANTES: eliminar " + valor, valor));

        arbol.eliminar(valor);

        System.out.println("\nDespués de eliminar " + valor + ":");
        arbol.imprimirArbol();
        System.out.print("Inorden después: ");
        arbol.mostrarInorden();
        System.out.println("¿" + valor + " existe? " + arbol.buscar(valor));
        System.out.println("¿BST válido? " + arbol.esBSTValido());
        System.out.println("Nodos: " + arbol.contarNodos());
        System.out.println();
        guardar(archivo + "_despues.dot", arbol.aDot("DESPUES de eliminar " + valor, nodoCambiado));
    }

    /** BST base: 50, 30, 70, 20, 40, 60, 80 */
    private static ArbolBST arbolBase() {
        ArbolBST arbol = new ArbolBST();
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) arbol.insertar(v);
        return arbol;
    }

    private static void guardar(String archivo, String texto) throws IOException {
        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write(texto);
        }
    }
}
