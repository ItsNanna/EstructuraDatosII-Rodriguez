/**
 * Nodo del arbol: guarda un Producto y apunta a sus dos hijos.
 */
class NodoBST {
    Producto dato;
    NodoBST izq;
    NodoBST der;

    NodoBST(Producto dato) {
        this.dato = dato;
        this.izq = null;
        this.der = null;
    }
}
