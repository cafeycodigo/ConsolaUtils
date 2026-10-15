package consolautils.cl.bemtorres.utilidades;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

final class TestSupport {
    private TestSupport() {
    }

    static String capturarSalida(Runnable accion) {
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

    static void assertEquals(Object esperado, Object actual) {
        if (esperado == null ? actual != null : !esperado.equals(actual)) {
            throw new AssertionError("Esperado: <" + esperado + ">, obtenido: <" + actual + ">");
        }
    }

    static void assertThrows(Class<? extends Throwable> tipo, Runnable accion) {
        try {
            accion.run();
        } catch (Throwable excepcion) {
            if (tipo.isInstance(excepcion)) {
                return;
            }
            throw new AssertionError("Se esperaba " + tipo.getSimpleName()
                    + " pero se obtuvo " + excepcion.getClass().getSimpleName(), excepcion);
        }
        throw new AssertionError("Se esperaba " + tipo.getSimpleName());
    }
}
