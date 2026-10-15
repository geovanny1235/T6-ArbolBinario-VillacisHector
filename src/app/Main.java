package app;

import java.util.Locale;
import java.util.Scanner;
import modelo.Nodo;
import negocio.ArbolBinario;

/** Programa principal: menu y comprobacion de los casos obligatorios. */
public class Main {
    public static void main(String[] args) {
        // Permite demostrar las pruebas sin agregar otra clase Java al proyecto.
        if (args.length > 0 && "--pruebas".equals(args[0])) {
            ejecutarPruebas();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        ArbolBinario<String> arbol = new ArbolBinario<>();
        String opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    if (arbol.esVacio()) {
                        arbol = construirArbolInicial();
                        System.out.println("Arbol inicial creado correctamente.");
                    } else {
                        System.out.println("El arbol ya fue creado.");
                    }
                    break;
                case "2":
                    arbol.mostrarArbol();
                    break;
                case "3":
                    System.out.println("Numero de nodos: " + arbol.contarNodos());
                    break;
                case "4":
                    System.out.println("Numero de hojas: " + arbol.contarHojas());
                    break;
                case "5":
                    System.out.println("Altura en aristas: " + arbol.altura());
                    break;
                case "6":
                    consultarNodo(arbol, scanner, false);
                    break;
                case "7":
                    consultarNodo(arbol, scanner, true);
                    break;
                case "8":
                    System.out.println("Gracias por utilizar el programa.");
                    break;
                default:
                    System.out.println("Opcion no valida. Elige un numero del 1 al 8.");
            }
        } while (!opcion.equals("8"));

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n========== ARBOL BINARIO ==========");
        System.out.println("1. Crear el arbol inicial");
        System.out.println("2. Visualizar el arbol");
        System.out.println("3. Consultar numero de nodos");
        System.out.println("4. Consultar numero de hojas");
        System.out.println("5. Consultar altura");
        System.out.println("6. Consultar grado de un nodo");
        System.out.println("7. Verificar si un nodo es hoja");
        System.out.println("8. Salir");
        System.out.print("Elige una opcion (1-8): ");
    }

    private static void consultarNodo(ArbolBinario<String> arbol,
            Scanner scanner, boolean consultarHoja) {
        if (arbol.esVacio()) {
            System.out.println("Primero debes crear el arbol (opcion 1).");
            return;
        }

        System.out.print("Escribe el nombre del nodo: ");
        String nombre = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        Nodo<String> nodo = arbol.buscar(nombre);

        if (nodo == null) {
            System.out.println("No existe un nodo llamado: " + nombre);
        } else if (consultarHoja) {
            System.out.println(nombre + (nodo.esHoja()
                    ? " SI es una hoja." : " NO es una hoja."));
        } else {
            System.out.println("Grado de " + nombre + ": " + nodo.grado());
        }
    }

    /** Construye el arbol indicado en la actividad. */
    private static ArbolBinario<String> construirArbolInicial() {
        ArbolBinario<String> arbol = new ArbolBinario<>();
        Nodo<String> universidad = arbol.crearRaiz("UNIVERSIDAD");

        Nodo<String> fisei = arbol.agregarIzquierdo(universidad, "FISEI");
        Nodo<String> dide = arbol.agregarDerecho(universidad, "DIDE");

        Nodo<String> software = arbol.agregarIzquierdo(fisei, "SOFTWARE");
        arbol.agregarDerecho(fisei, "TI");
        arbol.agregarIzquierdo(dide, "INVEST.");
        arbol.agregarDerecho(dide, "VINCULACIÓN");

        arbol.agregarIzquierdo(software, "NIVEL1");
        arbol.agregarDerecho(software, "NIVEL3");
        return arbol;
    }

    /** Ejecutar con: java -cp out app.Main --pruebas */
    private static void ejecutarPruebas() {
        System.out.println("==== CASOS DE PRUEBA OBLIGATORIOS ====");

        ArbolBinario<String> vacio = new ArbolBinario<>();
        comprobar("Arbol vacio: 0 nodos, 0 hojas y altura -1",
                vacio.esVacio() && vacio.contarNodos() == 0
                && vacio.contarHojas() == 0 && vacio.altura() == -1);

        ArbolBinario<String> simple = new ArbolBinario<>();
        Nodo<String> unico = simple.crearRaiz("UNIVERSIDAD");
        comprobar("Arbol con solo raiz: 1 nodo, 1 hoja y altura 0",
                simple.contarNodos() == 1 && simple.contarHojas() == 1
                && simple.altura() == 0 && unico.esHoja() && unico.grado() == 0);

        ArbolBinario<String> inicial = construirArbolInicial();
        comprobar("Arbol inicial: 9 nodos, 5 hojas y altura 3",
                inicial.contarNodos() == 9 && inicial.contarHojas() == 5
                && inicial.altura() == 3);

        Nodo<String> fisei = inicial.buscar("FISEI");
        Nodo<String> software = inicial.buscar("SOFTWARE");
        comprobar("Grado de FISEI = 2 y grado de SOFTWARE = 2",
                fisei != null && software != null
                && fisei.grado() == 2 && software.grado() == 2);

        boolean insercionRechazada = false;
        try {
            inicial.agregarIzquierdo(fisei, "OTRA DEPENDENCIA");
        } catch (IllegalStateException error) {
            insercionRechazada = true;
            System.out.println("Mensaje esperado: " + error.getMessage());
        }
        comprobar("Posicion ocupada: insercion rechazada, 9 nodos conservados",
                insercionRechazada && inicial.contarNodos() == 9);

        comprobar("NIVEL1 es hoja; SOFTWARE no es hoja",
                inicial.buscar("NIVEL1").esHoja() && !software.esHoja());
        System.out.println("TODAS LAS PRUEBAS PASARON.");
    }

    private static void comprobar(String descripcion, boolean condicion) {
        if (!condicion) {
            System.out.println("[ERROR] " + descripcion);
            throw new IllegalStateException("Fallo la prueba: " + descripcion);
        }
        System.out.println("[OK] " + descripcion);
    }
}
