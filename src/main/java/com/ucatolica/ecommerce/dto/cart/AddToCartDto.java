package com.ucatolica.ecommerce.dto.cart;

import javax.validation.constraints.NotNull;

/**
 * DTO que representa la solicitud para agregar un producto al carrito de compras.
 */
public class AddToCartDto {
    private Integer id;
    private @NotNull Integer productId;
    private @NotNull Integer quantity;

    /**
     * Constructor vacío de la clase `AddToCartDto`.
     */
    public AddToCartDto() {
    }

    /**
     * Devuelve una representación de cadena del objeto `AddToCartDto`.
     *
     * @return Una cadena que representa el objeto `AddToCartDto`.
     */
    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ",";
    }

    /**
     * Obtiene el ID del elemento del carrito.
     *
     * @return El ID del elemento del carrito.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el ID del elemento del carrito.
     *
     * @param id El ID del elemento del carrito.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del producto que se va a agregar al carrito.
     *
     * @return El ID del producto.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Establece el ID del producto que se va a agregar al carrito.
     *
     * @param productId El ID del producto.
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * Obtiene la cantidad del producto que se va a agregar al carrito.
     *
     * @return La cantidad del producto.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad del producto que se va a agregar al carrito.
     *
     * @param quantity La cantidad del producto.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
