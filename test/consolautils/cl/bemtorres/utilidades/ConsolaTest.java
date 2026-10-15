package consolautils.cl.bemtorres.utilidades;

public class ConsolaTest {
    private static final String BORDE = "*".repeat(50);
    private static final String NUEVA_LINEA = System.lineSeparator();

    public static void main(String[] args) {
        verificaSalto();
        verificaLinea();
        verificaLineaConTexto();
        verificaLineaConTextoEnBlanco();
        verificaTituloYBloque();
        verificaMensajes();
        System.out.println("ConsolaTest pasó.");
    }

    private static void verificaSalto() {
        TestSupport.assertEquals(NUEVA_LINEA, TestSupport.capturarSalida(Consola::salto));
    }

    private static void verificaLinea() {
        TestSupport.assertEquals(BORDE + NUEVA_LINEA,
                TestSupport.capturarSalida(Consola::linea));
    }

    private static void verificaLineaConTexto() {
        String esperado = BORDE + NUEVA_LINEA
                + "* Sección" + NUEVA_LINEA
                + BORDE + NUEVA_LINEA;
        TestSupport.assertEquals(esperado,
                TestSupport.capturarSalida(() -> Consola.linea("  Sección  ")));
    }

    private static void verificaLineaConTextoEnBlanco() {
        String esperado = BORDE + NUEVA_LINEA + BORDE + NUEVA_LINEA;
        TestSupport.assertEquals(esperado,
                TestSupport.capturarSalida(() -> Consola.linea("   ")));
        TestSupport.assertEquals(esperado,
                TestSupport.capturarSalida(() -> Consola.linea(null)));
    }

    private static void verificaTituloYBloque() {
        String texto = "a".repeat(50);
        String esperadoTitulo = BORDE + NUEVA_LINEA
                + "* " + "a".repeat(46) + " *" + NUEVA_LINEA
                + "* " + " ".repeat(21) + "aaaa" + " ".repeat(21) + " *" + NUEVA_LINEA
                + BORDE + NUEVA_LINEA;
        TestSupport.assertEquals(esperadoTitulo,
                TestSupport.capturarSalida(() -> Consola.titulo(texto)));

        String esperadoBloque = BORDE + NUEVA_LINEA
                + "* " + "a".repeat(46) + " *" + NUEVA_LINEA
                + "* aaaa" + " ".repeat(42) + " *" + NUEVA_LINEA
                + BORDE + NUEVA_LINEA;
        TestSupport.assertEquals(esperadoBloque,
                TestSupport.capturarSalida(() -> Consola.bloque(texto)));
        TestSupport.assertEquals("Detalles" + NUEVA_LINEA + "--------" + NUEVA_LINEA,
                TestSupport.capturarSalida(() -> Consola.subtitulo(" Detalles ")));
    }

    private static void verificaMensajes() {
        TestSupport.assertEquals("Hola" + NUEVA_LINEA,
                TestSupport.capturarSalida(() -> Consola.imprimir("Hola")));
        TestSupport.assertEquals("Creado por Bemtorres" + NUEVA_LINEA,
                TestSupport.capturarSalida(Consola::autor));

        Consola.configurarColores(false);
        TestSupport.assertEquals("OK: Listo" + NUEVA_LINEA,
                TestSupport.capturarSalida(() -> Consola.exito("Listo")));

        Consola.configurarColores(true);
        try {
            TestSupport.assertEquals("\u001B[32mOK: Listo\u001B[0m" + NUEVA_LINEA,
                    TestSupport.capturarSalida(() -> Consola.exito("Listo")));
            TestSupport.assertEquals("\u001B[33mAdvertencia: Atención\u001B[0m" + NUEVA_LINEA,
                    TestSupport.capturarSalida(() -> Consola.advertencia("Atención")));
            TestSupport.assertEquals("\u001B[31mError: Falló\u001B[0m" + NUEVA_LINEA,
                    TestSupport.capturarSalida(() -> Consola.error("Falló")));
        } finally {
            Consola.configurarColores(false);
        }
    }
}
