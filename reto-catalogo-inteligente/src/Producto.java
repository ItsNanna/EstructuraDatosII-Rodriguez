/**
 * Un producto del catálogo. El código es la clave del BST.
 */
public class Producto {
    private final int codigo;
    private final String nombre;
    private final int precio;

    public Producto(int codigo, String nombre, int precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getPrecio() { return precio; }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " - $" + precio;
    }
}
