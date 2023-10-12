package com.ucatolica.ecommerce.exceptions;

/**
 * Excepción personalizada que se lanza cuando un producto no existe en la aplicación.
 */
public class ProductNotExistException extends IllegalArgumentException {
    /**
     * Crea una nueva instancia de la excepción de producto inexistente con un mensaje de error.
     *
     * @param msg El mensaje de error que describe la causa de la excepción.
     */
    public ProductNotExistException(String msg) {
        super(msg);
    }
}
