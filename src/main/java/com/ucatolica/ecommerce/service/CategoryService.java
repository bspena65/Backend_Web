package com.ucatolica.ecommerce.service;

import com.ucatolica.ecommerce.model.Category;
import com.ucatolica.ecommerce.repository.Categoryrepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar las categorías en el sistema de comercio electrónico.
 * Proporciona métodos para listar, crear, leer, actualizar y eliminar categorías.
 */
@Service
@Transactional
public class CategoryService {

	private final Categoryrepository categoryrepository;

	/**
	 * Constructor de la clase que recibe una instancia de Categoryrepository.
	 *
	 * @param categoryrepository Repositorio de categorías utilizado para acceder a la base de datos.
	 */
	public CategoryService(Categoryrepository categoryrepository) {
		this.categoryrepository = categoryrepository;
	}

	/**
	 * Obtiene la lista de todas las categorías en el sistema.
	 *
	 * @return Lista de categorías.
	 */
	public List<Category> listCategories() {
		return categoryrepository.findAll();
	}

	/**
	 * Crea una nueva categoría en el sistema.
	 *
	 * @param category La categoría que se va a crear.
	 */
	public void createCategory(Category category) {
		categoryrepository.save(category);
	}

	/**
	 * Lee una categoría por su nombre.
	 *
	 * @param categoryName El nombre de la categoría que se va a buscar.
	 * @return La categoría encontrada o null si no se encuentra.
	 */
	public Category readCategory(String categoryName) {
		return categoryrepository.findByCategoryName(categoryName);
	}

	/**
	 * Lee una categoría por su ID.
	 *
	 * @param categoryId El ID de la categoría que se va a buscar.
	 * @return La categoría encontrada o un Optional vacío si no se encuentra.
	 */
	public Optional<Category> readCategory(Integer categoryId) {
		return categoryrepository.findById(categoryId);
	}

	/**
	 * Actualiza una categoría existente en el sistema.
	 *
	 * @param categoryID  El ID de la categoría que se va a actualizar.
	 * @param newCategory La nueva información de la categoría.
	 */
	public void updateCategory(Integer categoryID, Category newCategory) {
		Category category = categoryrepository.findById(categoryID).get();
		category.setCategoryName(newCategory.getCategoryName());
		category.setDescription(newCategory.getDescription());
		category.setProducts(newCategory.getProducts());
		category.setImageUrl(newCategory.getImageUrl());

		categoryrepository.save(category);
	}
}
