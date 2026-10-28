import java.util.Scanner;

/**
 * Menu de consola del sistema de inventario (BST).
 * Compilar:  javac *.java
 * Ejecutar:  java Main
 */
public class Main {

    static Scanner sc = new Scanner(System.in);

    // Lee un entero; si el usuario escribe otra cosa, vuelve a pedirlo.
    static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida: escribe un numero entero.");
            }
        }
    }

    static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            if (!linea.isEmpty()) {
                return linea;
            }
            System.out.println("El texto no puede estar vacio.");
        }
    }

    static void mostrarMenu() {
        System.out.println();
        System.out.println("===== INVENTARIO (BST) =====");
        System.out.println("1. Registrar producto");
        System.out.println("2. Buscar producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Listar inventario (Inorden)");
        System.out.println("5. Ver producto de menor y mayor codigo");
        System.out.println("6. Comparar insercion ordenada vs aleatoria");
        System.out.println("7. Ejecutar pruebas automaticas");
        System.out.println("0. Salir");
    }

    static void registrar(ArbolInventario arbol) {
        int codigo = leerEntero("Codigo: ");
        if (codigo <= 0) {
            System.out.println("El codigo debe ser un entero positivo.");
            return;
        }
        String nombre = leerTexto("Nombre: ");
        int cantidad = leerEntero("Cantidad: ");
        if (cantidad < 0) {
            System.out.println("La cantidad no puede ser negativa.");
            return;
        }
        if (arbol.registrar(new Producto(codigo, nombre, cantidad))) {
            System.out.println("Producto registrado.");
        } else {
            System.out.println("Error: ya existe un producto con el codigo " + codigo + ".");
        }
    }

    static void buscar(ArbolInventario arbol) {
        int codigo = leerEntero("Codigo a buscar: ");
        Producto p = arbol.buscar(codigo);
        if (p != null) {
            System.out.println("Encontrado: " + p);
        } else {
            System.out.println("No existe un producto con el codigo " + codigo + ".");
        }
        System.out.println("Comparaciones realizadas: " + arbol.getComparaciones());
    }

    static void eliminar(ArbolInventario arbol) {
        int codigo = leerEntero("Codigo a eliminar: ");
        if (arbol.eliminar(codigo)) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("No existe un producto con el codigo " + codigo + ".");
        }
    }

    static void menorYMayor(ArbolInventario arbol) {
        if (arbol.estaVacio()) {
            System.out.println("El inventario esta vacio.");
            return;
        }
        System.out.println("Menor codigo: " + arbol.menor());
        System.out.println("Mayor codigo: " + arbol.mayor());
    }

    public static void main(String[] args) {
        ArbolInventario arbol = new ArbolInventario();
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opcion: ");
            switch (opcion) {
                case 1:
                    registrar(arbol);
                    break;
                case 2:
                    buscar(arbol);
                    break;
                case 3:
                    eliminar(arbol);
                    break;
                case 4:
                    arbol.listarInorden();
                    break;
                case 5:
                    menorYMayor(arbol);
                    break;
                case 6:
                    PruebasInventario.compararInsercion(1000);
                    break;
                case 7:
                    PruebasInventario.ejecutarPruebas();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }
}
