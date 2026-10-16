public class Main {

    public static void main(String[] args) {

        // Nodos del árbol (letras de mi nombre)
        NodoArbol<String> d = new NodoArbol<>("D");
        NodoArbol<String> a = new NodoArbol<>("A");
        NodoArbol<String> n = new NodoArbol<>("N");
        NodoArbol<String> n2 = new NodoArbol<>("N2");
        NodoArbol<String> a2 = new NodoArbol<>("A2");
        NodoArbol<String> r = new NodoArbol<>("R");
        NodoArbol<String> o = new NodoArbol<>("O");
        NodoArbol<String> d2 = new NodoArbol<>("D2");
        NodoArbol<String> r2 = new NodoArbol<>("R2");
        NodoArbol<String> i = new NodoArbol<>("I");
        NodoArbol<String> g = new NodoArbol<>("G");
        NodoArbol<String> u = new NodoArbol<>("U");
        NodoArbol<String> e = new NodoArbol<>("E");
        NodoArbol<String> z = new NodoArbol<>("Z");

        /*
         *                 D
         *            /         \
         *          A             N
         *        /   \         /   \
         *      N2     A2      R     O
         *     /  \   /  \    / \   /
         *    D2  R2 I    G  U   E Z
         */
        d.setIzquierdo(a);
        d.setDerecho(n);

        a.setIzquierdo(n2);
        a.setDerecho(a2);

        n.setIzquierdo(r);
        n.setDerecho(o);

        n2.setIzquierdo(d2);
        n2.setDerecho(r2);

        a2.setIzquierdo(i);
        a2.setDerecho(g);

        r.setIzquierdo(u);
        r.setDerecho(e);

        o.setIzquierdo(z);

        ArbolBinario<String> arbol = new ArbolBinario<>(d);

        System.out.println("=== RAÍZ E HIJOS ===");
        arbol.imprimirRaizYHijos();

        System.out.println("\n=== ÁRBOL ===");
        arbol.imprimirArbol();

        System.out.println("\n=== PROPIEDADES ===");
        System.out.println("Nodos: " + arbol.contarNodos());
        System.out.println("Hojas: " + arbol.contarHojas());
        System.out.println("Altura: " + arbol.altura());
        System.out.println("Grado de D: " + d.grado());
        System.out.println("Grado de O: " + o.grado());

        // Clasificación de mi árbol
        System.out.println("\n=== CLASIFICACIÓN DE MI ÁRBOL ===");
        Clasificador.mostrar(arbol);

        // Los cuatro árboles pequeños
        System.out.println("\n=== ÁRBOL LLENO ===");
        Clasificador.mostrar(arbolLleno());

        System.out.println("\n=== ÁRBOL COMPLETO ===");
        Clasificador.mostrar(arbolCompleto());

        System.out.println("\n=== ÁRBOL PERFECTO ===");
        Clasificador.mostrar(arbolPerfecto());

        System.out.println("\n=== ÁRBOL DEGENERADO ===");
        Clasificador.mostrar(arbolDegenerado());

        // Pruebas con null
        System.out.println("\n=== CONTROL DE NULL ===");
        System.out.println("Hijo derecho de O: " + o.getDerecho());

        if (o.getDerecho() != null) {
            System.out.println(o.getDerecho().getDato());
        } else {
            System.out.println("O no tiene hijo derecho.");
        }

        System.out.println("¿O es hoja?: " + o.esHoja());
        System.out.println("¿Z es hoja?: " + z.esHoja());

        System.out.println("\nSubárbol desde O:");
        ArbolBinario<String> subarbol = new ArbolBinario<>(o);
        subarbol.imprimirRaizYHijos();

        System.out.println("\n=== ÁRBOL VACÍO ===");
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        vacio.imprimirRaizYHijos();
    }

    static ArbolBinario<Integer> arbolLleno() {
        NodoArbol<Integer> n1 = new NodoArbol<>(1);
        NodoArbol<Integer> n2 = new NodoArbol<>(2);
        NodoArbol<Integer> n3 = new NodoArbol<>(3);
        NodoArbol<Integer> n4 = new NodoArbol<>(4);
        NodoArbol<Integer> n5 = new NodoArbol<>(5);

        n1.setIzquierdo(n2);
        n1.setDerecho(n3);
        n3.setIzquierdo(n4);
        n3.setDerecho(n5);

        return new ArbolBinario<>(n1);
    }

    static ArbolBinario<Integer> arbolCompleto() {
        NodoArbol<Integer> n1 = new NodoArbol<>(1);
        NodoArbol<Integer> n2 = new NodoArbol<>(2);
        NodoArbol<Integer> n3 = new NodoArbol<>(3);
        NodoArbol<Integer> n4 = new NodoArbol<>(4);

        n1.setIzquierdo(n2);
        n1.setDerecho(n3);
        n2.setIzquierdo(n4);

        return new ArbolBinario<>(n1);
    }

    static ArbolBinario<Integer> arbolPerfecto() {
        NodoArbol<Integer> n1 = new NodoArbol<>(1);
        NodoArbol<Integer> n2 = new NodoArbol<>(2);
        NodoArbol<Integer> n3 = new NodoArbol<>(3);
        NodoArbol<Integer> n4 = new NodoArbol<>(4);
        NodoArbol<Integer> n5 = new NodoArbol<>(5);
        NodoArbol<Integer> n6 = new NodoArbol<>(6);
        NodoArbol<Integer> n7 = new NodoArbol<>(7);

        n1.setIzquierdo(n2);
        n1.setDerecho(n3);
        n2.setIzquierdo(n4);
        n2.setDerecho(n5);
        n3.setIzquierdo(n6);
        n3.setDerecho(n7);

        return new ArbolBinario<>(n1);
    }

    static ArbolBinario<Integer> arbolDegenerado() {
        NodoArbol<Integer> n1 = new NodoArbol<>(1);
        NodoArbol<Integer> n2 = new NodoArbol<>(2);
        NodoArbol<Integer> n3 = new NodoArbol<>(3);
        NodoArbol<Integer> n4 = new NodoArbol<>(4);

        n1.setDerecho(n2);
        n2.setIzquierdo(n3);
        n3.setDerecho(n4);

        return new ArbolBinario<>(n1);
    }
}
