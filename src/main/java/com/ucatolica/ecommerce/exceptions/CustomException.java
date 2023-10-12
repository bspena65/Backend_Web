package com.ucatolica.ecommerce.exceptions;

/**
 * Excepción personalizada que se utiliza para representar situaciones excepcionales específicas en la aplicación.
 */
public class CustomException extends IllegalArgumentException {

    /**
     * Crea una nueva instancia de `CustomException` con el mensaje de error especificado.
     *
     * @param msg El mensaje de error que describe la excepción.
     */
    public CustomException(String msg) {
        super(msg);
    }
}
