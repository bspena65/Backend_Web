package com.ucatolica.ecommerce.service;

import com.ucatolica.ecommerce.model.WishList;
import com.ucatolica.ecommerce.repository.WishListRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Servicio que gestiona las operaciones relacionadas con la lista de deseos en el sistema de comercio electrónico.
 * Proporciona métodos para crear una lista de deseos y leer la lista de deseos de un usuario.
 */
@Service
@Transactional
public class WishListService {

    private final WishListRepository wishListRepository;

    /**
     * Constructor del servicio WishListService.
     *
     * @param wishListRepository Repositorio de datos de la lista de deseos.
     */
    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    /**
     * Crea una lista de deseos en el sistema.
     *
     * @param wishList La lista de deseos que se va a crear.
     */
    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    /**
     * Lee la lista de deseos de un usuario y la devuelve en orden descendente por fecha de creación.
     *
     * @param userId El identificador del usuario cuya lista de deseos se va a leer.
     * @return Una lista de elementos de la lista de deseos del usuario en orden descendente por fecha de creación.
     */
    public List<WishList> readWishList(Integer userId) {
        return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
    }
}
