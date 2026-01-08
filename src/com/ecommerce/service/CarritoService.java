package com.ecommerce.service; 

import com.ecommerce.exception.CantidadInvalidaException;
import com.ecommerce.model.Producto;
import java.util.*;

public class CarritoService {
    private Map<Producto, Integer> items = new HashMap<>();

    public void agregar(Producto p, int cantidad) throws CantidadInvalidaException {
        if (cantidad <= 0) throw new CantidadInvalidaException("La cantidad debe ser > 0");
        items.put(p, items.getOrDefault(p, 0) + cantidad);
    }

    public void quitar(int productoId) {
        items.entrySet().removeIf(entry -> entry.getKey().getId() == productoId);
    }

    public double calcularTotalBase() {
        return items.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrecio() * e.getValue())
                .sum();
    }

    public Map<Producto, Integer> getItems() { return items; }
    public void vaciar() { items.clear(); }
}