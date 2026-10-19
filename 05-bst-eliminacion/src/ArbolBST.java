/**
 * Árbol Binario de Búsqueda (BST) con inserción, búsqueda y eliminación.
 *
 * Convención:
 *  - valores menores           -> subárbol izquierdo
 *  - valores mayores o iguales -> subárbol derecho
 */
public class ArbolBST {

    private NodoArbol<Integer> raiz;

    public NodoArbol<Integer> getRaiz() {
        return raiz;
    }

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
    // BUSCAR (de la Guía 4)
    // ------------------------------------------------------------
    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(NodoArbol<Integer> nodo, int valor) {
        if (nodo == null) return false;
        if (valor == nodo.getDato()) return true;
        return valor < nodo.getDato()
                ? buscarRec(nodo.getIzquierdo(), valor)
                : buscarRec(nodo.getDerecho(), valor);
    }

    // ------------------------------------------------------------
    // MÍNIMO de un subárbol (sirve para hallar el sucesor inorden)
    // ------------------------------------------------------------
    public int minValor(NodoArbol<Integer> nodo) {
        // El mínimo está siempre en el extremo izquierdo.
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo.getDato();
    }

    // ------------------------------------------------------------
    // ELIMINAR (recursivo)
    // ------------------------------------------------------------
    public void eliminar(int valor) {
        // Se reasigna la raíz porque el nodo eliminado podría ser la raíz.
        raiz = eliminarRec(raiz, valor);
    }

    /** Devuelve la nueva raíz del subárbol después de eliminar el valor. */
    private NodoArbol<Integer> eliminarRec(NodoArbol<Integer> nodo, int valor) {

        // El valor no está en el árbol: no se cambia nada.
        if (nodo == null) return null;

        if (valor < nodo.getDato()) {
            nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), valor));

        } else if (valor > nodo.getDato()) {
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), valor));

        } else {
            // Encontramos el nodo a eliminar.

            // CASO 1 (hoja) y CASO 2 (un hijo a la derecha):
            // si no tiene hijo izquierdo, el padre pasa a apuntar al derecho
            // (que es null cuando el nodo es hoja).
            if (nodo.getIzquierdo() == null) return nodo.getDerecho();

            // CASO 2 (un hijo a la izquierda):
            // el padre pasa a apuntar al hijo izquierdo.
            if (nodo.getDerecho() == null) return nodo.getIzquierdo();

            // CASO 3 (dos hijos):
            // 1) el sucesor inorden es el mínimo del subárbol derecho
            int sucesor = minValor(nodo.getDerecho());
            // 2) se copia su valor en este nodo
            nodo.setDato(sucesor);
            // 3) se elimina el sucesor original del subárbol derecho
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), sucesor));
        }

        return nodo;
    }

    // ------------------------------------------------------------
    // Validación y utilidades
    // ------------------------------------------------------------
    public int contarNodos() {
        return contarRec(raiz);
    }

    private int contarRec(NodoArbol<Integer> nodo) {
        if (nodo == null) return 0;
        return 1 + contarRec(nodo.getIzquierdo()) + contarRec(nodo.getDerecho());
    }

    /** Comprueba que el árbol siga cumpliendo la propiedad BST. */
    public boolean esBSTValido() {
        return validarRec(raiz, null, null);
    }

    // menores a la izquierda (< límite superior), mayores o iguales a la derecha (>= límite inferior)
    private boolean validarRec(NodoArbol<Integer> nodo, Integer minimo, Integer maximo) {
        if (nodo == null) return true;
        int v = nodo.getDato();
        if (minimo != null && v < minimo) return false;
        if (maximo != null && v >= maximo) return false;
        return validarRec(nodo.getIzquierdo(), minimo, v)
                && validarRec(nodo.getDerecho(), v, maximo);
    }

    /** Inorden: en un BST imprime los valores de menor a mayor. */
    public void mostrarInorden() {
        inordenRec(raiz);
        System.out.println();
    }

    private void inordenRec(NodoArbol<Integer> nodo) {
        if (nodo == null) return;
        inordenRec(nodo.getIzquierdo());
        System.out.print(nodo.getDato() + " ");
        inordenRec(nodo.getDerecho());
    }

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

    /**
     * Devuelve el árbol en formato DOT (Graphviz) para generar el diagrama.
     * El nodo "resaltado" se pinta de otro color (usar -1 para no resaltar).
     */
    public String aDot(String titulo, int resaltado) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph BST {\n  label=\"").append(titulo).append("\";\n  labelloc=t;\n  ordering=out;\n");
        sb.append("  node [shape=circle, style=filled, fillcolor=\"#cfe8ff\"];\n");
        sb.append("  edge [fontsize=10];\n");
        if (resaltado >= 0) {
            sb.append("  ").append(resaltado).append(" [fillcolor=\"#ffb3b3\"];\n");
        }
        if (raiz != null && raiz.getIzquierdo() == null && raiz.getDerecho() == null) {
            sb.append("  ").append(raiz.getDato()).append(";\n");
        }
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
