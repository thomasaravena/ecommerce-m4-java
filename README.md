# E-Commerce CLI - Sistema de Gestión de Compras (Módulo 4)

Este proyecto es una aplicación de consola desarrollada en Java que simula el flujo de un comercio electrónico. Permite gestionar un catálogo de productos, manejar un carrito de compras y aplicar reglas de negocio automáticas mediante servicios especializados.

Enlace al Repositorio: https://github.com/thomasaravena/ecommerce-m4-java

## Tecnologías Utilizadas
* Lenguaje: Java 17 o superior.
* Pruebas Unitarias: JUnit 5.
* Gestión de Versiones: Git y GitHub.
* Paradigma: Programación Orientada a Objetos (POO).

## Instrucciones de Ejecución
1. Clonar el repositorio: git clone https://github.com/thomasaravena/ecommerce-m4-java.git
2. Importar en Eclipse: File > Import > General > Existing Projects into Workspace.
3. Configurar JUnit: Si aparecen errores en los tests, hacer clic derecho en el proyecto > Build Path > Add Libraries > JUnit > JUnit 5.
4. Ejecutar: Localizar la clase Main.java en el paquete com.ecommerce, clic derecho y seleccionar Run As > Java Application.

## Menús del Sistema
El sistema cuenta con dos menús principales:
1. Menú Administrador: Permite listar, crear y eliminar productos del catálogo.
2. Menú Usuario: Permite buscar productos, gestionar el carrito, ver reglas de descuento y confirmar la compra.

## Ejemplo de Compra y Descuentos
A continuación, se detalla un flujo típico de compra:

### 1. Ver Descuentos Activos
Al ingresar al menú de usuario y seleccionar la opción 4 (Descuentos), el sistema muestra:
> --- REGLAS DE DESCUENTO ---
> 1. 10% de descuento si el total base es >= $500
> 2. 5% de descuento adicional si compras productos de 'Electronica'

### 2. Confirmación de Compra
Si un usuario agrega una Laptop ($1000) al carrito y selecciona 5 (Confirmar Compra), el sistema procesa lo siguiente:

> --- RESUMEN DE COMPRA ---
> Subtotal: $1000.0
> Descuentos aplicados: -$150.0  *(100 por monto + 50 por categoría)*
> TOTAL A PAGAR: $850.0
> ¡Gracias por su compra! Carrito vaciado.

## Pruebas Unitarias (JUnit)
Se han implementado tests automatizados para validar:
- Cálculo de Totales: Suma exacta de productos y cantidades.
- Validación de Excepciones: Bloqueo de cantidades negativas (CantidadInvalidaException).
- Lógica de Descuentos: Aplicación correcta de porcentajes según reglas de negocio.
