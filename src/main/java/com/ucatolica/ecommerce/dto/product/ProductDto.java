package com.ucatolica.ecommerce.dto.product;

import com.ucatolica.ecommerce.model.Product;

import javax.validation.constraints.NotNull;

/**
 * DTO que representa un producto.
 */
public class ProductDto {

    private Integer id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer categoryId;

    private @NotNull Integer quantity;


    /**
     * Constructor de la clase `ProductDto` que crea un DTO a partir de un objeto `Product`.
     *
     * @param product El objeto `Product` a partir del cual se crea el DTO.
     */
    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setImageURL(product.getImageURL());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategory().getId());
        this.quantity = product.getQuantity();
    }

    /**
     * Constructor de la clase `ProductDto` que recibe los detalles de un producto.
     *
     * @param name        El nombre del producto.
     * @param imageURL    La URL de la imagen del producto.
     * @param price       El precio del producto.
     * @param description La descripción del producto.
     * @param categoryId  El ID de la categoría a la que pertenece el producto.
     */
    public ProductDto(@NotNull String name, @NotNull String imageURL, @NotNull double price, @NotNull String description, @NotNull Integer categoryId) {
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }

    /**
     * Constructor vacío de la clase `ProductDto`.
     */
    public ProductDto() {
    }

    /**
     * Obtiene el ID del producto.
     *
     * @return El ID del producto.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el ID del producto.
     *
     * @param id El ID del producto.
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
     * Obtiene el ID de la categoría a la que pertenece el producto.
     *
     * @return El ID de la categoría.
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * Establece el ID de la categoría a la que pertenece el producto.
     *
     * @param categoryId El ID de la categoría.
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
