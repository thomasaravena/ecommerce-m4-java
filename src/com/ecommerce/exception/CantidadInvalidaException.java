package com.ecommerce.exception;

public class CantidadInvalidaException extends Exception {
   
    private static final long serialVersionUID = 1L;

    public CantidadInvalidaException(String mensaje) {
        super(mensaje);
    }
}