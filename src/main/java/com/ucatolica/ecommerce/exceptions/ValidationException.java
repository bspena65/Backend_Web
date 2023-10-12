package com.ucatolica.ecommerce.exceptions;

/**
 * Excepción personalizada que se lanza cuando ocurren errores de validación en la aplicación.
 */
public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = 6064663768170825752L;

    /**
     * Crea una nueva instancia de la excepción de validación con un mensaje de error.
     *
     * @param message El mensaje de error que describe la causa de la excepción.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Crea una nueva instancia de la excepción de validación con un mensaje de error y una causa subyacente.
     *
     * @param message El mensaje de error que describe la causa de la excepción.
     * @param cause   La causa subyacente de la excepción.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
