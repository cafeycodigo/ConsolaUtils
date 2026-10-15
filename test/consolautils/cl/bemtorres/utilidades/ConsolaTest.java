package consolautils.cl.bemtorres.utilidades;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

public class ConsolaTest {
    private static final String BORDE = "*".repeat(50);
    private static final String NUEVA_LINEA = System.lineSeparator();

    public static void main(String[] args) {
        verificaSalto();
        verificaLinea();
        verificaLineaConTexto();
        verificaLineaConTextoEnBlanco();
        verificaImprimir();
        verificaAutor();
        System.out.println("Todas las pruebas unitarias pasaron.");
    }

    private static void verificaSalto() {
        assertEquals(NUEVA_LINEA, capturaSalida(Consola::salto));
    }

    private static void verificaLinea() {
        assertEquals(BORDE + NUEVA_LINEA, capturaSalida(Consola::linea));
    }

    private static void verificaLineaConTexto() {
        String esperado = BORDE + NUEVA_LINEA
                + "* Sección" + NUEVA_LINEA
                + BORDE + NUEVA_LINEA;
        assertEquals(esperado, capturaSalida(() -> Consola.linea("  Sección  ")));
    }

    private static void verificaLineaConTextoEnBlanco() {
        String esperado = BORDE + NUEVA_LINEA + BORDE + NUEVA_LINEA;
        assertEquals(esperado, capturaSalida(() -> Consola.linea("   ")));
        assertEquals(esperado, capturaSalida(() -> Consola.linea(null)));
    }

    private static void verificaImprimir() {
        assertEquals("Hola" + NUEVA_LINEA, capturaSalida(() -> Consola.imprimir("Hola")));
    }

    private static void verificaAutor() {
        assertEquals("Creado por Bemtorres" + NUEVA_LINEA, capturaSalida(Consola::autor));
    }

    private static String capturaSalida(Runnable accion) {
        PrintStream salidaOriginal = System.out;
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try (PrintStream capturada = new PrintStream(salida, true, Charset.defaultCharset())) {
            System.setOut(capturada);
            accion.run();
        } finally {
            System.setOut(salidaOriginal);
        }
        return salida.toString(Charset.defaultCharset());
    }

    private static void assertEquals(String esperado, String actual) {
        if (!esperado.equals(actual)) {
            throw new AssertionError("Esperado: <" + esperado + ">, obtenido: <" + actual + ">");
        }
    }
}
