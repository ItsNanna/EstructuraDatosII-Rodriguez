import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();

        String[] diccionario = {
            "programa", "programacion", "programador", "proceso", "procesador",
            "producto", "proyecto", "protocolo", "propiedad", "promedio",
            "estructura", "estado", "estudiante", "estandar", "estrategia",
            "arbol", "arreglo", "archivo", "algoritmo", "analisis",
            "clase", "codigo", "compilador", "cadena", "consulta",
            "dato", "diccionario", "diseno", "depuracion", "directorio",
            "lista", "pila", "cola", "grafo", "mapa", "nodo", "raiz", "hoja"
        };
        for (String p : diccionario) {
            trie.insertar(p);
        }
        System.out.println("Palabras cargadas: " + diccionario.length);

        System.out.println("\n--- Pruebas de buscar y empiezaCon ---");
        System.out.println("buscar(nodo): " + trie.buscar("nodo"));
        System.out.println("buscar(pro): " + trie.buscar("pro"));
        System.out.println("buscar(casa): " + trie.buscar("casa"));
        System.out.println("empiezaCon(prog): " + trie.empiezaCon("prog"));
        System.out.println("empiezaCon(zz): " + trie.empiezaCon("zz"));

        System.out.println("\n--- Sugerencias (maximo 5) ---");
        String[] prefijos = {"pro", "est", "ar", "co", "di", "xyz"};
        for (String prefijo : prefijos) {
            System.out.println(prefijo + " -> " + trie.sugerir(prefijo));
        }

        // Autocompletado interactivo
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEscribe un prefijo (o 'salir'):");
        while (sc.hasNextLine()) {
            String texto = sc.nextLine().trim();
            if (texto.equals("salir")) {
                break;
            }
            System.out.println(trie.sugerir(texto));
        }
    }
}
