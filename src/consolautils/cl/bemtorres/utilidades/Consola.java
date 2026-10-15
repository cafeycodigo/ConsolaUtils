/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consolautils.cl.bemtorres.utilidades;

/**
 *
 * @author benja
 */
public class Consola {
    private static final int ANCHO = 50;
    private static final char BORDE = '*';

    private Consola() {
    }

    public static void salto() {
        System.out.println();
    }

    public static void linea() {
        System.out.println(String.valueOf(BORDE).repeat(ANCHO));
    }

    public static void linea(String texto) {
        linea();

        if (texto != null && !texto.isBlank()) {
            System.out.println("* " + texto.trim());
        }

        linea();
    }

    public static void imprimir(String texto) {
        System.out.println(texto);
    }
    
    public static void autor() {
        imprimir("Creado por Bemtorres");
    }
}
