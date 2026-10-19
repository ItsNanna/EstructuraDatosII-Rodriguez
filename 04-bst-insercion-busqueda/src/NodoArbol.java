/**
 * Nodo de un Árbol Binario de Búsqueda.
 * Guarda un dato y dos referencias: izquierdo y derecho.
 */
public class NodoArbol<T> {
    private T dato;
    private NodoArbol<T> izquierdo;
    private NodoArbol<T> derecho;

    public NodoArbol(T dato) {
        this.dato = dato;
    }

    public T getDato() { return dato; }
    public NodoArbol<T> getIzquierdo() { return izquierdo; }
    public NodoArbol<T> getDerecho() { return derecho; }

    public void setIzquierdo(NodoArbol<T> izquierdo) { this.izquierdo = izquierdo; }
    public void setDerecho(NodoArbol<T> derecho) { this.derecho = derecho; }
}
