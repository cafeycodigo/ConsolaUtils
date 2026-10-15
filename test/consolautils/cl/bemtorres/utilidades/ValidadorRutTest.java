package consolautils.cl.bemtorres.utilidades;

import java.io.StringReader;

public class ValidadorRutTest {
    private static final String NUEVA_LINEA = System.lineSeparator();

    public static void main(String[] args) {
        verificaFormatosValidos();
        verificaFormatosInvalidos();
        verificaLecturaDesdeConsola();
        System.out.println("ValidadorRutTest pasó.");
    }

    private static void verificaFormatosValidos() {
        TestSupport.assertEquals(true, ValidadorRut.esValido("12.345.678-5"));
        TestSupport.assertEquals(true, ValidadorRut.esValido("12345678-5"));
        TestSupport.assertEquals(true, ValidadorRut.esValido("123456785"));
        TestSupport.assertEquals(true, ValidadorRut.esValido("23-k"));
        TestSupport.assertEquals(true, ValidadorRut.esValido("23-K"));
    }

    private static void verificaFormatosInvalidos() {
        TestSupport.assertEquals(false, ValidadorRut.esValido("11.111.111-2"));
        TestSupport.assertEquals(false, ValidadorRut.esValido("12.345.678-6"));
        TestSupport.assertEquals(false, ValidadorRut.esValido("0-0"));
        TestSupport.assertEquals(false, ValidadorRut.esValido("12.34.567-8"));
        TestSupport.assertEquals(false, ValidadorRut.esValido("12.345.678-A"));
        TestSupport.assertEquals(false, ValidadorRut.esValido(null));
    }

    private static void verificaLecturaDesdeConsola() {
        EntradaConsola entrada = new EntradaConsola(
                new StringReader("12.345.678-6\n12.345.678-5\n"));
        String[] rut = new String[1];
        String salida = TestSupport.capturarSalida(
                () -> rut[0] = entrada.leerRut("RUT: "));
        TestSupport.assertEquals("12.345.678-5", rut[0]);
        TestSupport.assertEquals("RUT: Error: Ingresa un RUT chileno válido." + NUEVA_LINEA
                + "RUT: ", salida);
    }
}
