import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Trie {

    private final NodoTrie raiz = new NodoTrie();

    // Inserta una palabra: crea los nodos que falten y marca el ultimo
    void insertar(String palabra) {
        NodoTrie actual = raiz;
        for (char c : palabra.toLowerCase().toCharArray()) {
            actual = actual.hijos.computeIfAbsent(c, k -> new NodoTrie());
        }
        actual.finPalabra = true;
    }

    // Baja por el trie siguiendo el texto. Devuelve el ultimo nodo o null si no existe
    private NodoTrie bajar(String texto) {
        NodoTrie actual = raiz;
        for (char c : texto.toLowerCase().toCharArray()) {
            actual = actual.hijos.get(c);
            if (actual == null) {
                return null;
            }
        }
        return actual;
    }

    // true solo si la palabra completa fue insertada
    boolean buscar(String palabra) {
        NodoTrie nodo = bajar(palabra);
        return nodo != null && nodo.finPalabra;
    }

    // true si hay al menos una palabra que empiece con el prefijo
    boolean empiezaCon(String prefijo) {
        return bajar(prefijo) != null;
    }

    // Devuelve maximo 5 palabras que empiezan con el prefijo
    List<String> sugerir(String prefijo) {
        List<String> resultado = new ArrayList<>();
        NodoTrie nodo = bajar(prefijo);
        if (nodo != null) {
            recolectar(nodo, prefijo.toLowerCase(), resultado);
        }
        return resultado;
    }

    // Recorre el subarbol y va guardando las palabras completas
    private void recolectar(NodoTrie nodo, String palabra, List<String> resultado) {
        if (resultado.size() >= 5) {
            return;
        }
        if (nodo.finPalabra) {
            resultado.add(palabra);
        }
        for (Map.Entry<Character, NodoTrie> hijo : nodo.hijos.entrySet()) {
            recolectar(hijo.getValue(), palabra + hijo.getKey(), resultado);
        }
    }
}
