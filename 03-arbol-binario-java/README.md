# Guía 3 - Árbol binario y modelado en Java

Estructura de Datos II - Ingeniería de Software, semestre IV

## Mi árbol

Hice el árbol con las letras de mi nombre, DANNARODRIGUEZ (14 nodos). Las letras repetidas llevan un 2 (N2, A2, D2, R2).

```
                 D
            /         \
          A             N
        /   \         /   \
      N2     A2      R     O
     /  \   /  \    / \   /
    D2  R2 I    G  U   E Z
```

- Nodos: 14
- Hojas: 7 (D2, R2, I, G, U, E, Z)
- Altura: 3

## Clasificación

Mi árbol es **completo**: los niveles 0, 1 y 2 están llenos y el último nivel se llena de izquierda a derecha sin dejar huecos.

- No es lleno porque O tiene un solo hijo.
- No es perfecto porque al último nivel le falta un nodo.
- No es degenerado porque varios nodos tienen dos hijos.

Los cuatro árboles pequeños (lleno, completo, perfecto y degenerado) están en `diagramas.pdf` y también se prueban en `Main.java`.

## Modelo en memoria

Cada nodo es un objeto `NodoArbol<T>` con tres atributos: `dato`, `izquierdo` y `derecho`. Los atributos `izquierdo` y `derecho` no guardan el nodo como tal, sino una referencia (la dirección en memoria) al objeto hijo. Si no hay hijo, la referencia es `null`.

El objeto `ArbolBinario` solo guarda la referencia a la raíz (D), y desde ahí se llega a todos los nodos siguiendo las referencias. Por ejemplo, `O` tiene `izquierdo` apuntando a `Z` y `derecho` en `null`.

El dibujo del modelo en memoria está en la última página de `diagramas.pdf`.

## Diferencia entre null y nodo hoja

- Una referencia `null` significa que ahí no hay ningún objeto. Por ejemplo, el hijo derecho de O.
- Un nodo hoja sí existe (tiene dato), pero sus dos hijos son `null`. Por ejemplo, Z.

Si se llama `getDato()` sobre una referencia `null`, sale `NullPointerException`. Por eso antes de usar un hijo se valida con `if (nodo.getIzquierdo() != null)`.

## Árbol de expresión

En `ArbolExpresion.java` representé `(7 + 3) * (6 - 2)`. Los operadores son nodos internos y los números son hojas. El resultado es 40.

## Archivos

- `NodoArbol.java`: nodo genérico con referencias izquierda y derecha.
- `ArbolBinario.java`: `imprimirRaizYHijos()`, imprimir el árbol, contar nodos y hojas, altura.
- `Clasificador.java`: revisa si un árbol es lleno, completo, perfecto o degenerado.
- `Main.java`: construcción de mi árbol, los cuatro árboles de clasificación y las pruebas con null.
- `ArbolExpresion.java`: árbol de expresión.
- `diagramas.pdf`: diagramas y capturas de ejecución.
- `diagramas.drawio`: diagramas editables en draw.io.

## Ejecutar

```
javac *.java
java Main
java ArbolExpresion
```
