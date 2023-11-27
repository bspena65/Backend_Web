package com.ucatolica.ecommerce.model;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Representa una lista de deseos de un usuario que contiene productos.
 */
@Entity
@Table(name = "wishlist")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    public WishList() {
    }

    /**
     * Crea una nueva instancia de `WishList` asociada a un usuario y un producto.
     *
     * @param user    El usuario al que pertenece la lista de deseos.
     * @param product El producto que se añadirá a la lista de deseos.
     */
    public WishList(User user, Product product) {
        this.user = user;
        this.product = product;
        this.createdDate = new Date();
    }

    /**
     * Obtiene el identificador único de la lista de deseos.
     *
     * @return El identificador único de la lista de deseos.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único de la lista de deseos.
     *
     * @param id El identificador único de la lista de deseos.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el usuario al que pertenece la lista de deseos.
     *
     * @return El usuario al que pertenece la lista de deseos.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario al que pertenece la lista de deseos.
     *
     * @param user El usuario al que pertenece la lista de deseos.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Obtiene la fecha de creación de la lista de deseos.
     *
     * @return La fecha de creación de la lista de deseos.
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Establece la fecha de creación de la lista de deseos.
     *
     * @param createdDate La fecha de creación de la lista de deseos.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Obtiene el producto asociado a la lista de deseos.
     *
     * @return El producto asociado a la lista de deseos.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Establece el producto asociado a la lista de deseos.
     *
     * @param product El producto asociado a la lista de deseos.
     */
    public void setProduct(Product product) {
        this.product = product;
    }
}
