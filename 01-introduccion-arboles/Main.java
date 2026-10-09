public class Main {

    public static void main(String[] args) {
        // Nivel 1
        NodoGeneral<String> raiz = new NodoGeneral<>("Empresa");

        // Nivel 2
        NodoGeneral<String> tecnologia = new NodoGeneral<>("Tecnología");
        NodoGeneral<String> finanzas = new NodoGeneral<>("Finanzas");
        NodoGeneral<String> talento = new NodoGeneral<>("Talento Humano");

        raiz.agregarHijo(tecnologia);
        raiz.agregarHijo(finanzas);
        raiz.agregarHijo(talento);

        // Nivel 3
        tecnologia.agregarHijo(new NodoGeneral<>("Desarrollo"));
        tecnologia.agregarHijo(new NodoGeneral<>("Soporte"));
        finanzas.agregarHijo(new NodoGeneral<>("Contabilidad"));
        finanzas.agregarHijo(new NodoGeneral<>("Tesorería"));
        talento.agregarHijo(new NodoGeneral<>("Selección"));
        talento.agregarHijo(new NodoGeneral<>("Nómina"));

        System.out.println("Raíz: " + raiz.getDato());
        System.out.println("Jerarquía completa:");
        mostrar(raiz, 0);
    }

    // Recorre el árbol e imprime cada nodo con sangría según su nivel
    public static void mostrar(NodoGeneral<String> nodo, int nivel) {
        String espacios = "";
        for (int i = 0; i < nivel; i++) {
            espacios += "    ";
        }
        System.out.println(espacios + "- " + nodo.getDato());

        for (NodoGeneral<String> hijo : nodo.getHijos()) {
            mostrar(hijo, nivel + 1);
        }
    }
}