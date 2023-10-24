package com.ucatolica.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ucatolica.ecommerce.enums.Role;

import jakarta.persistence.*;
import java.util.List;

/**
 * Representa un usuario en el sistema de comercio electrónico.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador único del usuario.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id El identificador único del usuario.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el primer nombre del usuario.
     *
     * @return El primer nombre del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el primer nombre del usuario.
     *
     * @param name El primer nombre del usuario.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el apellido del usuario.
     *
     * @return El apellido del usuario.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del usuario.
     *
     * @param lastName El apellido del usuario.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email El correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return El rol del usuario.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param role El rol del usuario.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Crea una nueva instancia de `User` con los datos proporcionados.
     *
     * @param name El primer nombre del usuario.
     * @param lastName  El apellido del usuario.
     * @param email     El correo electrónico del usuario.
     * @param role      El rol del usuario.
     * @param password  La contraseña del usuario.
     */
    public User(String name, String lastName, String email, Role role, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    /**
     * Crea una nueva instancia de `User`.
     */
    public User() {
    }

    /**
     * Obtiene la lista de órdenes asociadas a este usuario.
     *
     * @return La lista de órdenes asociadas a este usuario.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Establece la lista de órdenes asociadas a este usuario.
     *
     * @param orders La lista de órdenes asociadas a este usuario.
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
