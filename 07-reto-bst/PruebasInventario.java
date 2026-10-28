import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Pruebas del sistema. Se ejecutan solas con:  java PruebasInventario
 * Tambien se usa desde el menu (opcion 6) para comparar insercion ordenada vs aleatoria.
 */
class PruebasInventario {

    static int fallos = 0;

    static void verificar(String descripcion, boolean condicion) {
        if (condicion) {
            System.out.println("[OK]    " + descripcion);
        } else {
            System.out.println("[FALLO] " + descripcion);
            fallos++;
        }
    }

    static boolean estaOrdenado(ArrayList<Producto> lista) {
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i - 1).codigo >= lista.get(i).codigo) {
                return false;
            }
        }
        return true;
    }

    static void ejecutarPruebas() {
        System.out.println("=== PRUEBAS Y VALIDACIONES ===");
        ArbolInventario arbol = new ArbolInventario();

        // Arbol vacio
        verificar("Arbol vacio: buscar devuelve null", arbol.buscar(10) == null);
        verificar("Arbol vacio: eliminar devuelve false", !arbol.eliminar(10));
        verificar("Arbol vacio: menor y mayor son null", arbol.menor() == null && arbol.mayor() == null);

        // Registro
        verificar("Registrar 50", arbol.registrar(new Producto(50, "Teclado", 10)));
        verificar("Registrar 30", arbol.registrar(new Producto(30, "Mouse", 25)));
        verificar("Registrar 70", arbol.registrar(new Producto(70, "Monitor", 5)));
        verificar("Registrar 20", arbol.registrar(new Producto(20, "Cable HDMI", 40)));
        verificar("Registrar 40", arbol.registrar(new Producto(40, "Camara", 8)));
        verificar("Registrar 60", arbol.registrar(new Producto(60, "Parlante", 12)));
        verificar("Registrar 80", arbol.registrar(new Producto(80, "Audifonos", 15)));
        verificar("Tamano es 7", arbol.getTamano() == 7);

        // Duplicados
        verificar("Duplicado 50 es rechazado", !arbol.registrar(new Producto(50, "Otro", 1)));
        verificar("Tamano sigue en 7 tras duplicado", arbol.getTamano() == 7);
        verificar("El producto 50 no fue sobrescrito", arbol.buscar(50).nombre.equals("Teclado"));

        // Busqueda
        verificar("Buscar 40 lo encuentra", arbol.buscar(40) != null);
        verificar("Buscar 999 (inexistente) devuelve null", arbol.buscar(999) == null);

        // Comparaciones
        arbol.buscar(50);
        verificar("Buscar la raiz (50) usa 1 comparacion", arbol.getComparaciones() == 1);
        arbol.buscar(40);
        verificar("Buscar 40 usa 3 comparaciones (50, 30, 40)", arbol.getComparaciones() == 3);

        // Menor y mayor
        verificar("Menor es 20", arbol.menor().codigo == 20);
        verificar("Mayor es 80", arbol.mayor().codigo == 80);

        // Invariante de orden
        verificar("Inorden esta ordenado por codigo", estaOrdenado(arbol.inorden()));

        // Eliminacion: hoja, un hijo, dos hijos
        verificar("Eliminar hoja (20)", arbol.eliminar(20) && arbol.buscar(20) == null);
        verificar("Eliminar nodo con un hijo (30)", arbol.eliminar(30) && arbol.buscar(30) == null);
        verificar("Eliminar nodo con dos hijos (50, la raiz)", arbol.eliminar(50) && arbol.buscar(50) == null);
        verificar("Eliminar inexistente (999) devuelve false", !arbol.eliminar(999));
        verificar("Tamano es 4 tras 3 eliminaciones", arbol.getTamano() == 4);
        verificar("Inorden sigue ordenado tras eliminar", estaOrdenado(arbol.inorden()));
        verificar("Los demas productos siguen (40, 60, 70, 80)",
                arbol.buscar(40) != null && arbol.buscar(60) != null
                        && arbol.buscar(70) != null && arbol.buscar(80) != null);

        System.out.println();
        if (fallos == 0) {
            System.out.println("Todas las pruebas pasaron.");
        } else {
            System.out.println("Pruebas con fallos: " + fallos);
        }
    }

    // Compara insercion ordenada vs aleatoria con n productos.
    static void compararInsercion(int n) {
        ArbolInventario ordenado = new ArbolInventario();
        for (int i = 1; i <= n; i++) {
            ordenado.registrar(new Producto(i, "Prod" + i, 1));
        }

        ArrayList<Integer> codigos = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            codigos.add(i);
        }
        Collections.shuffle(codigos, new Random(42)); // semilla fija: resultados repetibles
        ArbolInventario aleatorio = new ArbolInventario();
        for (int c : codigos) {
            aleatorio.registrar(new Producto(c, "Prod" + c, 1));
        }

        // Se busca el ultimo codigo (n), el peor caso del arbol ordenado
        ordenado.buscar(n);
        int compOrdenado = ordenado.getComparaciones();
        aleatorio.buscar(n);
        int compAleatorio = aleatorio.getComparaciones();

        System.out.println("=== INSERCION ORDENADA vs ALEATORIA (n = " + n + ") ===");
        System.out.println("                      Ordenada   Aleatoria");
        System.out.printf("Altura del arbol      %-10d %d%n", ordenado.altura(), aleatorio.altura());
        System.out.printf("Comparaciones buscar(%d) %-7d %d%n", n, compOrdenado, compAleatorio);
        System.out.println("Con datos ordenados el BST se vuelve una lista (altura = n).");
    }

    public static void main(String[] args) {
        ejecutarPruebas();
        System.out.println();
        compararInsercion(1000);
    }
}
