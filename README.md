# ConsolaUtils

ConsolaUtils es una pequeña biblioteca Java con utilidades para imprimir
mensajes y formatos en la consola, y solicitar entradas validadas. El proyecto
también incluye una clase principal vacía, que puede utilizarse como punto de
entrada para una futura aplicación.

## Requisitos

- JDK 18 o posterior.
- Apache Ant para compilar desde la línea de comandos, o Apache NetBeans.

## Compilar

Desde la raíz del proyecto, ejecuta:

```powershell
ant jar
```

El archivo generado queda en `dist/ConsolaUtils.jar`. También puedes abrir el
proyecto en NetBeans y utilizar las acciones de compilación del IDE.

## Uso

La clase de utilidades es
`consolautils.cl.bemtorres.utilidades.Consola`. Sus métodos son estáticos:

```java
import consolautils.cl.bemtorres.utilidades.Consola;

Consola.linea();                  // Imprime una línea de 50 asteriscos
Consola.linea("Sección");         // Imprime el texto entre dos líneas
Consola.titulo("Resumen");        // Imprime un título enmarcado
Consola.subtitulo("Detalles");    // Imprime un subtítulo con subrayado
Consola.bloque("Texto largo");    // Enmarca y ajusta el texto al ancho
Consola.imprimir("Hola");         // Imprime un mensaje
Consola.salto();                 // Imprime una línea en blanco
Consola.autor();                 // Imprime "Creado por Bemtorres"
Consola.exito("Guardado");        // Imprime un mensaje de éxito
Consola.advertencia("Revisar");   // Imprime una advertencia
Consola.error("Falló");           // Imprime un mensaje de error
```

`linea(String)` recorta los espacios al inicio y al final del texto. Si recibe
`null`, una cadena vacía o una cadena compuesta solo por espacios, imprime
únicamente las dos líneas de borde.

Los mensajes de estado se imprimen sin colores por defecto. Para activar los
códigos ANSI (por ejemplo, en una terminal compatible), configura los colores
antes de imprimir y desactívalos si el entorno no los admite:

```java
Consola.configurarColores(true);
Consola.exito("Proceso completado");
Consola.configurarColores(false);
```

### Leer y validar entradas

`EntradaConsola` solicita valores y vuelve a preguntar cuando la respuesta no
es válida. Conserva una instancia para leer varias respuestas de la misma
entrada:

```java
import consolautils.cl.bemtorres.utilidades.EntradaConsola;

EntradaConsola entrada = new EntradaConsola();
String nombre = entrada.leerTexto("Nombre: ");
int edad = entrada.leerEntero("Edad: ");
double precio = entrada.leerDecimal("Precio: ");
boolean continuar = entrada.confirmar("¿Continuar?");
```

El texto no puede quedar vacío; los números deben ser válidos y finitos; las
confirmaciones aceptan `s`, `si`, `sí`, `n` y `no`, sin distinguir mayúsculas.
Si la entrada se cierra antes de recibir una respuesta, se informa con una
excepción en lugar de repetir indefinidamente.

### Validar RUT chileno

`ValidadorRut.esValido(String)` valida el formato y el dígito verificador; acepta
RUT con o sin puntos y guion, y acepta `k` en mayúscula o minúscula:

```java
import consolautils.cl.bemtorres.utilidades.ValidadorRut;

boolean valido = ValidadorRut.esValido("12.345.678-5");
```

También puedes pedirlo desde la consola. `leerRut` repite la pregunta hasta
recibir un RUT válido y devuelve el texto tal como lo escribió el usuario
(quitando espacios exteriores):

```java
String rut = entrada.leerRut("RUT: ");
```

### Validar patente chilena

`ValidadorPatenteChile.esValida` verifica sintaxis según el tipo de vehículo:

```java
import consolautils.cl.bemtorres.utilidades.ValidadorPatenteChile;

boolean autoFormatoAnterior = ValidadorPatenteChile.esValida(
        "BCDF-12", ValidadorPatenteChile.TipoVehiculo.AUTOMOVIL);
boolean autoFormatoNuevo = ValidadorPatenteChile.esValida(
        "BCDFG-1", ValidadorPatenteChile.TipoVehiculo.AUTOMOVIL);
boolean motoFormatoNuevo = ValidadorPatenteChile.esValida(
        "BCDF-1", ValidadorPatenteChile.TipoVehiculo.MOTOCICLETA);
```

Se aceptan letras mayúsculas o minúsculas, con o sin guion antes de los
números. Se rechazan las vocales, `Ñ` y el dígrafo `CH`. La validación de
automóviles acepta cuatro letras y dos números, o cinco letras y un número;
la de motocicletas acepta cuatro letras y un número. La forma de cuatro letras
y un número requiere indicar el tipo, ya que el patrón por sí solo no permite
distinguir esas categorías. Esta validación solo revisa formato: no consulta
el Registro Civil ni confirma que la placa haya sido asignada.

### DAO genérico en memoria

`DaoEnMemoria<T, ID>` guarda objetos en un `ArrayList` y ofrece las operaciones
de crear, buscar por ID, actualizar, eliminar y listar. No utiliza base de
datos ni conserva los datos al cerrar el programa. El tipo de entidad debe
implementar `Identificable<ID>` y devolver su identificador:

```java
import consolautils.cl.bemtorres.utilidades.DaoEnMemoria;
import consolautils.cl.bemtorres.utilidades.Identificable;

class Producto implements Identificable<Long> {
    private final Long id;
    private final String nombre;

    Producto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public Long getId() {
        return id;
    }
}

DaoEnMemoria<Producto, Long> productos = new DaoEnMemoria<>();
productos.guardar(new Producto(1L, "Teclado"));
productos.buscarPorId(1L).ifPresent(producto -> System.out.println(producto.getId()));
productos.listar();
productos.eliminar(1L);
```

No se permiten IDs nulos ni IDs duplicados. `buscarPorId` devuelve un
`Optional`; `actualizar` reemplaza el elemento con el mismo ID y falla si no
existe; `eliminar` devuelve `true` solo cuando encuentra y elimina un
elemento. `listar` devuelve una copia inmutable de la lista.

El nombre `getId()` es parte de la interfaz, pero el valor que devuelve no
tiene que ser un campo llamado `id` ni ser numérico. Puedes usar un código,
correo u otra clave única implementando `Identificable<String>` y devolviendo
ese valor desde `getId()`:

```java
class ProductoPorCodigo implements Identificable<String> {
    private final String codigo;
    private final String nombre;

    ProductoPorCodigo(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Override
    public String getId() {
        return codigo;
    }
}

DaoEnMemoria<ProductoPorCodigo, String> productosPorCodigo = new DaoEnMemoria<>();
productosPorCodigo.guardar(new ProductoPorCodigo("TEC-001", "Teclado"));
productosPorCodigo.buscarPorId("TEC-001");
productosPorCodigo.actualizar(new ProductoPorCodigo("TEC-001", "Teclado mecánico"));
productosPorCodigo.eliminar("TEC-001");
```

El DAO sigue llamando al método `getId()`, pero en este modelo ese método
devuelve el código. No es necesario modificar el DAO para cambiar el tipo de
clave.

## Pruebas unitarias

Las pruebas no requieren dependencias externas. Cada clase de prueba es
independiente y puede ejecutarse por separado desde PowerShell. Primero,
compila las clases de la biblioteca y las pruebas:

```powershell
$testDir = Join-Path $env:TEMP 'ConsolaUtils-tests'
New-Item -ItemType Directory -Force -Path $testDir | Out-Null
javac -encoding UTF-8 -d $testDir src\consolautils\cl\bemtorres\utilidades\*.java test\consolautils\cl\bemtorres\utilidades\*.java
```

Después, ejecuta cada conjunto de pruebas de forma independiente:

```powershell
java -cp $testDir consolautils.cl.bemtorres.utilidades.ConsolaTest
java -cp $testDir consolautils.cl.bemtorres.utilidades.EntradaConsolaTest
java -cp $testDir consolautils.cl.bemtorres.utilidades.ValidadorRutTest
java -cp $testDir consolautils.cl.bemtorres.utilidades.ValidadorPatenteChileTest
java -cp $testDir consolautils.cl.bemtorres.utilidades.DaoEnMemoriaTest
```

Cada clase prueba únicamente su componente: `Consola`, `EntradaConsola`,
`ValidadorRut`, `ValidadorPatenteChile` o `DaoEnMemoria`. Cada una informa su
resultado y termina con un error si alguna aserción falla.
