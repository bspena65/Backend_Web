package com.ucatolica.ecommerce.controller;

import com.ucatolica.ecommerce.common.ApiResponse;
import com.ucatolica.ecommerce.dto.cart.AddToCartDto;
import com.ucatolica.ecommerce.dto.cart.CartDto;
import com.ucatolica.ecommerce.exceptions.AuthenticationFailException;
import com.ucatolica.ecommerce.exceptions.CartItemNotExistException;
import com.ucatolica.ecommerce.exceptions.ProductNotExistException;
import com.ucatolica.ecommerce.model.*;
import com.ucatolica.ecommerce.service.AuthenticationService;
import com.ucatolica.ecommerce.service.CartService;
import com.ucatolica.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controlador que gestiona las operaciones relacionadas con el carrito de compras.
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Agrega un producto al carrito de compras.
     *
     * @param addToCartDto Los datos para agregar un producto al carrito.
     * @param token        El token de autenticación del usuario que realiza la solicitud.
     * @return Una respuesta que indica si el producto se ha agregado al carrito con éxito.
     * @throws AuthenticationFailException Si la autenticación falla.
     * @throws ProductNotExistException   Si el producto no existe.
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) throws AuthenticationFailException, ProductNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productService.getProductById(addToCartDto.getProductId());
        cartService.addToCart(addToCartDto, product, user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    /**
     * Obtiene los elementos del carrito de compras.
     *
     * @param token El token de autenticación del usuario que realiza la solicitud.
     * @return Una respuesta que contiene los elementos del carrito de compras.
     * @throws AuthenticationFailException Si la autenticación falla.
     */
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
    }

    /**
     * Actualiza un elemento del carrito de compras.
     *
     * @param cartDto Los nuevos datos para el elemento del carrito.
     * @param token   El token de autenticación del usuario que realiza la solicitud.
     * @return Una respuesta que indica si el elemento del carrito se ha actualizado con éxito.
     * @throws AuthenticationFailException Si la autenticación falla.
     * @throws ProductNotExistException   Si el producto no existe.
     */
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
                                                      @RequestParam("token") String token) throws AuthenticationFailException, ProductNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productService.getProductById(cartDto.getProductId());
        cartService.updateCartItem(cartDto, user, product);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    /**
     * Elimina un elemento del carrito de compras.
     *
     * @param itemID El ID del elemento del carrito a eliminar.
     * @param token  El token de autenticación del usuario que realiza la solicitud.
     * @return Una respuesta que indica si el elemento del carrito se ha eliminado con éxito.
     * @throws AuthenticationFailException   Si la autenticación falla.
     * @throws CartItemNotExistException     Si el elemento del carrito no existe.
     */
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID, @RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
        authenticationService.authenticate(token);
        int userId = authenticationService.getUser(token).getId();
        cartService.deleteCartItem(itemID, userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }
}
