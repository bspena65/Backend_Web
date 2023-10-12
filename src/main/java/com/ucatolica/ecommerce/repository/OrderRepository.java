package com.ucatolica.ecommerce.repository;

import com.ucatolica.ecommerce.model.Order;
import com.ucatolica.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de datos para la entidad Order en el sistema de comercio electrónico.
 * Proporciona métodos para realizar operaciones de acceso a datos relacionadas con los pedidos.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    /**
     * Encuentra todos los pedidos de un usuario dado y los ordena por fecha de creación en orden descendente.
     *
     * @param user El usuario cuyos pedidos se deben buscar.
     * @return Una lista de pedidos del usuario, ordenados por fecha de creación en orden descendente.
     */
    List<Order> findAllByUserOrderByCreatedDateDesc(User user);
}
