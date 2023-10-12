package com.ucatolica.ecommerce.controller;

import com.ucatolica.ecommerce.common.ApiResponse;
import com.ucatolica.ecommerce.dto.product.ProductDto;
import com.ucatolica.ecommerce.model.Product;
import com.ucatolica.ecommerce.model.User;
import com.ucatolica.ecommerce.model.WishList;
import com.ucatolica.ecommerce.service.AuthenticationService;
import com.ucatolica.ecommerce.service.ProductService;
import com.ucatolica.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador que gestiona las operaciones relacionadas con la lista de deseos (Wish List).
 */
@RestController
@RequestMapping("/wishlist")
public class WishListController {

        @Autowired
        private WishListService wishListService;

        @Autowired
        private AuthenticationService authenticationService;

        /**
         * Obtiene la lista de productos en la lista de deseos de un usuario.
         *
         * @param token El token de autenticación del usuario.
         * @return Una respuesta con la lista de productos en la lista de deseos.
         */
        @GetMapping("/{token}")
        public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {
                int user_id = authenticationService.getUser(token).getId();
                List<WishList> body = wishListService.readWishList(user_id);
                List<ProductDto> products = new ArrayList<ProductDto>();
                for (WishList wishList : body) {
                        products.add(ProductService.getDtoFromProduct(wishList.getProduct()));
                }

                return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
        }

        /**
         * Agrega un producto a la lista de deseos de un usuario.
         *
         * @param product El producto a agregar a la lista de deseos.
         * @param token   El token de autenticación del usuario.
         * @return Una respuesta que indica si se ha agregado el producto a la lista de deseos con éxito.
         */
        @PostMapping("/add")
        public ResponseEntity<ApiResponse> addWishList(@RequestBody Product product, @RequestParam("token") String token) {
                authenticationService.authenticate(token);
                User user = authenticationService.getUser(token);
                WishList wishList = new WishList(user, product);
                wishListService.createWishlist(wishList);
                return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);
        }
}
