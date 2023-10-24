package com.ucatolica.ecommerce.dto.user;

import com.ucatolica.ecommerce.enums.Role;

/**
 * DTO que representa los datos para actualizar un usuario existente.
 */
public class UserUpdateDto {
    // Se omite la actualización de la contraseña por ahora
    private Integer id;
    private String name;
    private String lastName;
    private Role role;

    /**
     * Obtiene el identificador del usuario.
     *
     * @return El identificador del usuario.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param id El identificador del usuario.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param name El nombre del usuario.
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
}
