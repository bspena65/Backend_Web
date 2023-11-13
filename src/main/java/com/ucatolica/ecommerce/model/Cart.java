package com.ucatolica.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

//import javax.persistence.*;
import java.util.Date;

/**
 * Clase que representa el carrito de compras de un usuario en el sistema de comercio electrónico.
 */
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Identificador único del carrito.

    @Column(name = "created_date")
    private Date createdDate; // Fecha de creación del carrito.

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product; // Producto agregado al carrito.

    @JsonIgnore
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user; // Usuario propietario del carrito.

    private int quantity; // Cantidad del producto en el carrito.

    /**
     * Crea una nueva instancia de la clase `Cart` con el producto, la cantidad y el usuario especificados.
     *
     * @param product  Producto agregado al carrito.
     * @param quantity Cantidad del producto en el carrito.
     * @param user     Usuario propietario del carrito.
     */
    public Cart(Product product, int quantity, User user) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.createdDate = new Date();
    }

    public Cart() {

    }

    /**
     * Obtiene el identificador único del carrito.
     *
     * @return El identificador único del carrito.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del carrito.
     *
     * @param id El identificador único del carrito.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el usuario propietario del carrito.
     *
     * @return El usuario propietario del carrito.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario propietario del carrito.
     *
     * @param user El usuario propietario del carrito.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Obtiene la fecha de creación del carrito.
     *
     * @return La fecha de creación del carrito.
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Establece la fecha de creación del carrito.
     *
     * @param createdDate La fecha de creación del carrito.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Obtiene el producto agregado al carrito.
     *
     * @return El producto agregado al carrito.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Establece el producto agregado al carrito.
     *
     * @param product El producto agregado al carrito.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Obtiene la cantidad del producto en el carrito.
     *
     * @return La cantidad del producto en el carrito.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad del producto en el carrito.
     *
     * @param quantity La cantidad del producto en el carrito.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
