package negocio;

import java.util.Objects;
import modelo.Nodo;

/**
 * Arbol binario generico con insercion explicita a izquierda o derecha.
 * No es un arbol binario de busqueda: los nombres siguen la jerarquia dada.
 *
 * @param <T> tipo de dato almacenado
 */
public class ArbolBinario<T> {
    private Nodo<T> raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    /** Crea la raiz si el arbol todavia esta vacio. */
    public Nodo<T> crearRaiz(T dato) {
        if (!esVacio()) {
            throw new IllegalStateException("El arbol ya tiene una raiz.");
        }
        raiz = new Nodo<>(dato);
        return raiz;
    }

    /** Inserta un hijo izquierdo si la posicion esta libre. */
    public Nodo<T> agregarIzquierdo(Nodo<T> padre, T dato) {
        validarPadre(padre);
        if (padre.getIzquierdo() != null) {
            throw new IllegalStateException("El hijo izquierdo de "
                    + padre.getDato() + " ya esta ocupado.");
        }
        Nodo<T> hijo = new Nodo<>(dato);
        padre.setIzquierdo(hijo);
        return hijo;
    }

    /** Inserta un hijo derecho si la posicion esta libre. */
    public Nodo<T> agregarDerecho(Nodo<T> padre, T dato) {
        validarPadre(padre);
        if (padre.getDerecho() != null) {
            throw new IllegalStateException("El hijo derecho de "
                    + padre.getDato() + " ya esta ocupado.");
        }
        Nodo<T> hijo = new Nodo<>(dato);
        padre.setDerecho(hijo);
        return hijo;
    }

    private void validarPadre(Nodo<T> padre) {
        if (padre == null) {
            throw new IllegalArgumentException("El nodo padre no puede ser nulo.");
        }
        if (!perteneceAlArbol(raiz, padre)) {
            throw new IllegalArgumentException("El nodo padre no pertenece a este arbol.");
        }
    }

    private boolean perteneceAlArbol(Nodo<T> actual, Nodo<T> buscado) {
        if (actual == null) {
            return false;
        }
        if (actual == buscado) {
            return true;
        }
        return perteneceAlArbol(actual.getIzquierdo(), buscado)
                || perteneceAlArbol(actual.getDerecho(), buscado);
    }

    public boolean esVacio() {
        return raiz == null;
    }

    /** Busca un nodo por su dato, recorriendo todo el arbol. */
    public Nodo<T> buscar(T dato) {
        return buscarRecursivo(raiz, dato);
    }

    private Nodo<T> buscarRecursivo(Nodo<T> actual, T dato) {
        if (actual == null) {
            return null;
        }
        if (Objects.equals(actual.getDato(), dato)) {
            return actual;
        }
        Nodo<T> encontrado = buscarRecursivo(actual.getIzquierdo(), dato);
        if (encontrado != null) {
            return encontrado;
        }
        return buscarRecursivo(actual.getDerecho(), dato);
    }

    public int contarNodos() {
        return contarNodosRecursivo(raiz);
    }

    private int contarNodosRecursivo(Nodo<T> actual) {
        if (actual == null) {
            return 0;
        }
        return 1 + contarNodosRecursivo(actual.getIzquierdo())
                + contarNodosRecursivo(actual.getDerecho());
    }

    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo<T> actual) {
        if (actual == null) {
            return 0;
        }
        if (actual.esHoja()) {
            return 1;
        }
        return contarHojasRecursivo(actual.getIzquierdo())
                + contarHojasRecursivo(actual.getDerecho());
    }

    /** Altura en aristas. Arbol vacio = -1; solo raiz = 0. */
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo<T> actual) {
        if (actual == null) {
            return -1;
        }
        int alturaIzquierda = alturaRecursiva(actual.getIzquierdo());
        int alturaDerecha = alturaRecursiva(actual.getDerecho());
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    /** Muestra la estructura en consola con sus ramas izquierda y derecha. */
    public void mostrarArbol() {
        if (esVacio()) {
            System.out.println("El arbol esta vacio.");
            return;
        }
        System.out.println(raiz.getDato());
        if (raiz.getIzquierdo() != null) {
            mostrarRama(raiz.getIzquierdo(), "", raiz.getDerecho() == null);
        }
        if (raiz.getDerecho() != null) {
            mostrarRama(raiz.getDerecho(), "", true);
        }
    }

    private void mostrarRama(Nodo<T> actual, String prefijo, boolean ultimo) {
        System.out.println(prefijo + (ultimo ? "└── " : "├── ") + actual.getDato());
        String nuevoPrefijo = prefijo + (ultimo ? "    " : "│   ");

        if (actual.getIzquierdo() != null) {
            mostrarRama(actual.getIzquierdo(), nuevoPrefijo,
                    actual.getDerecho() == null);
        }
        if (actual.getDerecho() != null) {
            mostrarRama(actual.getDerecho(), nuevoPrefijo, true);
        }
    }
}
