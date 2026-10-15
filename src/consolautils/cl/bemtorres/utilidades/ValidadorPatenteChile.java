package consolautils.cl.bemtorres.utilidades;

import java.util.Locale;
import java.util.regex.Pattern;

public final class ValidadorPatenteChile {
    private static final String CONSONANTE = "[B-DF-HJ-NP-TV-Z]";
    private static final Pattern AUTO_FORMATO_ANTIGUO =
            Pattern.compile("^" + CONSONANTE + "{4}-?[0-9]{2}$");
    private static final Pattern AUTO_FORMATO_NUEVO =
            Pattern.compile("^" + CONSONANTE + "{5}-?[0-9]$");
    private static final Pattern MOTO_FORMATO_NUEVO =
            Pattern.compile("^" + CONSONANTE + "{4}-?[0-9]$");

    private ValidadorPatenteChile() {
    }

    public static boolean esValida(String patente, TipoVehiculo tipoVehiculo) {
        if (patente == null || tipoVehiculo == null) {
            return false;
        }

        String normalizada = patente.trim().toUpperCase(Locale.ROOT);
        if (normalizada.contains("CH")) {
            return false;
        }

        switch (tipoVehiculo) {
            case AUTOMOVIL:
                return AUTO_FORMATO_ANTIGUO.matcher(normalizada).matches()
                        || AUTO_FORMATO_NUEVO.matcher(normalizada).matches();
            case MOTOCICLETA:
                return MOTO_FORMATO_NUEVO.matcher(normalizada).matches();
            default:
                throw new AssertionError("Tipo de vehículo no contemplado: " + tipoVehiculo);
        }
    }

    public enum TipoVehiculo {
        AUTOMOVIL,
        MOTOCICLETA
    }
}
