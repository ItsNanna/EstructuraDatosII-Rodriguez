import java.util.ArrayList;
import java.util.List;

public class NodoGeneral<T> {

    private T dato;
    private List<NodoGeneral<T>> hijos;

    public NodoGeneral(T dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    public T getDato() {
        return dato;
    }

    public List<NodoGeneral<T>> getHijos() {
        return hijos;
    }

    public void agregarHijo(NodoGeneral<T> hijo) {
        hijos.add(hijo);
    }

    // El grado de un nodo es su numero de hijos
    public int getGrado() {
        return hijos.size();
    }
}