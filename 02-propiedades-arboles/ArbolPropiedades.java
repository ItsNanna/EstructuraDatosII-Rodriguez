public class ArbolPropiedades {

    // Altura de un nodo: longitud del camino mas largo hasta una hoja
    static int altura(NodoGeneral<?> nodo) {
        if (nodo.getHijos().isEmpty()) return 0;
        int max = 0;
        for (NodoGeneral<?> hijo : nodo.getHijos()) {
            max = Math.max(max, altura(hijo));
        }
        return 1 + max;
    }

    // Recorre el arbol imprimiendo grado, profundidad y altura de cada nodo
    static void recorrer(NodoGeneral<?> nodo, int profundidad) {
        System.out.println(nodo.getDato() + "\tgrado=" + nodo.getGrado()
                + "\tprofundidad=" + profundidad + "\taltura=" + altura(nodo));
        for (NodoGeneral<?> hijo : nodo.getHijos()) {
            recorrer(hijo, profundidad + 1);
        }
    }

    public static void main(String[] args) {
        // Construccion del arbol de trabajo (13 nodos, 4 niveles)
        NodoGeneral<String> a = new NodoGeneral<>("A");
        NodoGeneral<String> b = new NodoGeneral<>("B");
        NodoGeneral<String> c = new NodoGeneral<>("C");
        NodoGeneral<String> d = new NodoGeneral<>("D");
        NodoGeneral<String> e = new NodoGeneral<>("E");
        NodoGeneral<String> f = new NodoGeneral<>("F");
        NodoGeneral<String> g = new NodoGeneral<>("G");
        NodoGeneral<String> h = new NodoGeneral<>("H");
        NodoGeneral<String> i = new NodoGeneral<>("I");
        NodoGeneral<String> j = new NodoGeneral<>("J");
        NodoGeneral<String> k = new NodoGeneral<>("K");
        NodoGeneral<String> l = new NodoGeneral<>("L");
        NodoGeneral<String> m = new NodoGeneral<>("M");

        a.agregarHijo(b);
        a.agregarHijo(c);
        a.agregarHijo(d);
        b.agregarHijo(e);
        b.agregarHijo(f);
        c.agregarHijo(g);
        d.agregarHijo(h);
        d.agregarHijo(i);
        e.agregarHijo(j);
        e.agregarHijo(k);
        g.agregarHijo(l);
        h.agregarHijo(m);

        System.out.println("=== Metricas por nodo ===");
        recorrer(a, 0);

        int alturaArbol = altura(a);
        System.out.println();
        System.out.println("Altura del arbol (calculada): " + alturaArbol);
        System.out.println("Altura del arbol (manual): 3");
        System.out.println("Coinciden: " + (alturaArbol == 3));

        // Ejercicio: agregar un nuevo nivel (nodo N como hijo de J)
        NodoGeneral<String> n = new NodoGeneral<>("N");
        j.agregarHijo(n);

        System.out.println();
        System.out.println("=== Despues de agregar N como hijo de J ===");
        System.out.println("Altura de J: " + altura(j));
        System.out.println("Altura de E: " + altura(e));
        System.out.println("Altura de B: " + altura(b));
        System.out.println("Altura de C: " + altura(c));
        System.out.println("Altura del arbol: " + altura(a));
    }
}