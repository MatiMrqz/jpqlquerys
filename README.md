# Proyecto de Demostración de Consultas JPQL

Este es un proyecto educativo diseñado para demostrar la implementación y ejecución de diversas consultas JPQL (Java Persistence Query Language) utilizando JPA e Hibernate. El proyecto se basa en un modelo de negocio simple de facturación, con Clientes, Artículos (Insumos y Manufacturados) y Facturas.

El objetivo principal es servir como repositorio de ejemplos prácticos de consultas, desde las más simples (`SELECT *`) hasta otras más complejas que involucran `JOIN`, `GROUP BY`, `ORDER BY`, subconsultas y funciones de agregación (`COUNT`, `SUM`, `MAX`, `AVG`).

-----

## Tecnologías Utilizadas

* **Java**: Lenguaje de programación base.
* **JPA (Java Persistence API)**: Estándar para el mapeo objeto-relacional (ORM).
* **Hibernate**: Implementación de JPA para la persistencia de datos.
* **H2 Database**: Base de datos SQL ligera basada en archivos (no en memoria).
* **Gradle**: Herramienta de automatización de compilación y gestión de dependencias.
* **Lombok**: Biblioteca para reducir el código repetitivo (boilerplate) en las entidades y modelos.

-----

## Modelo de Entidades (Resumen)

El núcleo del proyecto se define en el paquete `org.example`:

* **`Cliente`**: Almacena información básica de un cliente (CUIT, Razón Social).
* **`Articulo`**: Clase base abstracta para todos los artículos, usando la estrategia de herencia `InheritanceType.JOINED`.
    * **`ArticuloInsumo`**: Representa un artículo que se compra (materia prima).
    * **`ArticuloManufacturado`**: Representa un artículo que se produce a partir de `ArticuloInsumo` (ej. "Ensalada de frutas").
* **`Factura`**: Encabezado de la factura, que se relaciona con un `Cliente`.
* **`FacturaDetalle`**: Línea de detalle de una `Factura`, que se relaciona con un `Articulo`.
* **`Categoria`**: Agrupa artículos.

Las entidades están registradas en el archivo `persistence.xml`.

-----

## Puesta en Marcha

Para ejecutar este proyecto y probar las consultas, sigue estos pasos:

### 1\. Prerrequisitos

* Tener instalado un JDK (Java Development Kit).
* Tener Gradle instalado (o usar el wrapper `gradlew` incluido).

### 2\. Construir el Proyecto

Abre una terminal en la raíz del proyecto y ejecuta:

```bash
./gradlew build
```

### 3\. Poblar la Base de Datos (¡Importante\!)

Antes de poder ejecutar las consultas, necesitas tener datos de prueba.

Ejecuta la clase `org.example.Main`.

* **Ruta:** `src/main/java/org/example/Main.java`
* **Acción:** Este script creará e insertará entidades de ejemplo (Clientes, Artículos como "Manzana" y "Pera", un Artículo Manufacturado "Ensalada de frutas", y una Factura de prueba) en la base de datos H2 (`./data/db`).

### 4\. Ejecutar las Consultas de Demostración

La clase principal para ver los ejemplos de JPQL en acción es `managers.MainConsultasJPQL`.

* **Ruta:** `src/main/java/managers/MainConsultasJPQL.java`
* **Acción:** Esta clase contiene múltiples métodos (`static void`) que llaman a los diferentes "Managers" (como `FacturaManager`, `ClienteManager`) para ejecutar consultas JPQL específicas y mostrar los resultados en la consola.

-----

## Ejemplos de Consultas Incluidas

El código está organizado en "Managers" (`managers/`) que contienen los métodos de consulta. La clase `MainConsultasJPQL` llama a estos métodos.

Algunos de los ejemplos que puedes encontrar son:

### En `ClienteManager`

* **`getClienteMaxCantFacturas()`**: Obtiene el cliente que ha emitido más facturas (usa `GROUP BY`, `COUNT`, `ORDER BY`).
* **`getClientesXRazonSocialParcialmente()`**: Busca clientes por parte de su razón social (usa `LOWER` y `LIKE`).
* **`getClientesXIds()`**: Busca clientes que estén en una lista de IDs (usa `IN`).

### En `FacturaManager`

* **`getArticulosMasVendidos()`**: Devuelve los artículos ordenados por la cantidad total vendida (usa `GROUP BY` y `SUM`).
* **`getMontoFacturadoXCliente()`**: Calcula el total facturado para un cliente específico (usa `SUM` y `WHERE`).
* **`getFacturasXNombreArticulo()`**: Busca facturas que contengan un artículo por su nombre (usa `JOIN` y `LIKE`).
* **`getFacturasUlt3MesesXCliente()`**: Busca facturas de un cliente en un rango de fechas (usa `WHERE` con fechas).
* **`getArticuloMasCaroXFactura()`**: Obtiene el artículo más caro de una factura específica (usa `JOIN`, `ORDER BY` y `setMaxResults(1)`).

### En `ArticuloManager`

* **`getArticulosPrecioMayorAlPromedio()`**: Obtiene artículos cuyo precio de venta es superior al promedio de todos los artículos (usa una subconsulta en la cláusula `WHERE`).
* **`getArticulosXNombre()`**: Busca artículos por denominación.
* **`getArticulosXCodigo()`**: Busca artículos por código.

---

## 📄 Autores y Créditos

**Cátedra:** Desarrollo de Software
**Tema:** JPQL  
**Institución:** Universidad Tecnológica Nacional - Facultad Regional Mendoza
**Año:** 2025

**Integrantes del grupo:**
- Bruno Zaupa
- Candela Fernandez
- Emiliano Echavarria
- Julián Fernandez
- Macarena Copparoni
- Matías Márquez
- Soledad Montenegro