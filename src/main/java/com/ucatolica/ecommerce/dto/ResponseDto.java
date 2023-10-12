package com.ucatolica.ecommerce.dto;

/**
 * DTO que representa una respuesta genérica.
 */
public class ResponseDto {
    private String status;
    private String message;

    /**
     * Obtiene el estado de la respuesta.
     *
     * @return El estado de la respuesta.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Establece el estado de la respuesta.
     *
     * @param status El estado de la respuesta.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtiene el mensaje de la respuesta.
     *
     * @return El mensaje de la respuesta.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece el mensaje de la respuesta.
     *
     * @param message El mensaje de la respuesta.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Constructor de la clase `ResponseDto` que recibe el estado y el mensaje de la respuesta.
     *
     * @param status  El estado de la respuesta.
     * @param message El mensaje de la respuesta.
     */
    public ResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
