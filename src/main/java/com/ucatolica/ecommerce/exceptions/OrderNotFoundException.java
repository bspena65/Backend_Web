package com.ucatolica.ecommerce.exceptions;

/**
 * Excepción personalizada que se lanza cuando no se encuentra una orden en la aplicación.
 */
public class OrderNotFoundException extends IllegalArgumentException {
    /**
     * Crea una nueva instancia de la excepción de orden no encontrada con un mensaje de error.
     *
     * @param msg El mensaje de error que describe la causa de la excepción.
     */
    public OrderNotFoundException(String msg) {
        super(msg);
    }
}
