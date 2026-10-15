package consolautils.cl.bemtorres.utilidades;

import java.util.List;

public class DaoEnMemoriaTest {
    public static void main(String[] args) {
        verificaCrudYBusquedaPorId();
        verificaClaveDeTipoCodigo();
        verificaRestricciones();
        System.out.println("DaoEnMemoriaTest pasó.");
    }

    private static void verificaCrudYBusquedaPorId() {
        DaoEnMemoria<ProductoPrueba, Long> dao = new DaoEnMemoria<>();
        dao.guardar(new ProductoPrueba(1L, "Teclado"));
        TestSupport.assertEquals("Teclado", dao.buscarPorId(1L).orElseThrow().nombre);
        TestSupport.assertEquals(1, dao.listar().size());

        dao.actualizar(new ProductoPrueba(1L, "Teclado mecánico"));
        TestSupport.assertEquals("Teclado mecánico",
                dao.buscarPorId(1L).orElseThrow().nombre);
        TestSupport.assertEquals(true, dao.eliminar(1L));
        TestSupport.assertEquals(false, dao.eliminar(1L));
        TestSupport.assertEquals(true, dao.buscarPorId(1L).isEmpty());
        TestSupport.assertEquals(0, dao.listar().size());
    }

    private static void verificaClaveDeTipoCodigo() {
        DaoEnMemoria<ProductoPorCodigo, String> dao = new DaoEnMemoria<>();
        dao.guardar(new ProductoPorCodigo("TEC-001", "Teclado"));
        TestSupport.assertEquals("Teclado", dao.buscarPorId("TEC-001").orElseThrow().nombre);

        dao.actualizar(new ProductoPorCodigo("TEC-001", "Teclado mecánico"));
        TestSupport.assertEquals("Teclado mecánico",
                dao.buscarPorId("TEC-001").orElseThrow().nombre);
        TestSupport.assertEquals(true, dao.eliminar("TEC-001"));
    }

    private static void verificaRestricciones() {
        DaoEnMemoria<ProductoPrueba, Long> dao = new DaoEnMemoria<>();
        dao.guardar(new ProductoPrueba(1L, "Teclado"));
        TestSupport.assertThrows(IllegalArgumentException.class,
                () -> dao.guardar(new ProductoPrueba(1L, "Duplicado")));
        TestSupport.assertThrows(IllegalArgumentException.class,
                () -> dao.actualizar(new ProductoPrueba(2L, "No existe")));
        TestSupport.assertThrows(NullPointerException.class, () -> dao.buscarPorId(null));

        List<ProductoPrueba> listado = dao.listar();
        TestSupport.assertThrows(UnsupportedOperationException.class,
                () -> listado.add(new ProductoPrueba(2L, "Ratón")));
    }

    private static final class ProductoPrueba implements Identificable<Long> {
        private final Long id;
        private final String nombre;

        private ProductoPrueba(Long id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        @Override
        public Long getId() {
            return id;
        }
    }

    private static final class ProductoPorCodigo implements Identificable<String> {
        private final String codigo;
        private final String nombre;

        private ProductoPorCodigo(String codigo, String nombre) {
            this.codigo = codigo;
            this.nombre = nombre;
        }

        @Override
        public String getId() {
            return codigo;
        }
    }
}
