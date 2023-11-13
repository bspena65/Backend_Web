package com.ucatolica.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ucatolica.ecommerce.dto.product.ProductDto;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Representa un producto en el sistema de comercio electrónico.
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;

    private @NotNull Integer quantity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<WishList> wishListList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Cart> carts;

    /**
     * Crea una nueva instancia de `Product` a partir de un objeto `ProductDto` y una categoría.
     *
     * @param productDto El objeto `ProductDto` que contiene los datos del producto.
     * @param category   La categoría a la que pertenece el producto.
     */
    public Product(ProductDto productDto, Category category) {
        this.name = productDto.getName();
        this.imageURL = productDto.getImageURL();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.category = category;
        this.quantity = getQuantity();

    }

    /**
     * Crea una nueva instancia de `Product` con los datos proporcionados.
     *
     * @param name        El nombre del producto.
     * @param imageURL    La URL de la imagen del producto.
     * @param price       El precio del producto.
     * @param description La descripción del producto.
     * @param category    La categoría a la que pertenece el producto.
     */
    public Product(String name, String imageURL, double price, String description, Category category, Integer quantity) {
        super();
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
    }

    /**
     * Crea una nueva instancia de `Product`.
     */
    public Product() {
    }

    /**
     * Obtiene el identificador único del producto.
     *
     * @return El identificador único del producto.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     *
     * @param id El identificador único del producto.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param name El nombre del producto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la URL de la imagen del producto.
     *
     * @return La URL de la imagen del producto.
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Establece la URL de la imagen del producto.
     *
     * @param imageURL La URL de la imagen del producto.
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
     * Obtiene la descripción del producto.
     *
     * @return La descripción del producto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del producto.
     *
     * @param description La descripción del producto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene la categoría a la que pertenece el producto.
     *
     * @return La categoría del producto.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Establece la categoría a la que pertenece el producto.
     *
     * @param category La categoría del producto.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
