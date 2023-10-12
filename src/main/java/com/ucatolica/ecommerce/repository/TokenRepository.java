package com.ucatolica.ecommerce.repository;


import com.ucatolica.ecommerce.model.AuthenticationToken;
import com.ucatolica.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de datos para la entidad AuthenticationToken en el sistema de comercio electrónico.
 * Proporciona métodos para realizar operaciones de acceso a datos relacionadas con los tokens de autenticación.
 */
@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {

    /**
     * Busca y recupera un token de autenticación asociado a un usuario específico.
     *
     * @param user El usuario al que se asocia el token de autenticación.
     * @return El token de autenticación asociado al usuario especificado, o null si no se encuentra ninguno.
     */
    AuthenticationToken findTokenByUser(User user);

    /**
     * Busca y recupera un token de autenticación por su valor de token.
     *
     * @param token El valor del token de autenticación a buscar.
     * @return El token de autenticación que coincide con el valor especificado, o null si no se encuentra ninguno.
     */
    AuthenticationToken findTokenByToken(String token);
}
