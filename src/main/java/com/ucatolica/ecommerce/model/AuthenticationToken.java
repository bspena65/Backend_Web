package com.ucatolica.ecommerce.model;

import jakarta.persistence.*;

//import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Clase que representa un token de autenticación asociado a un usuario en el sistema de comercio electrónico.
 */
@Entity
@Table(name = "tokens")
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; // Identificador único del token.

    private String token; // Valor del token generado.

    @Column(name = "created_date")
    private Date createdDate; // Fecha de creación del token.

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user; // Usuario asociado al token.

    /**
     * Crea una nueva instancia de la clase `AuthenticationToken` asociada a un usuario.
     *
     * @param user El usuario al que se asocia el token.
     */
    public AuthenticationToken(User user) {
        this.user = user;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString(); // Genera un token único utilizando UUID.
    }

    /**
     * Obtiene el identificador único del token.
     *
     * @return El identificador único del token.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del token.
     *
     * @param id El identificador único del token.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el valor del token generado.
     *
     * @return El valor del token generado.
     */
    public String getToken() {
        return token;
    }

    /**
     * Establece el valor del token generado.
     *
     * @param token El valor del token generado.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Obtiene la fecha de creación del token.
     *
     * @return La fecha de creación del token.
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Establece la fecha de creación del token.
     *
     * @param createdDate La fecha de creación del token.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Obtiene el usuario asociado al token.
     *
     * @return El usuario asociado al token.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario asociado al token.
     *
     * @param user El usuario asociado al token.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Crea una nueva instancia de la clase `AuthenticationToken` con los parámetros especificados.
     *
     * @param id          Identificador único del token.
     * @param token       Valor del token generado.
     * @param createdDate Fecha de creación del token.
     * @param user        Usuario asociado al token.
     */
    public AuthenticationToken(Integer id, String token, Date createdDate, User user) {
        this.id = id;
        this.token = token;
        this.createdDate = createdDate;
        this.user = user;
    }

    /**
     * Crea una nueva instancia de la clase `AuthenticationToken`.
     */
    public AuthenticationToken() {
    }
}
