package com.ucatolica.ecommerce.common;

import java.time.LocalDateTime;

/**
 * Esta clase representa una respuesta genérica para las operaciones de la API.
 */
public class ApiResponse {
    private final boolean success;
    private final String message;

    /**
     * Constructor de la clase ApiResponse.
     *
     * @param success Indica si la operación fue exitosa.
     * @param message El mensaje asociado a la respuesta.
     */
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Obtiene el estado de éxito de la respuesta.
     *
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Obtiene el mensaje asociado a la respuesta.
     *
     * @return El mensaje de la respuesta.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Obtiene la marca de tiempo actual en formato de cadena.
     *
     * @return La marca de tiempo actual en formato de cadena.
     */
    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }
}
