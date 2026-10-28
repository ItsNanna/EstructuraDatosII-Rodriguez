import java.util.ArrayList;

/**
 * BST de productos. La clave de comparacion es Producto.codigo.
 * Invariante: todo codigo del subarbol izquierdo es menor que el de la raiz
 * y todo codigo del subarbol derecho es mayor. No hay codigos repetidos.
 */
class ArbolInventario {
    private NodoBST raiz;
    private int tamano;
    private int comparaciones;   // comparaciones de la ultima busqueda
    private boolean eliminado;   // bandera auxiliar para eliminar()

    ArbolInventario() {
        raiz = null;
        tamano = 0;
        comparaciones = 0;
    }

    // ---------- REGISTRAR (insercion) ----------
    // Devuelve false si el codigo ya existe (no inserta).
    boolean registrar(Producto p) {
        NodoBST nuevo = new NodoBST(p);
        if (raiz == null) {
            raiz = nuevo;
            tamano++;
            return true;
        }
        NodoBST actual = raiz;
        while (true) {
            if (p.codigo == actual.dato.codigo) {
                return false; // duplicado
            }
            if (p.codigo < actual.dato.codigo) {
                if (actual.izq == null) {
                    actual.izq = nuevo;
                    break;
                }
                actual = actual.izq;
            } else {
                if (actual.der == null) {
                    actual.der = nuevo;
                    break;
                }
                actual = actual.der;
            }
        }
        tamano++;
        return true;
    }

    // ---------- BUSCAR ----------
    // Devuelve el producto o null si no existe.
    // Cuenta una comparacion por cada nodo visitado.
    Producto buscar(int codigo) {
        comparaciones = 0;
        NodoBST actual = raiz;
        while (actual != null) {
            comparaciones++;
            if (codigo == actual.dato.codigo) {
                return actual.dato;
            }
            if (codigo < actual.dato.codigo) {
                actual = actual.izq;
            } else {
                actual = actual.der;
            }
        }
        return null;
    }

    // ---------- ELIMINAR ----------
    // Devuelve false si el codigo no existe.
    boolean eliminar(int codigo) {
        eliminado = false;
        raiz = eliminar(raiz, codigo);
        if (eliminado) {
            tamano--;
        }
        return eliminado;
    }

    private NodoBST eliminar(NodoBST nodo, int codigo) {
        if (nodo == null) {
            return null; // no existe
        }
        if (codigo < nodo.dato.codigo) {
            nodo.izq = eliminar(nodo.izq, codigo);
        } else if (codigo > nodo.dato.codigo) {
            nodo.der = eliminar(nodo.der, codigo);
        } else {
            eliminado = true;
            // Caso 1 y 2: hoja o un solo hijo
            if (nodo.izq == null) {
                return nodo.der;
            }
            if (nodo.der == null) {
                return nodo.izq;
            }
            // Caso 3: dos hijos -> sucesor inorden (menor del subarbol derecho)
            NodoBST sucesor = nodo.der;
            while (sucesor.izq != null) {
                sucesor = sucesor.izq;
            }
            nodo.dato = sucesor.dato;
            nodo.der = eliminar(nodo.der, sucesor.dato.codigo);
        }
        return nodo;
    }

    // ---------- INORDEN ----------
    // Devuelve los productos ordenados por codigo.
    ArrayList<Producto> inorden() {
        ArrayList<Producto> lista = new ArrayList<>();
        inorden(raiz, lista);
        return lista;
    }

    private void inorden(NodoBST nodo, ArrayList<Producto> lista) {
        if (nodo == null) {
            return;
        }
        inorden(nodo.izq, lista);
        lista.add(nodo.dato);
        inorden(nodo.der, lista);
    }

    void listarInorden() {
        if (raiz == null) {
            System.out.println("El inventario esta vacio.");
            return;
        }
        for (Producto p : inorden()) {
            System.out.println(p);
        }
    }

    // ---------- MENOR Y MAYOR ----------
    Producto menor() {
        if (raiz == null) {
            return null;
        }
        NodoBST actual = raiz;
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual.dato;
    }

    Producto mayor() {
        if (raiz == null) {
            return null;
        }
        NodoBST actual = raiz;
        while (actual.der != null) {
            actual = actual.der;
        }
        return actual.dato;
    }

    // ---------- UTILIDADES ----------
    int altura() {
        return altura(raiz);
    }

    private int altura(NodoBST nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
    }

    int getTamano() {
        return tamano;
    }

    int getComparaciones() {
        return comparaciones;
    }

    boolean estaVacio() {
        return raiz == null;
    }
}
