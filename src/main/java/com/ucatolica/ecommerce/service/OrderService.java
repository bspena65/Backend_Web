package com.ucatolica.ecommerce.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.ucatolica.ecommerce.dto.cart.CartDto;
import com.ucatolica.ecommerce.dto.cart.CartItemDto;
import com.ucatolica.ecommerce.dto.checkout.CheckoutItemDto;
import com.ucatolica.ecommerce.exceptions.OrderNotFoundException;
import com.ucatolica.ecommerce.model.Order;
import com.ucatolica.ecommerce.model.OrderItem;
import com.ucatolica.ecommerce.model.Product;
import com.ucatolica.ecommerce.model.User;
import com.ucatolica.ecommerce.repository.OrderItemsRepository;
import com.ucatolica.ecommerce.repository.OrderRepository;
import com.ucatolica.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Servicio que gestiona las operaciones relacionadas con los pedidos en el sistema de comercio electrónico,
 * incluyendo la creación de pedidos, la generación de sesiones de pago con Stripe y la recuperación de detalles de pedidos.
 */

@Service
@Transactional
public class OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Autowired
    ProductRepository productRepository;

    @Value("${BASE_URL}")
    private String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;



    /**
     * Crea un objeto PriceData para un producto en la sesión de pago de Stripe.
     *
     * @param checkoutItemDto Los datos del producto en el carrito de compra.
     * @return Un objeto PriceData que representa el precio del producto en la sesión de pago de Stripe.
     */
    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount((long)(checkoutItemDto.getPrice()*100))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getProductName())
                                .build())
                .build();
    }

    /**
     * Crea un objeto LineItem para un producto en la sesión de pago de Stripe.
     *
     * @param checkoutItemDto Los datos del producto en el carrito de compra.
     * @return Un objeto LineItem que representa el producto en la sesión de pago de Stripe.
     */
    SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {

        return SessionCreateParams.LineItem.builder()
                // establecer precio para cada producto
                .setPriceData(createPriceData(checkoutItemDto))
                // establecer cantidad para cada producto
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity())))
                .build();
    }

    /**
     * Crea una sesión de pago en Stripe para procesar una lista de productos en el carrito de compra.
     *
     * @param checkoutItemDtoList La lista de productos en el carrito de compra.
     * @return Un objeto Session que representa la sesión de pago de Stripe.
     * @throws StripeException Si se produce un error al interactuar con Stripe.
     */
    public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {

        // proporcionar URL de éxito y error para stripe
        String successURL = baseURL + "payment/success";
        String failedURL = baseURL + "payment/failed";

        // establece la clave privada
        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<>();

        // para cada producto calcula SessionCreateParams.LineItem
        for (CheckoutItemDto checkoutItemDto : checkoutItemDtoList) {
            sessionItemsList.add(createSessionLineItem(checkoutItemDto));
        }

        // construye el parámetro de sesión
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failedURL)
                .addAllLineItem(sessionItemsList)
                .setSuccessUrl(successURL)
                .build();
        return Session.create(params);
    }
    /**
     * Coloca un pedido en nombre del usuario, guardando los detalles del pedido y eliminando los productos del carrito.
     *
     * @param user     El usuario que realiza el pedido.
     * @param sessionId El ID de la sesión de pago de Stripe asociada al pedido.

     */

    public void placeOrder(User user, String sessionId) {
        // Obtener los artículos del carrito del usuario
        CartDto cartDto = cartService.listCartItems(user);

        // Crear un nuevo pedido
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setSessionId(sessionId);
        newOrder.setUser(user);
        newOrder.setTotalPrice(cartDto.getTotalCost());
        orderRepository.save(newOrder);

        // Procesar cada artículo del carrito
        for (CartItemDto cartItemDto : cartDto.getcartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDto.getProduct().getPrice());
            orderItem.setProduct(cartItemDto.getProduct());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrder(newOrder);
            orderItemsRepository.save(orderItem);

            // Actualizar el inventario del producto
            Product product = cartItemDto.getProduct();
            int newQuantity = product.getQuantity() - cartItemDto.getQuantity();
            product.setQuantity(Math.max(newQuantity, 0)); // Asegurar que la cantidad no sea negativa
            productRepository.save(product);
        }

        // Eliminar los artículos del carrito
        cartService.deleteUserCartItems(user);
    }

    /**
     * Lista los pedidos realizados por un usuario en orden descendente de fecha de creación.
     *
     * @param user El usuario para el que se listarán los pedidos.
     * @return Una lista de objetos Order que representan los pedidos del usuario.
     */
    public List<Order> listOrders(User user) {
        return orderRepository.findAllByUserOrderByCreatedDateDesc(user);
    }


    /**
     * Obtiene los detalles de un pedido por su ID.
     *
     * @param orderId El ID del pedido que se va a buscar.
     * @return El objeto Order que representa el pedido encontrado.
     * @throws OrderNotFoundException Si no se encuentra el pedido con el ID especificado.
     */
    public Order getOrder(Integer orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException("Order not found");
    }
}


