package com.ucatolica.ecommerce.config;

/**
 * Clase que contiene mensajes de cadena utilizados en la aplicación.
 */
public class MessageStrings {

    /**
     * Mensaje de error que se muestra cuando un usuario no está autorizado para realizar una operación.
     */
    public static final String USER_NOT_PERMITTED = "user is not permitted to perform this operation";

    /**
     * Mensaje de error que se muestra cuando no se encuentra presente un token de autenticación.
     */
    public static final String AUTH_TOEKN_NOT_PRESENT = "authentication token not present";

    /**
     * Mensaje de error que se muestra cuando un token de autenticación no es válido.
     */
    public static final String AUTH_TOEKN_NOT_VALID = "authentication token not valid";

    /**
     * Mensaje de éxito que se muestra cuando se crea un usuario correctamente.
     */
    public static final String USER_CREATED = "user created successfully";

    /**
     * Mensaje de error que se muestra cuando falta una clave primaria para una actualización.
     */
    public static final String ID_NOT_PRESENT = "primary key is required for updating";

    /**
     * Mensaje de error que se muestra cuando la contraseña es incorrecta.
     */
    public static final String WRONG_PASSWORD = "please check the pass";
}
