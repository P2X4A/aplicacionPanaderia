# Bakery Management System - aplicacionPanaderia

## Description

Bakery management system with a product warehouse, catalog and sales, using plain text file persistence (`productos_panaderia.txt`). The interface is built with **Swing** following the **MVC** pattern, and the data layer uses the **DAO** pattern with a `DataMapper`.

Academic project developed for the **Java programming** courses - Systems Engineering, Universidad El Bosque.

## Author

- Sergio Alejandro Parra Rivera

## Technologies

- Java 21 (compiled with `maven-compiler-plugin`)
- Swing for the graphical interface
- Maven 3.8+ with `exec-maven-plugin` to run the application
- **MVC** architecture (`controller`, `model`, `view`, `constant`)
- **DAO** pattern with a `DataMapper` over plain text files

## Requirements

| Requirement | Version | How to check |
| --- | --- | --- |
| JDK | 21 or higher | `javac -version` |
| Maven | 3.8 or higher | `mvn -v` |
| Git | Any recent version | `git --version` |

## Installation

Follow these steps exactly so the application runs on the first try.

### 1. Clone the repository

```bash
git clone https://github.com/P2X4A/aplicacionPanaderia.git
```

### 2. Enter the project folder

```bash
cd aplicacionPanaderia
```

> On Windows, if your path contains spaces, always quote it, for example: `cd "C:\Users\Example\Documents\aplicacionPanaderia"`.

### 3. Verify your environment

```bash
javac -version
mvn -v
```

Expected: JDK 21 (or higher) and Maven 3.8 (or higher). If any command fails, install or update that tool before continuing.

### 4. Compile the project

```bash
mvn clean compile
```

What success looks like:

- The console ends with `BUILD SUCCESS`.
- A `target/classes/` folder is created with the compiled classes.

## Running the application

```bash
mvn exec:java
```

The main window opens with the product catalog; the form window is used to register products. The sample catalog is included in the repository (`productos_panaderia.txt`).

Troubleshooting:

- `ClassNotFoundException` or missing main class: compile first with `mvn clean compile`.
- `UnsupportedClassVersionError`: your JDK is older than 21; install JDK 21 or higher and check it with `javac -version`.

## Main features

- **Warehouse:** `AlmacenPanaderia` keeps the stock of each product.
- **Catalog and sales:** browse the registered products and their product types (`TipoProducto`).
- **Registration form:** `VentanaFormulario` captures new products with the buttons defined in `GUIBotonConstant`.
- **Persistence:** products are stored in the plain text file `productos_panaderia.txt` through `ArchivoPlanoDAO`, `ProductoDAO`, a `DataMapper` and `ProductoPanaderiaDTO`.
- **Swing UI:** `VentanaPrincipal` shows the catalog and links to the rest of the windows.

## Project structure

```text
aplicacionPanaderia/
├── pom.xml
├── README.md
├── LICENSE
├── productos_panaderia.txt                # Sample product catalog
└── src/
    └── co/edu/unbosque/
        ├── constant/
        │   ├── GUIBotonConstant.java      # Button texts and labels
        │   └── TipoProducto.java          # Product types
        ├── controller/
        │   ├── AplMain.java               # Entry point (main class)
        │   └── Controlador.java           # Application controller (MVC)
        ├── model/
        │   ├── AlmacenPanaderia.java      # Product warehouse
        │   ├── ProductoPanaderia.java     # Product entity
        │   └── persistence/
        │       ├── ProductoDAO.java       # DAO interface
        │       ├── ArchivoPlanoDAO.java   # Plain text file DAO
        │       ├── ProductoPanaderiaDTO.java # Product record (DTO)
        │       └── DataMapper.java        # Domain object <-> DTO mapping
        └── view/                          # Swing windows
            ├── VentanaPrincipal.java
            └── VentanaFormulario.java
```
