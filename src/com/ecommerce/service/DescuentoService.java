package com.ecommerce.service;

import com.ecommerce.model.Producto;
import java.util.Map;

public class DescuentoService {
    
    // Cambiamos el nombre a verReglas() para que MenuPrincipal lo encuentre
    public String verReglas() {
        return "--- REGLAS DE DESCUENTO ---\n" +
               "1. 10% de descuento si el total base es >= $500\n" +
               "2. 5% de descuento adicional si compras productos de 'Electronica'";
    }

    public double calcularTotalDescuentos(double totalBase, Map<Producto, Integer> items) {
        double descuento = 0;
        
        // Regla 1: Por monto total
        if (totalBase >= 500) {
            descuento += totalBase * 0.10;
        }

        // Regla 2: Por categoría específica
        boolean tieneElectronica = items.keySet().stream()
                .anyMatch(p -> p.getCategoria().equalsIgnoreCase("Electronica"));
        
        if (tieneElectronica) {
            descuento += totalBase * 0.05;
        }

        return descuento;
    }
}