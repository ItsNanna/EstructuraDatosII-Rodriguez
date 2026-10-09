# Guía 1. De lo lineal a lo jerárquico

**Asignatura:** Estructura de Datos II – Ingeniería de Software, IV semestre

## Clasificación de los cuatro escenarios

| Escenario | Clasificación | Justificación |
|---|---|---|
| Sistema de archivos | Jerárquico | Una carpeta contiene archivos y otras carpetas, y cada elemento tiene un solo padre. |
| Organigrama empresarial | Jerárquico | La raíz es la empresa y de ella dependen áreas con subáreas. |
| Menú de aplicación | Jerárquico | Un menú tiene submenús y opciones con relación padre-hijo. |
| Árbol genealógico | Mixto | Parece un árbol, pero cada persona tiene dos padres, por lo que no es un árbol estricto. |

## Escenario modelado
Organigrama empresarial con 3 niveles y 10 nodos. El diagrama está en
`arbol-organigrama.drawio` y `arbol-organigrama.pdf`.

## Ejecución
    javac NodoGeneral.java Main.java
    java Main

## Ejercicios

**Dos ventajas de usar un árbol en vez de una lista:**
1. La relación padre-hijo queda explícita: cada nodo guarda sus hijos, sin recorrer toda la lista para saber quién depende de quién.
2. Agregar o buscar dentro de una rama no obliga a reorganizar toda la estructura.

**Si un nodo pudiera tener más de un padre:** dejaría de ser un árbol y
pasaría a ser un grafo, porque habría varios caminos hacia el mismo nodo y
posibles ciclos. Los recorridos tendrían que controlar nodos repetidos.

## Reflexión

Al analizar los cuatro escenarios entendí que las estructuras lineales funcionan bien cuando los datos van uno detrás de otro, como una fila o una pila, pero se quedan cortas cuando un elemento se relaciona con varios descendientes. En el organigrama de la empresa, por ejemplo, un área tiene varias subáreas, y representar eso en una lista obligaría a recorrerla completa para saber quién depende de quién. Con un árbol, cada nodo guarda directamente sus hijos, así que la relación padre-hijo es explícita y es más fácil recorrer, buscar y agregar nuevas áreas sin reorganizar todo. Otra ventaja es que la estructura refleja la realidad del problema, lo que hace el código más claro. Además, si un nodo pudiera tener más de un padre, el modelo dejaría de ser un árbol y pasaría a ser un grafo, porque aparecerían caminos múltiples y posibles ciclos, y los recorridos tendrían que controlar nodos repetidos. Por eso en el árbol genealógico clasifiqué el caso como mixto. Implementar NodoGeneral con una lista de hijos me mostró que con pocas líneas se puede modelar una jerarquía completa.