# Guía 11 – Trie: mini motor de autocompletado

Estructura de Datos II · Semestre IV · Java

## Qué hace
Un Trie guarda palabras letra por letra, compartiendo los prefijos. Con él se puede insertar
palabras, buscarlas y sugerir hasta 5 palabras a partir de un prefijo.

## Archivos
- `NodoTrie.java`: nodo con `Map<Character, NodoTrie> hijos` y `boolean finPalabra`.
- `Trie.java`: `insertar`, `buscar`, `empiezaCon` y `sugerir` (máximo 5).
- `Main.java`: carga 38 palabras, prueba 5 prefijos (más uno que no existe) y tiene modo interactivo.
- `salida.txt`: salida de la ejecución.

## Cómo ejecutar
```
javac *.java
java Main
```

## Estructura
- Cada nodo representa una letra. Los hijos se guardan en un `TreeMap` (así las sugerencias salen en orden alfabético).
- `finPalabra = true` marca que en ese nodo termina una palabra completa.
  Por eso `buscar("pro")` da `false` aunque `empiezaCon("pro")` dé `true`.

## Cómo funciona cada método
- `insertar`: recorre la palabra letra por letra, crea el nodo si no existe y marca el último con `finPalabra`.
- `buscar`: baja por las letras; es `true` solo si llega a un nodo con `finPalabra`.
- `empiezaCon`: igual que buscar, pero solo revisa que el camino exista.
- `sugerir`: baja hasta el nodo del prefijo y desde ahí recorre sus hijos (recursión), guardando las palabras
  completas hasta tener 5.

## Complejidad (L = longitud de la palabra o prefijo)
| Operación | Tiempo |
|---|---|
| insertar | O(L) |
| buscar | O(L) |
| empiezaCon | O(L) |
| sugerir | O(L + recorrido del subárbol hasta tener 5) |

Depende de la longitud de la palabra, no de cuántas palabras haya guardadas.

## Trie vs. lista de 1000 palabras
En una lista toca revisar palabra por palabra: hasta 1000 comparaciones por consulta, O(N·L).
En el Trie solo se avanzan L pasos (las letras del prefijo), aunque el diccionario tenga 1000 o 1.000.000 de palabras.
La desventaja del Trie es que gasta más memoria (un nodo por letra).

## Pruebas (salida real)
```
Palabras cargadas: 38

--- Pruebas de buscar y empiezaCon ---
buscar(nodo): true
buscar(pro): false
buscar(casa): false
empiezaCon(prog): true
empiezaCon(zz): false

--- Sugerencias (maximo 5) ---
pro -> [procesador, proceso, producto, programa, programacion]
est -> [estado, estandar, estrategia, estructura, estudiante]
ar -> [arbol, archivo, arreglo]
co -> [codigo, cola, compilador, consulta]
di -> [diccionario, directorio, diseno]
xyz -> []
```
`pro` tiene 9 palabras en el diccionario, pero solo se muestran 5 por el límite.
