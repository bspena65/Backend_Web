package com.ucatolica.ecommerce.repository;

import com.ucatolica.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de datos para la entidad Category en el sistema de comercio electrónico.
 * Proporciona métodos para realizar operaciones de acceso a datos relacionadas con las categorías de productos.
 */
@Repository
public interface Categoryrepository extends JpaRepository<Category, Integer> {

	/**
	 * Busca una categoría por su nombre.
	 *
	 * @param categoryName El nombre de la categoría a buscar.
	 * @return La categoría encontrada o null si no se encuentra ninguna coincidencia.
	 */
	Category findByCategoryName(String categoryName);
}
