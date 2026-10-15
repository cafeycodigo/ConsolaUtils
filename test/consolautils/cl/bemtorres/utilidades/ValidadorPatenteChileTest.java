package consolautils.cl.bemtorres.utilidades;

import static consolautils.cl.bemtorres.utilidades.ValidadorPatenteChile.TipoVehiculo.AUTOMOVIL;
import static consolautils.cl.bemtorres.utilidades.ValidadorPatenteChile.TipoVehiculo.MOTOCICLETA;

public class ValidadorPatenteChileTest {
    public static void main(String[] args) {
        verificaFormatosDeAutomovil();
        verificaFormatoDeMotocicleta();
        verificaCaracteresProhibidos();
        verificaFormatosInvalidos();
        System.out.println("ValidadorPatenteChileTest pasó.");
    }

    private static void verificaFormatosDeAutomovil() {
        TestSupport.assertEquals(true, ValidadorPatenteChile.esValida("BCDF-12", AUTOMOVIL));
        TestSupport.assertEquals(true, ValidadorPatenteChile.esValida("bcdf12", AUTOMOVIL));
        TestSupport.assertEquals(true, ValidadorPatenteChile.esValida("BCDFG-1", AUTOMOVIL));
    }

    private static void verificaFormatoDeMotocicleta() {
        TestSupport.assertEquals(true, ValidadorPatenteChile.esValida("BCDF-1", MOTOCICLETA));
        TestSupport.assertEquals(false, ValidadorPatenteChile.esValida("BCDF-1", AUTOMOVIL));
    }

    private static void verificaCaracteresProhibidos() {
        TestSupport.assertEquals(false, ValidadorPatenteChile.esValida("ABDF-12", AUTOMOVIL));
        TestSupport.assertEquals(false, ValidadorPatenteChile.esValida("CHDF-12", AUTOMOVIL));
        TestSupport.assertEquals(false, ValidadorPatenteChile.esValida("ÑCDF-12", AUTOMOVIL));
    }

    private static void verificaFormatosInvalidos() {
        TestSupport.assertEquals(false, ValidadorPatenteChile.esValida("BCD-12", AUTOMOVIL));
        TestSupport.assertEquals(false, ValidadorPatenteChile.esValida("BCDF-123", AUTOMOVIL));
        TestSupport.assertEquals(false, ValidadorPatenteChile.esValida("BCDF-12", MOTOCICLETA));
        TestSupport.assertEquals(false, ValidadorPatenteChile.esValida("BCDFG-12", AUTOMOVIL));
        TestSupport.assertEquals(false, ValidadorPatenteChile.esValida(null, AUTOMOVIL));
        TestSupport.assertEquals(false,
                ValidadorPatenteChile.esValida("BCDF-12", null));
    }
}
