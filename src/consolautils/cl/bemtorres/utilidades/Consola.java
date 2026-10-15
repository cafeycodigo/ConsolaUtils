/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consolautils.cl.bemtorres.utilidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author benja
 */
public class Consola {
    private static final int ANCHO = 50;
    private static final int ANCHO_CONTENIDO = ANCHO - 4;
    private static final char BORDE = '*';
    private static volatile boolean coloresHabilitados;

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
            for (String renglon : ajustar(texto.trim())) {
                System.out.println("* " + renglon);
            }
        }
        linea();
    }

    public static void titulo(String texto) {
        linea();
        if (texto != null && !texto.isBlank()) {
            for (String renglon : ajustar(texto.trim())) {
                int espaciosIzquierda = (ANCHO_CONTENIDO - renglon.length()) / 2;
                int espaciosDerecha = ANCHO_CONTENIDO - renglon.length() - espaciosIzquierda;
                System.out.println("* " + " ".repeat(espaciosIzquierda) + renglon
                        + " ".repeat(espaciosDerecha) + " *");
            }
        }
        linea();
    }

    public static void subtitulo(String texto) {
        if (texto == null || texto.isBlank()) {
            return;
        }

        String limpio = texto.trim();
        System.out.println(limpio);
        System.out.println("-".repeat(Math.min(ANCHO, limpio.length())));
    }

    public static void bloque(String texto) {
        linea();

        if (texto != null && !texto.isBlank()) {
            String[] parrafos = texto.trim().split("\\R");
            for (String parrafo : parrafos) {
                for (String renglon : ajustar(parrafo.trim())) {
                    int espaciosDerecha = ANCHO_CONTENIDO - renglon.length();
                    System.out.println("* " + renglon + " ".repeat(espaciosDerecha) + " *");
                }
            }
        }

        linea();
    }

    private static List<String> ajustar(String texto) {
        List<String> renglones = new ArrayList<>();
        if (texto.isEmpty()) {
            renglones.add("");
            return renglones;
        }

        String restante = texto;
        while (restante.length() > ANCHO_CONTENIDO) {
            int corte = restante.lastIndexOf(' ', ANCHO_CONTENIDO);
            if (corte <= 0) {
                corte = ANCHO_CONTENIDO;
            }
            renglones.add(restante.substring(0, corte));
            restante = restante.substring(corte).stripLeading();
        }
        renglones.add(restante);
        return renglones;
    }

    public static void imprimir(String texto) {
        System.out.println(texto);
    }

    public static void configurarColores(boolean habilitados) {
        coloresHabilitados = habilitados;
    }

    public static void exito(String texto) {
        mensaje("OK: " + texto, "\u001B[32m");
    }

    public static void advertencia(String texto) {
        mensaje("Advertencia: " + texto, "\u001B[33m");
    }

    public static void error(String texto) {
        mensaje("Error: " + texto, "\u001B[31m");
    }

    private static void mensaje(String texto, String color) {
        if (coloresHabilitados) {
            System.out.println(color + texto + "\u001B[0m");
        } else {
            System.out.println(texto);
        }
    }
    
    public static void autor() {
        imprimir("Creado por Bemtorres");
    }
}
