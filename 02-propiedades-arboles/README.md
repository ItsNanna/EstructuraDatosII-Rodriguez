# Guía 2 – Anatomía y propiedades de un árbol

**Asignatura:** Estructura de Datos II
**Programa:** Ingeniería de Software – Ingeniería de Sistemas, Semestre IV
**Ecosistema:** Java / GitHub

## Objetivo

Identificar y calcular raíz, hojas, nodos internos, grado, nivel, profundidad, altura y caminos en un árbol general.

## Convenciones

- La raíz tiene profundidad 0 y el nivel es igual a la profundidad.
- Una hoja tiene altura 0; la altura se mide en número de aristas.
- El grado de un nodo es su número de hijos; el grado del árbol es el mayor grado entre sus nodos.

## Árbol usado

Árbol con las letras de `DANNARODRIGUEZ`: 14 nodos, 7 hojas y 4 niveles (altura 3).
Si una letra se repite, la segunda vez lleva un 2 (`N2`, `A2`, `D2`, `R2`).
Leído por niveles, de izquierda a derecha, se forma el nombre: `D | A N N | A R O D R | I G U E Z`.

```text
D
├── A
│   ├── A2
│   │   ├── I
│   │   └── G
│   └── R
│       └── U
├── N
│   ├── O
│   └── D2
│       ├── E
│       └── Z
└── N2
    └── R2
```

## Archivos

- `NodoGeneral.java`: nodo con un dato y una lista de hijos.
- `MetricasArbol.java`: métodos recursivos de altura, profundidad, grado, caminos y conteos.
- `Main.java`: construye el árbol, imprime las métricas y resuelve el reto (agregar `S` como hijo de `I`).
- `arbol_taller.drawio`: diagrama rotulado del árbol y organigrama (Draw.io).

## Compilar y ejecutar

```bash
javac NodoGeneral.java MetricasArbol.java Main.java
java Main
```

## Resultados

| Dato | Antes del reto | Después del reto |
|------|----------------|------------------|
| Altura del árbol | 3 | 4 |
| Niveles | 4 | 5 |
| Nodos | 14 | 15 |
