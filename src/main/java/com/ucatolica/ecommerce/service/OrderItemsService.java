package com.ucatolica.ecommerce.service;

import com.ucatolica.ecommerce.model.OrderItem;
import com.ucatolica.ecommerce.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Servicio que gestiona las operaciones relacionadas con los elementos de pedido (OrderItem) en el sistema de comercio electrónico.
 * Esto incluye la adición de productos a un pedido y la interacción con el repositorio de elementos de pedido.
 */
@Service
@Transactional
public class OrderItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    /**
     * Agrega los productos ordenados (OrderItem) al repositorio de elementos de pedido.
     *
     * @param orderItem El objeto OrderItem que representa los productos ordenados.
     */
    public void addOrderedProducts(OrderItem orderItem) {
        orderItemsRepository.save(orderItem);
    }
}

