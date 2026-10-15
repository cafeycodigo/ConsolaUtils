/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package consolautils;

/**
 *
 * @author benja
 */
public class ConsolaUtils {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Ejemplos de formato y mensajes:
        // consolautils.cl.bemtorres.utilidades.Consola.titulo("Mi aplicación");
        // consolautils.cl.bemtorres.utilidades.Consola.subtitulo("Opciones");
        // consolautils.cl.bemtorres.utilidades.Consola.bloque("Texto largo que se ajusta al ancho disponible.");
        // consolautils.cl.bemtorres.utilidades.Consola.exito("Proceso completado");
        // consolautils.cl.bemtorres.utilidades.Consola.advertencia("Revisa los datos");
        // consolautils.cl.bemtorres.utilidades.Consola.error("Ocurrió un problema");

        // Ejemplos de entrada validada:
        // consolautils.cl.bemtorres.utilidades.EntradaConsola entrada =
        //         new consolautils.cl.bemtorres.utilidades.EntradaConsola();
        // String nombre = entrada.leerTexto("Nombre: ");
        // int edad = entrada.leerEntero("Edad: ");
        // double precio = entrada.leerDecimal("Precio: ");
        // boolean continuar = entrada.confirmar("¿Continuar?");

        // Validar y solicitar un RUT chileno:
        // boolean rutValido = consolautils.cl.bemtorres.utilidades.ValidadorRut
        //         .esValido("12.345.678-5");
        // String rut = entrada.leerRut("RUT: ");

        // Validar patentes chilenas según el tipo de vehículo:
        // boolean patenteAutoAntigua =
        //         consolautils.cl.bemtorres.utilidades.ValidadorPatenteChile.esValida(
        //                 "BCDF-12",
        //                 consolautils.cl.bemtorres.utilidades.ValidadorPatenteChile.TipoVehiculo.AUTOMOVIL);
        // boolean patenteAutoNueva =
        //         consolautils.cl.bemtorres.utilidades.ValidadorPatenteChile.esValida(
        //                 "BCDFG-1",
        //                 consolautils.cl.bemtorres.utilidades.ValidadorPatenteChile.TipoVehiculo.AUTOMOVIL);
        // boolean patenteMoto =
        //         consolautils.cl.bemtorres.utilidades.ValidadorPatenteChile.esValida(
        //                 "BCDF-1",
        //                 consolautils.cl.bemtorres.utilidades.ValidadorPatenteChile.TipoVehiculo.MOTOCICLETA);

        // Ejemplo de DAO en memoria:
        // class Producto implements consolautils.cl.bemtorres.utilidades.Identificable<Long> {
        //     private final Long id;
        //     private final String nombre;
        //
        //     Producto(Long id, String nombre) {
        //         this.id = id;
        //         this.nombre = nombre;
        //     }
        //
        //     @Override
        //     public Long getId() {
        //         return id;
        //     }
        // }
        //
        // var productos = new consolautils.cl.bemtorres.utilidades.DaoEnMemoria<Producto, Long>();
        // productos.guardar(new Producto(1L, "Teclado"));
        // productos.buscarPorId(1L).ifPresent(producto ->
        //         System.out.println(producto.nombre));
        // productos.actualizar(new Producto(1L, "Teclado mecánico"));
        // productos.listar().forEach(producto -> System.out.println(producto.nombre));
        // productos.eliminar(1L);

        // Si el código es la clave única, getId() puede devolver un String:
        // class ProductoPorCodigo implements
        //         consolautils.cl.bemtorres.utilidades.Identificable<String> {
        //     private final String codigo;
        //     private final String nombre;
        //
        //     ProductoPorCodigo(String codigo, String nombre) {
        //         this.codigo = codigo;
        //         this.nombre = nombre;
        //     }
        //
        //     @Override
        //     public String getId() {
        //         return codigo;
        //     }
        // }
        //
        // var productosPorCodigo =
        //         new consolautils.cl.bemtorres.utilidades.DaoEnMemoria<ProductoPorCodigo, String>();
        // productosPorCodigo.guardar(new ProductoPorCodigo("TEC-001", "Teclado"));
        // productosPorCodigo.buscarPorId("TEC-001").ifPresent(
        //         producto -> System.out.println(producto.nombre));
        // productosPorCodigo.actualizar(
        //         new ProductoPorCodigo("TEC-001", "Teclado mecánico"));
        // productosPorCodigo.eliminar("TEC-001");
    }
    
}
