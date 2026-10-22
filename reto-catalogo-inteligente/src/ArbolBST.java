/**
 * Catálogo de productos organizado como un Árbol Binario de Búsqueda.
 * La clave es el código del producto:
 *  - código menor -> subárbol izquierdo
 *  - código mayor -> subárbol derecho
 * (en un catálogo el código es único, así que no se permiten repetidos)
 */
public class ArbolBST {

    private NodoArbol<Producto> raiz;

    // ------------------------------------------------------------
    // INSERTAR (recursivo). Devuelve false si el código ya existe.
    // ------------------------------------------------------------
    public boolean insertar(Producto producto) {
        if (buscar(producto.getCodigo()) != null) return false;
        raiz = insertarRec(raiz, producto);
        return true;
    }

    private NodoArbol<Producto> insertarRec(NodoArbol<Producto> nodo, Producto producto) {
        // Caso base: llegamos a un hueco, aquí se crea el nodo nuevo.
        if (nodo == null) return new NodoArbol<>(producto);

        // Caso recursivo: se baja por un solo lado.
        if (producto.getCodigo() < nodo.getDato().getCodigo()) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), producto));
        } else {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), producto));
        }
        return nodo;
    }

    // ------------------------------------------------------------
    // BUSCAR (recursivo). Devuelve el producto o null si no existe.
    // ------------------------------------------------------------
    public Producto buscar(int codigo) {
        return buscarRec(raiz, codigo);
    }

    private Producto buscarRec(NodoArbol<Producto> nodo, int codigo) {
        if (nodo == null) return null;                          // no existe
        if (codigo == nodo.getDato().getCodigo()) return nodo.getDato();
        return codigo < nodo.getDato().getCodigo()
                ? buscarRec(nodo.getIzquierdo(), codigo)
                : buscarRec(nodo.getDerecho(), codigo);
    }

    // ------------------------------------------------------------
    // BUSCAR con evidencia: camino recorrido y número de comparaciones
    // ------------------------------------------------------------
    public ResultadoBusqueda buscarConTraza(int codigo) {
        return trazaRec(raiz, codigo, 0, "");
    }

    private ResultadoBusqueda trazaRec(NodoArbol<Producto> nodo, int codigo,
                                       int comparaciones, String camino) {
        if (nodo == null) {
            return new ResultadoBusqueda(null, comparaciones,
                    camino.isEmpty() ? "null" : camino + " -> null");
        }

        comparaciones++;
        int actual = nodo.getDato().getCodigo();
        camino = camino.isEmpty() ? "" + actual : camino + " -> " + actual;

        if (codigo == actual) {
            return new ResultadoBusqueda(nodo.getDato(), comparaciones, camino);
        }
        if (codigo < actual) {
            return trazaRec(nodo.getIzquierdo(), codigo, comparaciones, camino);
        }
        return trazaRec(nodo.getDerecho(), codigo, comparaciones, camino);
    }

    // ------------------------------------------------------------
    // ALTURA (en aristas): árbol vacío = -1, hoja = 0
    // ------------------------------------------------------------
    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(NodoArbol<Producto> nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(alturaRec(nodo.getIzquierdo()), alturaRec(nodo.getDerecho()));
    }

    // ------------------------------------------------------------
    // Utilidades para mostrar el árbol
    // ------------------------------------------------------------
    /** Inorden: muestra el catálogo ordenado por código. */
    public void mostrarCatalogo() {
        catalogoRec(raiz);
    }

    private void catalogoRec(NodoArbol<Producto> nodo) {
        if (nodo == null) return;
        catalogoRec(nodo.getIzquierdo());
        System.out.println(nodo.getDato());
        catalogoRec(nodo.getDerecho());
    }

    /** Imprime el árbol "acostado": la raíz a la izquierda, derecha arriba. */
    public void imprimirArbol() {
        imprimirRec(raiz, 0);
    }

    private void imprimirRec(NodoArbol<Producto> nodo, int nivel) {
        if (nodo == null) return;
        imprimirRec(nodo.getDerecho(), nivel + 1);
        System.out.println("    ".repeat(nivel) + nodo.getDato().getCodigo());
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

    private void dotRec(NodoArbol<Producto> nodo, StringBuilder sb) {
        if (nodo == null) return;
        int c = nodo.getDato().getCodigo();
        if (nodo.getIzquierdo() != null) {
            sb.append("  ").append(c).append(" -> ").append(nodo.getIzquierdo().getDato().getCodigo()).append(" [label=\"izq\"];\n");
            dotRec(nodo.getIzquierdo(), sb);
        }
        if (nodo.getDerecho() != null) {
            sb.append("  ").append(c).append(" -> ").append(nodo.getDerecho().getDato().getCodigo()).append(" [label=\"der\"];\n");
            dotRec(nodo.getDerecho(), sb);
        }
    }
}
