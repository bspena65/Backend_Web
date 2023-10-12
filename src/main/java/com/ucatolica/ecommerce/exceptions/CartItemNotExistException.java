package com.ucatolica.ecommerce.exceptions;

/**
 * Excepción que se lanza cuando se intenta realizar una operación en un elemento del carrito de compras que no existe.
 */
public class CartItemNotExistException extends IllegalArgumentException {

    /**
     * Crea una nueva instancia de `CartItemNotExistException` con el mensaje de error especificado.
     *
     * @param msg El mensaje de error que describe la excepción.
     */
    public CartItemNotExistException(String msg) {
        super(msg);
    }
}
