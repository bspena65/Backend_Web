package com.ucatolica.ecommerce.repository;


import com.ucatolica.ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de datos para la entidad WishList en el sistema de comercio electrónico.
 * Proporciona métodos para realizar operaciones de acceso a datos relacionadas con las listas de deseos de los usuarios.
 */
@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

    /**
     * Busca y recupera todas las listas de deseos de un usuario específico ordenadas por fecha de creación descendente.
     *
     * @param userId El identificador del usuario cuyas listas de deseos se desean recuperar.
     * @return Una lista de todas las listas de deseos del usuario especificado, ordenadas por fecha de creación descendente.
     */
    List<WishList> findAllByUserIdOrderByCreatedDateDesc(Integer userId);
}
