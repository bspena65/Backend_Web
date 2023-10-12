package com.ucatolica.ecommerce.repository;

import com.ucatolica.ecommerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de datos para la entidad OrderItem en el sistema de comercio electrónico.
 * Proporciona métodos para realizar operaciones de acceso a datos relacionadas con los elementos de pedido.
 */
public interface OrderItemsRepository extends JpaRepository<OrderItem, Integer> {
}
