package consolautils.cl.bemtorres.utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;

public class EntradaConsola {
    private final BufferedReader lector;

    public EntradaConsola() {
        this(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    public EntradaConsola(Reader lector) {
        Objects.requireNonNull(lector, "El lector no puede ser null.");
        this.lector = lector instanceof BufferedReader
                ? (BufferedReader) lector
                : new BufferedReader(lector);
    }

    public String leerTexto(String mensaje) {
        while (true) {
            preguntar(mensaje);
            String respuesta = leerLinea();
            if (!respuesta.isBlank()) {
                return respuesta.trim();
            }
            Consola.error("El texto no puede estar vacío.");
        }
    }

    public int leerEntero(String mensaje) {
        while (true) {
            preguntar(mensaje);
            String respuesta = leerLinea().trim();
            try {
                return Integer.parseInt(respuesta);
            } catch (NumberFormatException excepcion) {
                Consola.error("Ingresa un número entero válido.");
            }
        }
    }

    public double leerDecimal(String mensaje) {
        while (true) {
            preguntar(mensaje);
            String respuesta = leerLinea().trim();
            try {
                double valor = Double.parseDouble(respuesta);
                if (Double.isFinite(valor)) {
                    return valor;
                }
            } catch (NumberFormatException excepcion) {
                // La entrada se informa abajo y se vuelve a solicitar.
            }
            Consola.error("Ingresa un número decimal válido.");
        }
    }

    public String leerRut(String mensaje) {
        while (true) {
            preguntar(mensaje);
            String respuesta = leerLinea().trim();
            if (ValidadorRut.esValido(respuesta)) {
                return respuesta;
            }
            Consola.error("Ingresa un RUT chileno válido.");
        }
    }

    public boolean confirmar(String mensaje) {
        while (true) {
            preguntar(mensaje + " (s/n): ");
            String respuesta = leerLinea().trim().toLowerCase(Locale.ROOT);
            if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) {
                return true;
            }
            if (respuesta.equals("n") || respuesta.equals("no")) {
                return false;
            }
            Consola.error("Responde s o n.");
        }
    }

    private void preguntar(String mensaje) {
        System.out.print(mensaje);
    }

    private String leerLinea() {
        try {
            String respuesta = lector.readLine();
            if (respuesta == null) {
                throw new IllegalStateException("La entrada de consola se cerró antes de recibir una respuesta.");
            }
            return respuesta;
        } catch (IOException excepcion) {
            throw new UncheckedIOException("No se pudo leer la entrada de consola.", excepcion);
        }
    }
}
