package com.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecommerce.exception.CantidadInvalidaException;
// ESTOS IMPORTAN TUS CLASES (Asegúrate de que existan)
import com.ecommerce.model.Producto;

public class TiendaTest {

    private CarritoService carrito;
    private DescuentoService descuentoService;
    private Producto laptop;
    private Producto teclado;

    @BeforeEach
    void setup() {
        // Inicializamos los servicios y productos antes de cada test
        carrito = new CarritoService();
        descuentoService = new DescuentoService();
        laptop = new Producto(1, "Laptop", "Electronica", 1000.0);
        teclado = new Producto(2, "Teclado", "Oficina", 50.0);
    }

    @Test
    void testCalculoTotalBase() throws CantidadInvalidaException {
        // 1. Agregar productos
        carrito.agregar(laptop, 1); // 1000
        carrito.agregar(teclado, 2); // 100 (50x2)
        
        // 2. Verificar que la suma sea 1100
        assertEquals(1100.0, carrito.calcularTotalBase(), "El total base debería ser 1100");
    }

    @Test
    void testValidacionCantidadInvalida() {
        // 1. Intentar agregar cantidad 0 o negativa debe lanzar la excepción
        assertThrows(CantidadInvalidaException.class, () -> {
            carrito.agregar(laptop, 0);
        }, "Debería lanzar CantidadInvalidaException al agregar 0 unidades");
    }

    @Test
    void testAplicacionDescuentoPorMontoYCat() throws CantidadInvalidaException {
        // 1. Agregar laptop de $1000 (Supera los $500 y es Electronica)
        carrito.agregar(laptop, 1);
        double totalBase = carrito.calcularTotalBase();
        
        // 2. Calcular descuentos
        double descuento = descuentoService.calcularTotalDescuentos(totalBase, carrito.getItems());
        
        // Regla: 10% por monto ($100) + 5% por categoría ($50) = $150
        assertEquals(150.0, descuento, "El descuento total debería ser $150");
    }
}