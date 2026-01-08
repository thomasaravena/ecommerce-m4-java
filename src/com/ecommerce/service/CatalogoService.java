package com.ecommerce.service;

import com.ecommerce.model.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogoService {
    // Lista en memoria que actuará como nuestra base de datos de productos
    private List<Producto> productos = new ArrayList<>();

    public CatalogoService() {
        // Agregamos algunos productos por defecto para que la app no inicie vacía
        productos.add(new Producto(1, "Laptop", "Electronica", 800.0));
        productos.add(new Producto(2, "Teclado", "Electronica", 50.0));
        productos.add(new Producto(3, "Silla Gamer", "Hogar", 250.0));
        productos.add(new Producto(4, "Monitor 4K", "Electronica", 400.0));
    }

    // --- MÉTODOS PARA EL ADMINISTRADOR ---

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public boolean eliminar(int id) {
        // Elimina el producto si el ID coincide
        return productos.removeIf(p -> p.getId() == id);
    }

    public void editarProducto(int id, String nuevoNombre, String nuevaCat, double nuevoPrecio) {
        Producto p = buscarPorId(id);
        if (p != null) {
            p.setNombre(nuevoNombre);
            p.setCategoria(nuevaCat);
            p.setPrecio(nuevoPrecio);
        }
    }

    // --- MÉTODOS DE BÚSQUEDA Y LISTADO ---

    public List<Producto> listarTodos() {
        return productos;
    }

    public List<Producto> buscar(String criterio) {
        // Busca por nombre o categoría (ignora mayúsculas/minúsculas)
        return productos.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(criterio.toLowerCase()) || 
                             p.getCategoria().toLowerCase().contains(criterio.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Producto buscarPorId(int id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}