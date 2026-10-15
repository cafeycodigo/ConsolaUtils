package consolautils.cl.bemtorres.utilidades;

import java.io.StringReader;

public class EntradaConsolaTest {
    private static final String NUEVA_LINEA = System.lineSeparator();

    public static void main(String[] args) {
        verificaTextoYNumeros();
        verificaConfirmacion();
        System.out.println("EntradaConsolaTest pasó.");
    }

    private static void verificaTextoYNumeros() {
        EntradaConsola entradaTexto = new EntradaConsola(new StringReader("\n  Ana  \n"));
        String[] texto = new String[1];
        String salidaTexto = TestSupport.capturarSalida(
                () -> texto[0] = entradaTexto.leerTexto("Nombre: "));
        TestSupport.assertEquals("Ana", texto[0]);
        TestSupport.assertEquals("Nombre: Error: El texto no puede estar vacío." + NUEVA_LINEA
                + "Nombre: ", salidaTexto);

        EntradaConsola entradaNumerica = new EntradaConsola(
                new StringReader("abc\n42\nInfinity\n3.5\n"));
        int[] entero = new int[1];
        double[] decimal = new double[1];
        String salidaNumerica = TestSupport.capturarSalida(() -> {
            entero[0] = entradaNumerica.leerEntero("Edad: ");
            decimal[0] = entradaNumerica.leerDecimal("Precio: ");
        });
        TestSupport.assertEquals(42, entero[0]);
        TestSupport.assertEquals(3.5, decimal[0]);
        TestSupport.assertEquals("Edad: Error: Ingresa un número entero válido." + NUEVA_LINEA
                + "Edad: Precio: Error: Ingresa un número decimal válido." + NUEVA_LINEA
                + "Precio: ", salidaNumerica);
    }

    private static void verificaConfirmacion() {
        EntradaConsola entrada = new EntradaConsola(new StringReader("quizás\nSÍ\nno\n"));
        boolean[] respuestas = new boolean[2];
        String salida = TestSupport.capturarSalida(() -> {
            respuestas[0] = entrada.confirmar("¿Continuar?");
            respuestas[1] = entrada.confirmar("¿Repetir?");
        });
        TestSupport.assertEquals(true, respuestas[0]);
        TestSupport.assertEquals(false, respuestas[1]);
        TestSupport.assertEquals("¿Continuar? (s/n): Error: Responde s o n." + NUEVA_LINEA
                + "¿Continuar? (s/n): ¿Repetir? (s/n): ", salida);
    }
}
