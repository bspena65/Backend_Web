package com.ucatolica.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Representa un elemento de un pedido (order item) en el sistema de comercio electrónico.
 */
@Entity
@Table(name = "orderitems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity")
    private @NotNull int quantity;

    @Column(name = "price")
    private @NotNull double price;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    /**
     * Crea una nueva instancia de `OrderItem` asociada a un pedido, un producto, una cantidad y un precio.
     *
     * @param order    El pedido al que pertenece este elemento.
     * @param product  El producto que se incluye en este elemento del pedido.
     * @param quantity La cantidad de productos incluidos en este elemento.
     * @param price    El precio unitario del producto en este elemento.
     */
    public OrderItem(Order order, @NotNull Product product, @NotNull int quantity, @NotNull double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.createdDate = new Date();
    }

    /**
     * Crea una nueva instancia de `OrderItem`.
     */
    public OrderItem() {
    }

    /**
     * Obtiene el producto incluido en este elemento del pedido.
     *
     * @return El producto incluido en este elemento del pedido.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Establece el producto incluido en este elemento del pedido.
     *
     * @param product El producto incluido en este elemento del pedido.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Obtiene la cantidad de productos incluidos en este elemento del pedido.
     *
     * @return La cantidad de productos incluidos en este elemento del pedido.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad de productos incluidos en este elemento del pedido.
     *
     * @param quantity La cantidad de productos incluidos en este elemento del pedido.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Obtiene el precio unitario del producto en este elemento del pedido.
     *
     * @return El precio unitario del producto en este elemento del pedido.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Establece el precio unitario del producto en este elemento del pedido.
     *
     * @param price El precio unitario del producto en este elemento del pedido.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Obtiene la fecha de creación de este elemento del pedido.
     *
     * @return La fecha de creación de este elemento del pedido.
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Establece la fecha de creación de este elemento del pedido.
     *
     * @param createdDate La fecha de creación de este elemento del pedido.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Obtiene el pedido al que pertenece este elemento.
     *
     * @return El pedido al que pertenece este elemento.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Establece el pedido al que pertenece este elemento.
     *
     * @param order El pedido al que pertenece este elemento.
     */
    public void setOrder(Order order) {
        this.order = order;
    }
}
