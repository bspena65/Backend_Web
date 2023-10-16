package com.ucatolica.ecommerce.model;

import jakarta.persistence.*;

//import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Clase que representa una categoría de productos en el sistema de comercio electrónico.
 */
@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // Identificador único de la categoría.

	@Column(name = "category_name")
	@NotBlank
	private String categoryName; // Nombre de la categoría.

	@NotBlank
	private String description; // Descripción de la categoría.

	private String imageUrl; // URL de la imagen de la categoría.

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Product> products; // Conjunto de productos asociados a esta categoría.

	/**
	 * Crea una nueva instancia de la clase `Category` con el nombre y la descripción especificados.
	 *
	 * @param categoryName Nombre de la categoría.
	 * @param description  Descripción de la categoría.
	 */
	public Category(@NotBlank String categoryName, @NotBlank String description) {
		this.categoryName = categoryName;
		this.description = description;
	}

	/**
	 * Crea una nueva instancia de la clase `Category` con el nombre, la descripción y la URL de la imagen especificados.
	 *
	 * @param categoryName Nombre de la categoría.
	 * @param description  Descripción de la categoría.
	 * @param imageUrl     URL de la imagen de la categoría.
	 */
	public Category(@NotBlank String categoryName, @NotBlank String description, String imageUrl) {
		this.categoryName = categoryName;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public Category() {
		// Constructor sin argumentos
	}


	/**
	 * Obtiene el identificador único de la categoría.
	 *
	 * @return El identificador único de la categoría.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Establece el identificador único de la categoría.
	 *
	 * @param id El identificador único de la categoría.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre de la categoría.
	 *
	 * @return El nombre de la categoría.
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Establece el nombre de la categoría.
	 *
	 * @param categoryName El nombre de la categoría.
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * Obtiene la descripción de la categoría.
	 *
	 * @return La descripción de la categoría.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Establece la descripción de la categoría.
	 *
	 * @param description La descripción de la categoría.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obtiene la URL de la imagen de la categoría.
	 *
	 * @return La URL de la imagen de la categoría.
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Establece la URL de la imagen de la categoría.
	 *
	 * @param imageUrl La URL de la imagen de la categoría.
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * Obtiene el conjunto de productos asociados a esta categoría.
	 *
	 * @return El conjunto de productos asociados a esta categoría.
	 */
	public Set<Product> getProducts() {
		return products;
	}

	/**
	 * Establece el conjunto de productos asociados a esta categoría.
	 *
	 * @param products El conjunto de productos asociados a esta categoría.
	 */
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category {id=" + id + ", categoryName='" + categoryName + "', description='" + description + "'}";
	}
}
