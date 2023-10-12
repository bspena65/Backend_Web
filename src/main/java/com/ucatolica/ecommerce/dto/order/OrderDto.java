package com.ucatolica.ecommerce.dto.order;

import com.ucatolica.ecommerce.model.Order;

import javax.validation.constraints.NotNull;

/**
 * DTO que representa un pedido (order) y su asociación con un usuario.
 */
public class OrderDto {
    private Integer id;
    private @NotNull Integer userId;

    /**
     * Constructor vacío de la clase `OrderDto`.
     */
    public OrderDto() {
    }

    /**
     * Constructor de la clase `OrderDto` que crea un DTO a partir de un objeto `Order`.
     *
     * @param order El objeto `Order` a partir del cual se crea el DTO.
     */
    public OrderDto(Order order) {
        this.setId(order.getId());
        //this.setUserId(order.getUserId()); // Comentado por razones desconocidas
    }

    /**
     * Obtiene el ID del pedido.
     *
     * @return El ID del pedido.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el ID del pedido.
     *
     * @param id El ID del pedido.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del usuario asociado al pedido.
     *
     * @return El ID del usuario.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Establece el ID del usuario asociado al pedido.
     *
     * @param userId El ID del usuario.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
