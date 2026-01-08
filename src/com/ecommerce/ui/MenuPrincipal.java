package com.ecommerce.ui;

import com.ecommerce.model.Producto;
import com.ecommerce.service.*;
import com.ecommerce.exception.CantidadInvalidaException;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner sc = new Scanner(System.in);
    private CatalogoService catalogo = new CatalogoService();
    private CarritoService carrito = new CarritoService();
    private DescuentoService descService = new DescuentoService();

    public void iniciar() {
        int opt = -1;
        while (opt != 0) {
            System.out.println("\n--- E-COMMERCE CLI ---");
            System.out.println("1) ADMIN | 2) USUARIO | 0) SALIR");
            System.out.print("Seleccione: ");
            opt = sc.nextInt();
            if (opt == 1) menuAdmin();
            else if (opt == 2) menuUsuario();
        }
    }

    // --- FLUJO ADMINISTRADOR ---
    private void menuAdmin() {
        System.out.println("\n[ADMIN] 1.Listar 2.Crear 3.Eliminar 4.Volver");
        int sub = sc.nextInt();
        switch (sub) {
            case 1 -> catalogo.listarTodos().forEach(System.out::println);
            case 2 -> {
                System.out.print("ID: "); int id = sc.nextInt();
                System.out.print("Nombre: "); String n = sc.next();
                System.out.print("Categoría: "); String c = sc.next();
                System.out.print("Precio: "); double p = sc.nextDouble();
                catalogo.agregarProducto(new Producto(id, n, c, p));
                System.out.println("Producto agregado.");
            }
            case 3 -> {
                System.out.print("ID a eliminar: ");
                int id = sc.nextInt();
                if (catalogo.eliminar(id)) System.out.println("Eliminado correctamente.");
                else System.out.println("ID no encontrado.");
            }
        }
    }

    // --- FLUJO USUARIO ---
    private void menuUsuario() {
        System.out.println("\n[USER] 1.Listar 2.Agregar al Carrito 3.Ver Carrito 4.Descuentos 5.Confirmar Compra 6.Volver");
        int sub = sc.nextInt();
        switch(sub) {
            case 1 -> catalogo.listarTodos().forEach(System.out::println);
            case 2 -> {
                System.out.print("Ingrese ID del producto: ");
                int id = sc.nextInt();
                System.out.print("Cantidad: ");
                int cant = sc.nextInt();
                try {
                    Producto p = catalogo.buscarPorId(id);
                    carrito.agregar(p, cant);
                    System.out.println("Agregado al carrito.");
                } catch (CantidadInvalidaException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case 3 -> {
                System.out.println("--- TU CARRITO ---");
                carrito.getItems().forEach((prod, cant) -> 
                    System.out.println(prod.getNombre() + " x" + cant + " - Subtotal: $" + (prod.getPrecio() * cant)));
                System.out.println("TOTAL BASE: $" + carrito.calcularTotalBase());
            }
            case 4 -> System.out.println(descService.verReglas());
            case 5 -> confirmarCompra();
        }
    }

    private void confirmarCompra() {
        double base = carrito.calcularTotalBase();
        if (base <= 0) {
            System.out.println("El carrito está vacío. No puedes comprar.");
            return;
        }
        double desc = descService.calcularTotalDescuentos(base, carrito.getItems());
        System.out.println("\n--- RESUMEN DE COMPRA ---");
        System.out.println("Subtotal: $" + base);
        System.out.println("Descuentos aplicados: -$" + desc);
        System.out.println("TOTAL A PAGAR: $" + (base - desc));
        
        carrito.vaciar();
        System.out.println("¡Gracias por su compra! Carrito vaciado.");
    }
}