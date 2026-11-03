import java.util.Map;
import java.util.TreeMap;

class NodoTrie {
    // cada hijo es un caracter -> siguiente nodo (TreeMap para que salgan en orden alfabetico)
    Map<Character, NodoTrie> hijos = new TreeMap<>();
    boolean finPalabra;
}
