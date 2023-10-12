package com.ucatolica.ecommerce.repository;


import com.ucatolica.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de datos para la entidad User en el sistema de comercio electrónico.
 * Proporciona métodos para realizar operaciones de acceso a datos relacionadas con los usuarios.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Recupera una lista de todos los usuarios registrados en el sistema.
     *
     * @return Una lista de todos los usuarios registrados.
     */
    List<User> findAll();

    /**
     * Busca y recupera un usuario por su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico del usuario a buscar.
     * @return El usuario que coincide con la dirección de correo electrónico especificada, o null si no se encuentra ninguno.
     */
    User findByEmail(String email);

    /**
     * Busca y recupera un usuario por su dirección de correo electrónico.
     * Este método es un sinónimo de {@link #findByEmail(String)}.
     *
     * @param email La dirección de correo electrónico del usuario a buscar.
     * @return El usuario que coincide con la dirección de correo electrónico especificada, o null si no se encuentra ninguno.
     */
    User findUserByEmail(String email);
}
