package com.ucatolica.ecommerce.controller;

import com.ucatolica.ecommerce.common.ApiResponse;
import com.ucatolica.ecommerce.model.Category;
import com.ucatolica.ecommerce.service.CategoryService;
import com.ucatolica.ecommerce.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlador que gestiona las operaciones relacionadas con categorías.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * Obtiene la lista de todas las categorías.
	 *
	 * @return Una respuesta con la lista de categorías.
	 */
	@GetMapping("/")
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> body = categoryService.listCategories();
		return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
	}

	/**
	 * Crea una nueva categoría.
	 *
	 * @param category La categoría a crear.
	 * @return Una respuesta que indica si la categoría se ha creado con éxito.
	 */
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
		if (Helper.notNull(categoryService.readCategory(category.getCategoryName()))) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
		}
		categoryService.createCategory(category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
	}

	/**
	 * Actualiza una categoría existente por su ID.
	 *
	 * @param categoryID El ID de la categoría a actualizar.
	 * @param category   Los nuevos datos de la categoría.
	 * @return Una respuesta que indica si la categoría se ha actualizado con éxito.
	 */
	@PostMapping("/update/{categoryID}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID, @Valid @RequestBody Category category) {
		// Comprueba si la categoría existe.
		if (Helper.notNull(categoryService.readCategory(categoryID))) {
			// Si la categoría existe, actualízala.
			categoryService.updateCategory(categoryID, category);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
		}

		// Si la categoría no existe, devuelve una respuesta sin éxito.
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
	}
}
