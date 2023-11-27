package com.ucatolica.ecommerce.service;

import com.ucatolica.ecommerce.dto.cart.AddToCartDto;
import com.ucatolica.ecommerce.dto.cart.CartDto;
import com.ucatolica.ecommerce.dto.cart.CartItemDto;
import com.ucatolica.ecommerce.exceptions.CartItemNotExistException;
import com.ucatolica.ecommerce.model.*;
import com.ucatolica.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servicio encargado de gestionar el carrito de compras en el sistema de comercio electrónico.
 * Proporciona métodos para agregar, listar, actualizar y eliminar elementos del carrito de compras.
 */
@Service
@Transactional
public class CartService {

    @Autowired
    private  CartRepository cartRepository;


    /**
     * Constructor de la clase que recibe una instancia de CartRepository.
     *
     * @param cartRepository Repositorio de carrito utilizado para acceder a la base de datos.
     */
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    /**
     * Agrega un producto al carrito de compras de un usuario.
     *
     * @param addToCartDto DTO que contiene la información del producto a agregar.
     * @param product      El producto que se va a agregar al carrito.
     * @param user         El usuario al que se le agregará el producto.
     */
    public void addToCart(AddToCartDto addToCartDto, Product product, User user){
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }

    /**
     * Obtiene los elementos del carrito de compras de un usuario.
     *
     * @param user El usuario cuyo carrito de compras se va a listar.
     * @return DTO que contiene los elementos del carrito y el costo total.
     */
    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }

    /**
     * Obtiene un DTO a partir de un elemento de carrito.
     *
     * @param cart El elemento de carrito del que se va a crear el DTO.
     * @return DTO que representa el elemento de carrito.
     */
    public static CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }


    /**
     * Actualiza la cantidad de un producto en el carrito de compras de un usuario.
     *
     * @param cartDto DTO que contiene la información actualizada del producto en el carrito.
     * @param user    El usuario cuyo carrito de compras se va a actualizar.
     * @param product El producto que se va a actualizar en el carrito.
     */
    public void updateCartItem(AddToCartDto cartDto, User user,Product product){
        Cart cart = cartRepository.getOne(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    /**
     * Elimina un elemento del carrito de compras por su ID.
     *
     * @param id     El ID del elemento de carrito que se va a eliminar.
     * @param userId El ID del usuario al que pertenece el carrito de compras.
     * @throws CartItemNotExistException Si el elemento de carrito no existe.
     */
    public void deleteCartItem(int id,int userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);

    }

    /**
     * Elimina todos los elementos del carrito de compras de un usuario.
     *
     * @param userId El ID del usuario cuyo carrito de compras se va a vaciar.
     */
    public void deleteCartItems(int userId) {
        cartRepository.deleteAll();
    }


    /**
     * Elimina todos los elementos del carrito de compras de un usuario específico.
     *
     * @param user El usuario cuyo carrito de compras se va a vaciar.
     */
    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }
}


