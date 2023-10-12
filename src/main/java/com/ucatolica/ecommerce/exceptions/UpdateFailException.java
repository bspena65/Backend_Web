package com.ucatolica.ecommerce.exceptions;

/**
 * Excepción personalizada que se lanza cuando falla una operación de actualización en la aplicación.
 */
public class UpdateFailException extends IllegalArgumentException {
    /**
     * Crea una nueva instancia de la excepción de falla en la actualización con un mensaje de error.
     *
     * @param msg El mensaje de error que describe la causa de la excepción.
     */
    public UpdateFailException(String msg) {
        super(msg);
    }
}
