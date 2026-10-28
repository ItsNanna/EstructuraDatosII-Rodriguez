/**
 * Producto del inventario.
 * El codigo es la clave que usa el BST para ordenar.
 */
class Producto {
    int codigo;
    String nombre;
    int cantidad;

    Producto(int codigo, String nombre, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " - stock: " + cantidad;
    }
}
