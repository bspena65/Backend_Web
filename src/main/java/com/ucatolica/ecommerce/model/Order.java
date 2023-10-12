package com.ucatolica.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Representa un pedido en el sistema de comercio electrónico.
 */
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "session_id")
    private String sessionId;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /**
     * Crea una nueva instancia de `Order`.
     */
    public Order() {
    }

    /**
     * Obtiene la lista de elementos de pedido (order items) asociados a este pedido.
     *
     * @return La lista de elementos de pedido asociados a este pedido.
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * Establece la lista de elementos de pedido (order items) asociados a este pedido.
     *
     * @param orderItems La lista de elementos de pedido asociados a este pedido.
     */
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * Obtiene el identificador único de este pedido.
     *
     * @return El identificador único de este pedido.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único de este pedido.
     *
     * @param id El identificador único de este pedido.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha de creación de este pedido.
     *
     * @return La fecha de creación de este pedido.
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Establece la fecha de creación de este pedido.
     *
     * @param createdDate La fecha de creación de este pedido.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Obtiene el precio total de este pedido.
     *
     * @return El precio total de este pedido.
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Establece el precio total de este pedido.
     *
     * @param totalPrice El precio total de este pedido.
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Obtiene el ID de la sesión asociada a este pedido.
     *
     * @return El ID de la sesión asociada a este pedido.
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Establece el ID de la sesión asociada a este pedido.
     *
     * @param sessionId El ID de la sesión asociada a este pedido.
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Obtiene el usuario que realizó este pedido.
     *
     * @return El usuario que realizó este pedido.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario que realizó este pedido.
     *
     * @param user El usuario que realizó este pedido.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
