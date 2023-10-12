package com.ucatolica.ecommerce.repository;

import com.ucatolica.ecommerce.model.Cart;
import com.ucatolica.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de datos para la entidad Cart en el sistema de comercio electrónico.
 * Proporciona métodos para realizar operaciones de acceso a datos relacionadas con el carrito de compras.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    /**
     * Busca todos los elementos del carrito de compras de un usuario y los ordena por fecha de creación descendente.
     *
     * @param user El usuario cuyo carrito de compras se va a buscar.
     * @return Una lista de elementos del carrito de compras del usuario ordenados por fecha de creación descendente.
     */
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

    /**
     * Elimina todos los elementos del carrito de compras de un usuario.
     *
     * @param user El usuario cuyo carrito de compras se va a vaciar.
     * @return Una lista de elementos del carrito de compras eliminados.
     */
    List<Cart> deleteByUser(User user);
}
