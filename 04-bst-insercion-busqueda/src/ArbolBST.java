/**
 * Árbol Binario de Búsqueda (BST) con inserción y búsqueda recursivas.
 *
 * Convención:
 *  - valores menores    -> subárbol izquierdo
 *  - valores mayores o iguales -> subárbol derecho
 */
public class ArbolBST {

    private NodoArbol<Integer> raiz;

    // ------------------------------------------------------------
    // INSERTAR (recursivo)
    // ------------------------------------------------------------
    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private NodoArbol<Integer> insertarRec(NodoArbol<Integer> nodo, int valor) {
        // Caso base: llegamos a un hueco, aquí se crea el nodo nuevo.
        if (nodo == null) return new NodoArbol<>(valor);

        // Caso recursivo: bajamos por un solo lado.
        if (valor < nodo.getDato()) nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), valor));
        else nodo.setDerecho(insertarRec(nodo.getDerecho(), valor));

        return nodo;
    }

    // ------------------------------------------------------------
    // BUSCAR (recursivo)
    // ------------------------------------------------------------
    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(NodoArbol<Integer> nodo, int valor) {
        if (nodo == null) return false;              // no existe
        if (valor == nodo.getDato()) return true;    // lo encontramos
        return valor < nodo.getDato()
                ? buscarRec(nodo.getIzquierdo(), valor)
                : buscarRec(nodo.getDerecho(), valor);
    }

    // ------------------------------------------------------------
    // BUSCAR con evidencia: camino recorrido y número de comparaciones
    // ------------------------------------------------------------
    public ResultadoBusqueda buscarConTraza(int valor) {
        return trazaRec(raiz, valor, 0, "");
    }

    private ResultadoBusqueda trazaRec(NodoArbol<Integer> nodo, int valor,
                                       int comparaciones, String camino) {
        if (nodo == null) {
            return new ResultadoBusqueda(false, comparaciones,
                    camino.isEmpty() ? "null" : camino + " -> null");
        }

        comparaciones++;
        camino = camino.isEmpty() ? "" + nodo.getDato() : camino + " -> " + nodo.getDato();

        if (valor == nodo.getDato()) {
            return new ResultadoBusqueda(true, comparaciones, camino);
        }
        if (valor < nodo.getDato()) {
            return trazaRec(nodo.getIzquierdo(), valor, comparaciones, camino);
        }
        return trazaRec(nodo.getDerecho(), valor, comparaciones, camino);
    }

    // ------------------------------------------------------------
    // ALTURA (en aristas): árbol vacío = -1, hoja = 0
    // ------------------------------------------------------------
    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(NodoArbol<Integer> nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(alturaRec(nodo.getIzquierdo()), alturaRec(nodo.getDerecho()));
    }

    // ------------------------------------------------------------
    // Utilidades para mostrar el árbol
    // ------------------------------------------------------------
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

    /** Devuelve el árbol en formato DOT (Graphviz) para generar el diagrama. */
    public String aDot(String titulo) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph BST {\n  label=\"").append(titulo).append("\";\n  labelloc=t;\n");
        sb.append("  node [shape=circle, style=filled, fillcolor=\"#cfe8ff\"];\n  edge [fontsize=10];\n");
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
