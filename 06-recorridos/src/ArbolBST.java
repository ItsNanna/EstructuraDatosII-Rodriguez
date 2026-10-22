import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Árbol Binario de Búsqueda (BST) con los cuatro recorridos:
 * Preorden, Inorden, Postorden (recursivos) y Por niveles (con Queue).
 *
 * Convención:
 *  - valores menores           -> subárbol izquierdo
 *  - valores mayores o iguales -> subárbol derecho
 */
public class ArbolBST {

    private NodoArbol<Integer> raiz;

    // ------------------------------------------------------------
    // INSERTAR (de la Guía 4)
    // ------------------------------------------------------------
    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private NodoArbol<Integer> insertarRec(NodoArbol<Integer> nodo, int valor) {
        if (nodo == null) return new NodoArbol<>(valor);

        if (valor < nodo.getDato()) nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), valor));
        else nodo.setDerecho(insertarRec(nodo.getDerecho(), valor));

        return nodo;
    }

    // ------------------------------------------------------------
    // PREORDEN: raíz -> izquierda -> derecha
    // ------------------------------------------------------------
    public List<Integer> preorden() {
        List<Integer> resultado = new ArrayList<>();
        preordenRec(raiz, resultado);
        return resultado;
    }

    private void preordenRec(NodoArbol<Integer> nodo, List<Integer> resultado) {
        if (nodo == null) return;
        resultado.add(nodo.getDato());              // 1) visita la raíz
        preordenRec(nodo.getIzquierdo(), resultado); // 2) subárbol izquierdo
        preordenRec(nodo.getDerecho(), resultado);   // 3) subárbol derecho
    }

    // ------------------------------------------------------------
    // INORDEN: izquierda -> raíz -> derecha
    // ------------------------------------------------------------
    public List<Integer> inorden() {
        List<Integer> resultado = new ArrayList<>();
        inordenRec(raiz, resultado);
        return resultado;
    }

    private void inordenRec(NodoArbol<Integer> nodo, List<Integer> resultado) {
        if (nodo == null) return;
        inordenRec(nodo.getIzquierdo(), resultado);  // 1) subárbol izquierdo
        resultado.add(nodo.getDato());               // 2) visita la raíz
        inordenRec(nodo.getDerecho(), resultado);    // 3) subárbol derecho
    }

    // ------------------------------------------------------------
    // POSTORDEN: izquierda -> derecha -> raíz
    // ------------------------------------------------------------
    public List<Integer> postorden() {
        List<Integer> resultado = new ArrayList<>();
        postordenRec(raiz, resultado);
        return resultado;
    }

    private void postordenRec(NodoArbol<Integer> nodo, List<Integer> resultado) {
        if (nodo == null) return;
        postordenRec(nodo.getIzquierdo(), resultado); // 1) subárbol izquierdo
        postordenRec(nodo.getDerecho(), resultado);   // 2) subárbol derecho
        resultado.add(nodo.getDato());                // 3) visita la raíz
    }

    // ------------------------------------------------------------
    // POR NIVELES: usa una cola (Queue), nivel por nivel
    // ------------------------------------------------------------
    public List<Integer> porNiveles() {
        List<Integer> resultado = new ArrayList<>();
        if (raiz == null) return resultado;

        Queue<NodoArbol<Integer>> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            NodoArbol<Integer> actual = cola.poll();   // saca el primero de la cola
            resultado.add(actual.getDato());

            // los hijos entran al final de la cola: se visitarán después
            if (actual.getIzquierdo() != null) cola.add(actual.getIzquierdo());
            if (actual.getDerecho() != null) cola.add(actual.getDerecho());
        }
        return resultado;
    }

    // ------------------------------------------------------------
    // ELIMINAR TODO EL ÁRBOL de forma segura (POSTORDEN)
    // Primero se liberan los hijos y al final el padre.
    // ------------------------------------------------------------
    public void eliminarArbol() {
        raiz = eliminarArbolRec(raiz);
        System.out.println();
    }

    private NodoArbol<Integer> eliminarArbolRec(NodoArbol<Integer> nodo) {
        if (nodo == null) return null;
        nodo.setIzquierdo(eliminarArbolRec(nodo.getIzquierdo())); // hijos primero
        nodo.setDerecho(eliminarArbolRec(nodo.getDerecho()));
        System.out.print("elimino " + nodo.getDato() + "  ");     // el padre al final
        return null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // ------------------------------------------------------------
    // Utilidades para mostrar el árbol
    // ------------------------------------------------------------
    /** Imprime el árbol "acostado": la raíz a la izquierda, derecha arriba. */
    public void imprimirArbol() {
        imprimirRec(raiz, 0);
    }

    private void imprimirRec(NodoArbol<Integer> nodo, int nivel) {
        if (nodo == null) return;
        imprimirRec(nodo.getDerecho(), nivel + 1);
        System.out.println("    ".repeat(nivel) + nodo.getDato());
        imprimirRec(nodo.getIzquierdo(), nivel + 1);
    }

    /** Devuelve el árbol en formato DOT (Graphviz) para generar el diagrama. */
    public String aDot(String titulo) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph BST {\n  label=\"").append(titulo).append("\";\n  labelloc=t;\n  ordering=out;\n");
        sb.append("  node [shape=circle, style=filled, fillcolor=\"#cfe8ff\"];\n");
        sb.append("  edge [fontsize=10];\n");
        dotRec(raiz, sb);
        sb.append("}\n");
        return sb.toString();
    }

    private void dotRec(NodoArbol<Integer> nodo, StringBuilder sb) {
        if (nodo == null) return;
        if (nodo.getIzquierdo() != null) {
            sb.append("  ").append(nodo.getDato()).append(" -> ").append(nodo.getIzquierdo().getDato()).append(" [label=\"izq\"];\n");
            dotRec(nodo.getIzquierdo(), sb);
        }
        if (nodo.getDerecho() != null) {
            sb.append("  ").append(nodo.getDato()).append(" -> ").append(nodo.getDerecho().getDato()).append(" [label=\"der\"];\n");
            dotRec(nodo.getDerecho(), sb);
        }
    }
}
