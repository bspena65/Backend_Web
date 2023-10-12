package com.ucatolica.ecommerce.dto.cart;

import com.ucatolica.ecommerce.model.Cart;
import com.ucatolica.ecommerce.model.Product;

import javax.validation.constraints.NotNull;

/**
 * DTO que representa un elemento del carrito de compras.
 */
public class CartItemDto {
    private Integer id;
    private @NotNull Integer quantity;
    private @NotNull Product product;

    /**
     * Constructor vacío de la clase `CartItemDto`.
     */
    public CartItemDto() {
    }

    /**
     * Constructor que crea un `CartItemDto` a partir de un objeto `Cart`.
     *
     * @param cart El objeto `Cart` del que se creará el DTO.
     */
    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }

    /**
     * Devuelve una representación de cadena del objeto `CartItemDto`.
     *
     * @return Una cadena que representa el objeto `CartItemDto`.
     */
    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", productName=" + product.getName() +
                '}';
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
     * Obtiene la cantidad del producto en el carrito.
     *
     * @return La cantidad del producto en el carrito.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad del producto en el carrito.
     *
     * @param quantity La cantidad del producto en el carrito.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Obtiene el producto asociado al elemento del carrito.
     *
     * @return El producto asociado al elemento del carrito.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Establece el producto asociado al elemento del carrito.
     *
     * @param product El producto asociado al elemento del carrito.
     */
    public void setProduct(Product product) {
        this.product = product;
    }
}
