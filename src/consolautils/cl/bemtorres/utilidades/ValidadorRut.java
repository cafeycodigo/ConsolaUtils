package consolautils.cl.bemtorres.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidadorRut {
    private static final Pattern FORMATO_RUT = Pattern.compile(
            "^([0-9]{1,8}|[0-9]{1,2}(?:\\.[0-9]{3}){1,2})-?([0-9Kk])$");

    private ValidadorRut() {
    }

    public static boolean esValido(String rut) {
        if (rut == null) {
            return false;
        }

        Matcher coincidencia = FORMATO_RUT.matcher(rut.trim());
        if (!coincidencia.matches()) {
            return false;
        }

        String cuerpo = coincidencia.group(1).replace(".", "");
        if (cuerpo.chars().allMatch(digito -> digito == '0')) {
            return false;
        }

        return calcularDigitoVerificador(cuerpo).equals(
                coincidencia.group(2).toUpperCase());
    }

    private static String calcularDigitoVerificador(String cuerpo) {
        int suma = 0;
        int multiplicador = 2;
        for (int indice = cuerpo.length() - 1; indice >= 0; indice--) {
            suma += Character.digit(cuerpo.charAt(indice), 10) * multiplicador;
            multiplicador = multiplicador == 7 ? 2 : multiplicador + 1;
        }

        int resultado = 11 - suma % 11;
        if (resultado == 11) {
            return "0";
        }
        if (resultado == 10) {
            return "K";
        }
        return Integer.toString(resultado);
    }
}
