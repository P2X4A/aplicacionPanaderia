# Sistema de Panaderia

Sistema de gestion de una panaderia con almacen de productos, catalogo y venta, usando persistencia en archivo plano.

## Detalle

| | |
|---|---|
| Tipo | Swing (MVC) |
| Archivos Java | 12 |
| Version de Java | 21 |
| Patron | Arquitectura MVC con capa de persistencia (patron DAO) y DataMapper |

## Funcionalidades

- Almacen de productos por tipo (pan, postre, galleta)
- Ventana principal y formulario de registro
- Capa de persistencia DAO
- DataMapper entidad-DTO
- Interfaz grafica Swing

## Estructura del proyecto

```
aplicacionPanaderia/
|-- pom.xml       configuracion de Maven, importable desde cualquier IDE
|-- src/          codigo fuente
`-- README.md
```

## Como ejecutarlo

### Con Maven

```bash
mvn clean compile
mvn exec:java
```

### Con javac directamente

```bash
# Linux / macOS
javac -d bin $(find src -name "*.java")

# Windows (PowerShell)
javac -d bin (Get-ChildItem -Recurse -Filter *.java src).FullName
```

```bash
java -cp bin co.edu.unbosque.controller.AplMain
```

### Desde un IDE

Cualquier IDE con soporte de Java (Eclipse, IntelliJ IDEA, VS Code o NetBeans)
puede importar el proyecto directamente como **proyecto Maven**. Basta con abrir
o importar la carpeta: el IDE detecta el `pom.xml`, resuelve las dependencias y
compila sin configuracion adicional.

## Autor

Desarrollado por [Sergio Parra](https://github.com/P2X4A) como parte de su
formacion en Java.

## Licencia

Ver el archivo [LICENSE](LICENSE).