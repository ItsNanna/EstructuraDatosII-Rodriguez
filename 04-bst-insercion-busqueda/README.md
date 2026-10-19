# Guía 4 – BST I: inserción y búsqueda

**Asignatura:** Estructura de Datos II · **Semestre:** IV · **Ecosistema:** Java / GitHub

## Objetivo
Implementar inserción y búsqueda recursiva en un Árbol Binario de Búsqueda (BST) y analizar su eficiencia.

## Estructura de la carpeta
```
04-bst-insercion-busqueda/
├── src/            NodoArbol.java, ResultadoBusqueda.java, ArbolBST.java, Main.java
├── evidencia/      diagramas (.png/.dot) y tabla comparativa (.csv)
├── Resultados.txt  salida de la ejecución
└── README.md
```

## Compilar y ejecutar
```bash
cd evidencia
javac -encoding UTF-8 -d ../out ../src/*.java
java -cp ../out Main
```

## Propiedad BST
Menor que el nodo → izquierda. Mayor o igual → derecha.

## Ejercicio 1: 50, 30, 70, 20, 40, 60, 80
```
        50
       /  \
     30    70
    / \    / \
   20 40  60 80
```
Diagrama: `evidencia/bst7.png`

## Ejercicio 2: búsqueda con camino
| Valor | Resultado | Camino | Comparaciones |
|---|---|---|---|
| 60 | existe | 50 → 70 → 60 | 3 |
| 65 | no existe | 50 → 70 → 60 → null | 3 |

## Diagramas (15 códigos de productos)
- Inserción aleatoria: `evidencia/bst_aleatorio.png` (altura 4)
- Inserción ordenada: `evidencia/bst_ordenado.png` (altura 14, una "lista enlazada")

Datos usados: los mismos 15 códigos en dos órdenes.
Aleatorio: 450, 780, 130, 610, 320, 950, 60, 520, 210, 840, 390, 170, 700, 280, 890.
Ordenado: 60, 130, 170, 210, 280, 320, 390, 450, 520, 610, 700, 780, 840, 890, 950.
Consultas: 60, 390, 700, 950 (existen) y 1000 (no existe).

## Tabla comparativa
| Escenario | Nodos | Altura (aristas) | Comparaciones totales (5 búsquedas) | Promedio |
|---|---|---|---|---|
| Aleatorio | 15 | 4 | 17 | 3,40 |
| Ordenado | 15 | 14 | 49 | 9,80 |

## Análisis: O(log n) frente a O(n)
En cada paso, insertar y buscar descartan uno de los dos subárboles, así que se recorre **un solo camino** de la raíz hasta una hoja. El costo es proporcional a la **altura h** del árbol.

- **Árbol razonablemente balanceado:** h ≈ log₂(n). Con 15 nodos serían unas 4 comparaciones. Costo **O(log n)**. En la prueba aleatoria, la altura fue 4 y el promedio 3,40 comparaciones.
- **Árbol degenerado:** h = n − 1. Cada nodo tiene un solo hijo y el árbol se vuelve una lista. Costo **O(n)**. En la prueba ordenada, la altura fue 14 y el promedio 9,80 (buscar 950 o 1000 costó 15 comparaciones, es decir, los 15 nodos).

### ¿Por qué insertar datos ordenados degrada el rendimiento?
Si los datos llegan de menor a mayor, cada valor nuevo es mayor que todos los anteriores. Entonces siempre se va a la derecha del último nodo, y el árbol crece como una cadena. La propiedad BST se cumple, pero se pierde el beneficio de descartar la mitad del árbol en cada paso, y buscar equivale a recorrer una lista.
