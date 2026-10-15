# Informe de evidencias - Implementación de árbol binario en Java

> **BORRADOR:** Reemplaza los campos entre corchetes, agrega las capturas reales y revisa la reflexión con tus propias palabras antes de exportar a PDF y entregar.

**Asignatura:** Estructura de Datos  
**Estudiante:** [NOMBRE COMPLETO]  
**Curso/paralelo:** [CURSO Y PARALELO]  
**Fecha:** [FECHA DE ENTREGA]  
**Repositorio GitHub público:** [PEGAR ENLACE REAL]

## 1. Descripción del programa

Desarrollé en Java un sistema para representar la organización de dependencias universitarias con un árbol binario. Cada dependencia se almacena en un nodo que puede tener hasta dos hijos. Separé el proyecto en las clases `Nodo<T>`, `ArbolBinario<T>` y `Main`, organizadas en los paquetes `modelo`, `negocio` y `app`, respectivamente.

## 2. Estructura del árbol

```text
UNIVERSIDAD
├── FISEI
│   ├── SOFTWARE
│   │   ├── NIVEL1
│   │   └── NIVEL3
│   └── TI
└── DIDE
    ├── INVEST.
    └── VINCULACIÓN
```

## 3. Resultados de las pruebas

| Prueba | Resultado |
|---|---|
| Árbol vacío | 0 nodos, 0 hojas, altura -1 |
| Árbol con solo raíz | 1 nodo, 1 hoja, altura 0 |
| Árbol inicial | 9 nodos, 5 hojas, altura 3 |
| Intento de inserción en posición ocupada | Inserción rechazada y 9 nodos conservados |
| Grado de FISEI | 2 |
| Grado de SOFTWARE | 2 |
| NIVEL1 | Es hoja |
| SOFTWARE | No es hoja |

## 4. Capturas de pantalla (incorporar imágenes REALES)

1. [CAPTURA: carpetas del proyecto en VS Code].
2. [CAPTURA: menú + dibujo del árbol en ejecución].
3. [CAPTURA: ejecución de `java -cp out app.Main --pruebas`].
4. [CAPTURA: repositorio público y mínimo 3 commits significativos].

## 5. Preguntas de reflexión (respuestas sugeridas para adaptar)

**1. ¿Por qué la altura de un árbol binario depende del camino más largo desde la raíz hasta una hoja?**

La altura se define como la mayor cantidad de aristas que hay que recorrer desde la raíz hasta una hoja. Por ese motivo, aunque algunas ramas sean cortas, el árbol tiene la altura de su rama más profunda. En este ejercicio la ruta UNIVERSIDAD → FISEI → SOFTWARE → NIVEL1 tiene tres aristas, así que la altura es 3. Si el árbol está vacío, la implementación devuelve -1 y, si solo tiene la raíz, devuelve 0.

**2. ¿Qué diferencias existen entre almacenar información en una lista enlazada y en un árbol binario?**

Una lista enlazada conecta los elementos de manera secuencial: normalmente cada nodo apunta al siguiente. Un árbol binario, en cambio, organiza los elementos por niveles y cada nodo puede tener un hijo izquierdo y otro derecho. La lista resulta práctica para recorrer datos en orden, mientras que el árbol permite representar relaciones jerárquicas, como las dependencias universitarias. Es importante aclarar que un árbol binario común no garantiza búsquedas más rápidas; eso depende de cómo esté organizado.

**3. ¿Cómo podría utilizarse un árbol binario en el proyecto integrador SmartETL para organizar o buscar registros?**

En SmartETL se podría utilizar para organizar reglas de transformación, clasificaciones o registros por un criterio. Por ejemplo, un árbol binario de búsqueda podría ordenar registros según un identificador y permitir consultas eficientes cuando se mantiene equilibrado. También sería útil como árbol de decisiones para evaluar condiciones durante el proceso de transformación. Para manejar muchos datos reales sería necesario estudiar si conviene un árbol balanceado, índices de una base de datos u otra estructura.

## 6. Reflexión personal (modelo; redactar con tus palabras)

La actividad me permitió entender que un árbol binario no es solo un dibujo: cada nodo está conectado a otros objetos mediante referencias. Lo que más me ayudó fue comprobar la cantidad de nodos, hojas y altura con pruebas concretas. También entendí que la recursividad facilita recorrer las ramas sin programar manualmente cada nivel. Debo seguir practicando cómo se realizan las llamadas recursivas para poder explicar mejor el recorrido del árbol.

## 7. Entrega

- Confirmar que el programa compile y funcione en tu computador.
- Revisar que el repositorio público contenga las tres clases Java y README.
- Confirmar que existan al menos tres commits reales y significativos.
- Agregar capturas propias y pegar el URL real de GitHub.
- Exportar el documento final como PDF y cargarlo en Moodle.
