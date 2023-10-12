package com.ucatolica.ecommerce.dto.checkout;

/**
 * DTO que representa una respuesta de Stripe que contiene un identificador de sesión.
 */
public class StripeResponse {
    private String sessionId;

    /**
     * Obtiene el identificador de sesión de Stripe.
     *
     * @return El identificador de sesión.
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Establece el identificador de sesión de Stripe.
     *
     * @param sessionId El identificador de sesión.
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Constructor de la clase `StripeResponse` que recibe el identificador de sesión como parámetro.
     *
     * @param sessionId El identificador de sesión.
     */
    public StripeResponse(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Constructor vacío de la clase `StripeResponse`.
     */
    public StripeResponse() {
    }
}
