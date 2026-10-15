# ConsolaUtils

ConsolaUtils es una pequeña biblioteca Java con métodos estáticos para imprimir
separadores y mensajes en la consola. El proyecto también incluye una clase
principal vacía, que puede utilizarse como punto de entrada para una futura
aplicación.

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
Consola.imprimir("Hola");         // Imprime un mensaje
Consola.salto();                 // Imprime una línea en blanco
Consola.autor();                 // Imprime "Creado por Bemtorres"
```

`linea(String)` recorta los espacios al inicio y al final del texto. Si recibe
`null`, una cadena vacía o una cadena compuesta solo por espacios, imprime
únicamente las dos líneas de borde.

## Prueba unitaria

La prueba no requiere dependencias externas. Desde PowerShell, compila las
clases y ejecútala así:

```powershell
$testDir = Join-Path $env:TEMP 'ConsolaUtils-tests'
New-Item -ItemType Directory -Force -Path $testDir | Out-Null
javac -d $testDir src\consolautils\cl\bemtorres\utilidades\Consola.java test\consolautils\cl\bemtorres\utilidades\ConsolaTest.java
java -cp $testDir consolautils.cl.bemtorres.utilidades.ConsolaTest
```

La prueba verifica la salida de cada método, incluyendo el tratamiento de
textos nulos o en blanco por `linea(String)`. Si todo pasa, muestra
`Todas las pruebas unitarias pasaron.`; ante un fallo, termina con un error.
