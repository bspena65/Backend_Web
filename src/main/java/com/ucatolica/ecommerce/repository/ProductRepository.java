package com.ucatolica.ecommerce.repository;

import com.ucatolica.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de datos para la entidad Product en el sistema de comercio electrónico.
 * Proporciona métodos para realizar operaciones de acceso a datos relacionadas con los productos.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Esta interfaz hereda los métodos CRUD estándar proporcionados por JpaRepository.
    // No se requieren métodos personalizados ya que JpaRepository maneja operaciones comunes de acceso a datos.
}
