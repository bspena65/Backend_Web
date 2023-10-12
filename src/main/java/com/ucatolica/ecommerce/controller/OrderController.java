package com.ucatolica.ecommerce.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.ucatolica.ecommerce.common.ApiResponse;
import com.ucatolica.ecommerce.dto.checkout.CheckoutItemDto;
import com.ucatolica.ecommerce.dto.checkout.StripeResponse;
import com.ucatolica.ecommerce.exceptions.AuthenticationFailException;
import com.ucatolica.ecommerce.exceptions.OrderNotFoundException;
import com.ucatolica.ecommerce.model.Order;
import com.ucatolica.ecommerce.model.User;
import com.ucatolica.ecommerce.service.AuthenticationService;
import com.ucatolica.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que gestiona las operaciones relacionadas con pedidos.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Crea una sesión de Stripe para el proceso de pago.
     *
     * @param checkoutItemDtoList La lista de elementos de compra.
     * @return Una respuesta con el ID de la sesión de Stripe.
     * @throws StripeException Si ocurre una excepción relacionada con Stripe.
     */
    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        // Crear la sesión de Stripe
        Session session = orderService.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        // Enviar el ID de la sesión de Stripe en la respuesta
        return new ResponseEntity<StripeResponse>(stripeResponse, HttpStatus.OK);
    }

    /**
     * Realiza un pedido después del proceso de pago.
     *
     * @param token     El token de autenticación del usuario que realiza la solicitud.
     * @param sessionId El ID de la sesión de Stripe.
     * @return Una respuesta que indica si se ha realizado el pedido con éxito.
     * @throws AuthenticationFailException Si la autenticación falla.
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId)
            throws AuthenticationFailException {
        // Validar el token
        authenticationService.authenticate(token);
        // Recuperar al usuario
        User user = authenticationService.getUser(token);
        // Realizar el pedido
        orderService.placeOrder(user, sessionId);
        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }

    /**
     * Obtiene todos los pedidos de un usuario.
     *
     * @param token El token de autenticación del usuario que realiza la solicitud.
     * @return Una lista de todos los pedidos del usuario.
     * @throws AuthenticationFailException Si la autenticación falla.
     */
    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("token") String token) throws AuthenticationFailException {
        // Validar el token
        authenticationService.authenticate(token);
        // Recuperar al usuario
        User user = authenticationService.getUser(token);
        // Obtener los pedidos
        List<Order> orderDtoList = orderService.listOrders(user);

        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    /**
     * Obtiene los detalles de un pedido por su ID.
     *
     * @param id    El ID del pedido.
     * @param token El token de autenticación del usuario que realiza la solicitud.
     * @return Una respuesta con los detalles del pedido o un mensaje de error si el pedido no se encuentra.
     * @throws AuthenticationFailException Si la autenticación falla.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable("id") Integer id, @RequestParam("token") String token)
            throws AuthenticationFailException {
        // Validar el token
        authenticationService.authenticate(token);
        try {
            Order order = orderService.getOrder(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
