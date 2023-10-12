package com.ucatolica.ecommerce.exceptions;

/**
 * Excepción que se lanza cuando falla la autenticación de un usuario en la aplicación.
 */
public class AuthenticationFailException extends IllegalArgumentException {

    /**
     * Crea una nueva instancia de `AuthenticationFailException` con el mensaje de error especificado.
     *
     * @param msg El mensaje de error que describe la excepción.
     */
    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
