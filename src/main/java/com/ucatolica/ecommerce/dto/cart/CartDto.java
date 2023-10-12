package com.ucatolica.ecommerce.dto.cart;

import java.util.List;

/**
 * DTO que representa el carrito de compras con sus elementos y costo total.
 */
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalCost;

    /**
     * Constructor de la clase `CartDto`.
     *
     * @param cartItemDtoList La lista de elementos del carrito de compras.
     * @param totalCost       El costo total del carrito de compras.
     */
    public CartDto(List<CartItemDto> cartItemDtoList, double totalCost) {
        this.cartItems = cartItemDtoList;
        this.totalCost = totalCost;
    }

    /**
     * Obtiene la lista de elementos del carrito de compras.
     *
     * @return La lista de elementos del carrito de compras.
     */
    public List<CartItemDto> getcartItems() {
        return cartItems;
    }

    /**
     * Establece la lista de elementos del carrito de compras.
     *
     * @param cartItemDtoList La lista de elementos del carrito de compras.
     */
    public void setCartItems(List<CartItemDto> cartItemDtoList) {
        this.cartItems = cartItemDtoList;
    }

    /**
     * Obtiene el costo total del carrito de compras.
     *
     * @return El costo total del carrito de compras.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Establece el costo total del carrito de compras.
     *
     * @param totalCost El costo total del carrito de compras.
     */
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
