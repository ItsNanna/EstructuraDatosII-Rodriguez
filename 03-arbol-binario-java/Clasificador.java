public class Clasificador {

    // Lleno: cada nodo tiene 0 o 2 hijos
    public static <T> boolean esLleno(NodoArbol<T> nodo) {
        if (nodo == null) return true;
        if (nodo.grado() == 1) return false;
        return esLleno(nodo.getIzquierdo()) && esLleno(nodo.getDerecho());
    }

    // Completo: se numeran los nodos por niveles (raíz 1, hijos 2i y 2i+1)
    // y ningún número puede pasarse del total de nodos
    public static <T> boolean esCompleto(ArbolBinario<T> arbol) {
        return esCompleto(arbol.getRaiz(), 1, arbol.contarNodos());
    }

    private static <T> boolean esCompleto(NodoArbol<T> nodo, int pos, int total) {
        if (nodo == null) return true;
        if (pos > total) return false;
        return esCompleto(nodo.getIzquierdo(), 2 * pos, total)
            && esCompleto(nodo.getDerecho(), 2 * pos + 1, total);
    }

    // Perfecto: nodos = 2^(altura + 1) - 1
    public static <T> boolean esPerfecto(ArbolBinario<T> arbol) {
        int nodos = arbol.contarNodos();
        int altura = arbol.altura();
        return nodos == (int) Math.pow(2, altura + 1) - 1;
    }

    // Degenerado: ningún nodo tiene 2 hijos
    public static <T> boolean esDegenerado(NodoArbol<T> nodo) {
        if (nodo == null) return true;
        if (nodo.grado() == 2) return false;
        return esDegenerado(nodo.getIzquierdo()) && esDegenerado(nodo.getDerecho());
    }

    public static <T> void mostrar(ArbolBinario<T> arbol) {
        if (arbol.getRaiz() == null) {
            System.out.println("El árbol está vacío.");
            return;
        }
        System.out.println("Nodos: " + arbol.contarNodos()
            + " | Hojas: " + arbol.contarHojas()
            + " | Altura: " + arbol.altura());
        System.out.println("Lleno: " + esLleno(arbol.getRaiz()));
        System.out.println("Completo: " + esCompleto(arbol));
        System.out.println("Perfecto: " + esPerfecto(arbol));
        System.out.println("Degenerado: " + esDegenerado(arbol.getRaiz()));
    }
}
