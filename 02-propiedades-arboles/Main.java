public class Main {

    public static void main(String[] args) {

        // ============================================================
        // 1. CREACIÓN DE LOS NODOS
        // Letras de "DANNARODRIGUEZ". Si una letra se repite,
        // la segunda vez lleva un 2 (N2, A2, D2, R2).
        // ============================================================

        // Nivel 0
        NodoGeneral<String> d = new NodoGeneral<>("D");

        // Nivel 1
        NodoGeneral<String> a = new NodoGeneral<>("A");
        NodoGeneral<String> n = new NodoGeneral<>("N");
        NodoGeneral<String> n2 = new NodoGeneral<>("N2");

        // Nivel 2
        NodoGeneral<String> a2 = new NodoGeneral<>("A2");
        NodoGeneral<String> r = new NodoGeneral<>("R");
        NodoGeneral<String> o = new NodoGeneral<>("O");
        NodoGeneral<String> d2 = new NodoGeneral<>("D2");
        NodoGeneral<String> r2 = new NodoGeneral<>("R2");

        // Nivel 3
        NodoGeneral<String> i = new NodoGeneral<>("I");
        NodoGeneral<String> g = new NodoGeneral<>("G");
        NodoGeneral<String> u = new NodoGeneral<>("U");
        NodoGeneral<String> e = new NodoGeneral<>("E");
        NodoGeneral<String> z = new NodoGeneral<>("Z");

        // ============================================================
        // 2. CONSTRUCCIÓN DE LA JERARQUÍA
        // ============================================================

        d.agregarHijo(a);
        d.agregarHijo(n);
        d.agregarHijo(n2);

        a.agregarHijo(a2);
        a.agregarHijo(r);

        n.agregarHijo(o);
        n.agregarHijo(d2);

        n2.agregarHijo(r2);

        a2.agregarHijo(i);
        a2.agregarHijo(g);

        r.agregarHijo(u);

        d2.agregarHijo(e);
        d2.agregarHijo(z);

        // ============================================================
        // 3. MOSTRAR EL ÁRBOL
        // ============================================================

        System.out.println("=== ÁRBOL DANNARODRIGUEZ ===");
        MetricasArbol.mostrarArbol(d, 0);

        // ============================================================
        // 4. PROPIEDADES GENERALES
        // ============================================================

        System.out.println();
        System.out.println("=== PROPIEDADES DEL ÁRBOL ===");

        System.out.println("Raíz: " + d.getDato());
        System.out.println("Número total de nodos: " + MetricasArbol.contarNodos(d));
        System.out.println("Número de hojas: " + MetricasArbol.contarHojas(d));
        System.out.println("Grado del árbol: " + MetricasArbol.gradoArbol(d));
        System.out.println("Altura del árbol: " + MetricasArbol.altura(d));
        System.out.println("Número de niveles: " + (MetricasArbol.altura(d) + 1));

        // ============================================================
        // 5. PROFUNDIDAD DE CINCO NODOS
        // ============================================================

        System.out.println();
        System.out.println("=== PROFUNDIDAD DE CINCO NODOS ===");

        mostrarProfundidad(d, a);
        mostrarProfundidad(d, a2);
        mostrarProfundidad(d, i);
        mostrarProfundidad(d, d2);
        mostrarProfundidad(d, z);

        // ============================================================
        // 6. GRADO DE NODOS
        // ============================================================

        System.out.println();
        System.out.println("=== GRADO DE ALGUNOS NODOS ===");

        mostrarGrado(d);
        mostrarGrado(a);
        mostrarGrado(n);
        mostrarGrado(n2);
        mostrarGrado(i);

        // ============================================================
        // 7. TABLA COMPLETA DE MÉTRICAS
        // ============================================================

        System.out.println();
        System.out.println("=== TABLA DE MÉTRICAS ===");
        MetricasArbol.mostrarTablaMetricas(d);

        // ============================================================
        // 8. DOS CAMINOS Y SU LONGITUD
        // ============================================================

        System.out.println();
        System.out.println("=== CAMINOS ===");

        mostrarCamino(d, i);
        mostrarCamino(d, r2);

        // ============================================================
        // 9. COMPARACIÓN MANUAL VS. CÓDIGO
        // ============================================================

        System.out.println();
        System.out.println("=== COMPARACIÓN ===");

        int alturaManual = 3;
        int alturaCalculada = MetricasArbol.altura(d);

        System.out.println("Altura calculada manualmente: " + alturaManual);
        System.out.println("Altura calculada por Java: " + alturaCalculada);
        System.out.println(
                alturaManual == alturaCalculada
                        ? "Resultado: COINCIDEN."
                        : "Resultado: REVISAR CÁLCULO."
        );

        // ============================================================
        // 10. RETO: AGREGAR UN NUEVO NIVEL
        // ============================================================

        System.out.println();
        System.out.println("=== RETO: NUEVO NIVEL ===");

        System.out.println("Altura del árbol antes: " + MetricasArbol.altura(d));
        System.out.println("Número de niveles antes: " + (MetricasArbol.altura(d) + 1));

        NodoGeneral<String> nuevo = new NodoGeneral<>("S");
        i.agregarHijo(nuevo);

        System.out.println();
        System.out.println("Se agregó 'S' como hijo de 'I'");
        System.out.println("Altura del árbol después: " + MetricasArbol.altura(d));
        System.out.println("Número de niveles después: " + (MetricasArbol.altura(d) + 1));
        System.out.println("Número total de nodos: " + MetricasArbol.contarNodos(d));

        System.out.println();
        System.out.println("Altura de los nodos del camino afectado:");
        mostrarAltura(i);
        mostrarAltura(a2);
        mostrarAltura(a);
        mostrarAltura(d);

        System.out.println();
        System.out.println("Altura de un nodo fuera del camino (no cambia):");
        mostrarAltura(n);
    }

    private static void mostrarProfundidad(NodoGeneral<?> raiz, NodoGeneral<?> nodo) {
        System.out.printf("%-8s -> %d%n", nodo.getDato(), MetricasArbol.profundidad(raiz, nodo));
    }

    private static void mostrarGrado(NodoGeneral<?> nodo) {
        System.out.printf("%-8s -> %d%n", nodo.getDato(), MetricasArbol.gradoNodo(nodo));
    }

    private static void mostrarAltura(NodoGeneral<?> nodo) {
        System.out.printf("%-8s -> %d%n", nodo.getDato(), MetricasArbol.altura(nodo));
    }

    private static void mostrarCamino(NodoGeneral<?> raiz, NodoGeneral<?> destino) {
        var ruta = MetricasArbol.camino(raiz, destino);

        System.out.println(MetricasArbol.rutaComoTexto(ruta));
        System.out.println("Longitud: " + MetricasArbol.longitudCamino(raiz, destino) + " aristas");
    }
}
