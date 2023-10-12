package com.ucatolica.ecommerce.dto.order;

import javax.validation.constraints.NotNull;

/**
 * DTO que representa los elementos de un pedido (order) que incluyen detalles de productos.
 */
public class OrderItemsDto {

    private @NotNull double price;
    private @NotNull int quantity;
    private @NotNull int orderId;
    private @NotNull int productId;

    /**
     * Constructor vacío de la clase `OrderItemsDto`.
     */
    public OrderItemsDto() {}

    /**
     * Constructor de la clase `OrderItemsDto` que recibe los detalles de un elemento de pedido.
     *
     * @param price El precio del producto.
     * @param quantity La cantidad del producto.
     * @param orderId El ID del pedido.
     * @param productId El ID del producto.
     */
    public OrderItemsDto(@NotNull double price, @NotNull int quantity, @NotNull int orderId, @NotNull int productId) {
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Establece el precio del producto.
     *
     * @param price El precio del producto.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Obtiene la cantidad del producto.
     *
     * @return La cantidad del producto.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad del producto.
     *
     * @param quantity La cantidad del producto.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Obtiene el ID del pedido al que pertenece este elemento.
     *
     * @return El ID del pedido.
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Establece el ID del pedido al que pertenece este elemento.
     *
     * @param orderId El ID del pedido.
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Obtiene el ID del producto asociado a este elemento.
     *
     * @return El ID del producto.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Establece el ID del producto asociado a este elemento.
     *
     * @param productId El ID del producto.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
}
