package com.ucatolica.ecommerce.dto.user;

/**
 * DTO que representa la respuesta de inicio de sesión de un usuario.
 */
public class SignInResponseDto {
    private String status;
    private String token;
    private String role;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    /**
     * Obtiene el estado de la respuesta de inicio de sesión.
     *
     * @return El estado de la respuesta.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Establece el estado de la respuesta de inicio de sesión.
     *
     * @param status El estado de la respuesta.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtiene el token de autenticación de inicio de sesión.
     *
     * @return El token de autenticación.
     */
    public String getToken() {
        return token;
    }

    /**
     * Establece el token de autenticación de inicio de sesión.
     *
     * @param token El token de autenticación.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Constructor de la clase `SignInResponseDto` que recibe el estado y el token de autenticación.
     *
     * @param status El estado de la respuesta.
     * @param token  El token de autenticación.
     *
     */
    public SignInResponseDto(String status, String token, String role) {
        this.status = status;
        this.token = token;
        this.role = role;
    }
}
