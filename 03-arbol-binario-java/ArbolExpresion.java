public class ArbolExpresion {

    public static void main(String[] args) {

        // (7 + 3) * (6 - 2)
        NodoArbol<String> por = new NodoArbol<>("*");
        NodoArbol<String> mas = new NodoArbol<>("+");
        NodoArbol<String> menos = new NodoArbol<>("-");
        NodoArbol<String> siete = new NodoArbol<>("7");
        NodoArbol<String> tres = new NodoArbol<>("3");
        NodoArbol<String> seis = new NodoArbol<>("6");
        NodoArbol<String> dos = new NodoArbol<>("2");

        por.setIzquierdo(mas);
        por.setDerecho(menos);

        mas.setIzquierdo(siete);
        mas.setDerecho(tres);

        menos.setIzquierdo(seis);
        menos.setDerecho(dos);

        ArbolBinario<String> arbol = new ArbolBinario<>(por);

        System.out.println("=== ÁRBOL DE EXPRESIÓN ===");
        arbol.imprimirArbol();

        System.out.println("\n(7 + 3) * (6 - 2) = " + evaluar(por));
    }

    static int evaluar(NodoArbol<String> nodo) {
        if (nodo == null) {
            throw new IllegalArgumentException("Nodo null");
        }

        // Las hojas son los números
        if (nodo.esHoja()) {
            return Integer.parseInt(nodo.getDato());
        }

        int izq = evaluar(nodo.getIzquierdo());
        int der = evaluar(nodo.getDerecho());

        switch (nodo.getDato()) {
            case "+": return izq + der;
            case "-": return izq - der;
            case "*": return izq * der;
            case "/": return izq / der;
            default: throw new IllegalArgumentException("Operador no válido: " + nodo.getDato());
        }
    }
}
