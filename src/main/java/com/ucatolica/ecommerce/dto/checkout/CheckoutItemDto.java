package com.ucatolica.ecommerce.dto.checkout;

/**
 * DTO que representa un elemento del carrito de compras en el proceso de checkout.
 */
public class CheckoutItemDto {

    private String productName;
    private int  quantity;
    private double price;
    private long productId;
    private int userId;

    /**
     * Constructor vacío de la clase `CheckoutItemDto`.
     */
    public CheckoutItemDto() {}

    /**
     * Constructor de la clase `CheckoutItemDto` que recibe los datos del elemento del carrito de compras en el proceso de checkout.
     *
     * @param productName El nombre del producto.
     * @param quantity La cantidad del producto.
     * @param price El precio del producto.
     * @param productId El ID del producto.
     * @param userId El ID del usuario.
     */
    public CheckoutItemDto(String productName, int quantity, double price, long productId, int userId) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.userId = userId;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param productName El nombre del producto.
     */
    public void setProductName(String productName) {
        this.productName = productName;
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
     * Obtiene el ID del usuario.
     *
     * @return El ID del usuario.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param userId El ID del usuario.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Obtiene el ID del producto.
     *
     * @return El ID del producto.
     */
    public long getProductId() {
        return productId;
    }

    /**
     * Establece el ID del producto.
     *
     * @param id El ID del producto.
     */
    public void setProductId(long id) {
        this.productId = id;
    }
}
