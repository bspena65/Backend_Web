package com.ucatolica.ecommerce.service;

import com.ucatolica.ecommerce.dto.product.ProductDto;
import com.ucatolica.ecommerce.exceptions.ProductNotExistException;
import com.ucatolica.ecommerce.model.Category;
import com.ucatolica.ecommerce.model.Product;
import com.ucatolica.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio que gestiona las operaciones relacionadas con los productos en el sistema de comercio electrónico.
 * Proporciona métodos para listar productos, agregar productos, actualizar productos y obtener detalles de un producto.
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     * Obtiene una lista de productos disponibles en el sistema.
     *
     * @return Una lista de objetos ProductDto que representan los productos.
     */
    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto productDto = getDtoFromProduct(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    /**
     * Convierte un objeto Product en un objeto ProductDto.
     *
     * @param product El producto a convertir.
     * @return Un objeto ProductDto que representa el producto.
     */
    public static ProductDto getDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }

    /**
     * Convierte un objeto ProductDto en un objeto Product, asignándole una categoría.
     *
     * @param productDto El objeto ProductDto que contiene los datos del producto.
     * @param category   La categoría a la que pertenece el producto.
     * @return Un objeto Product que representa el producto con la categoría asignada.
     */
    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        product.setQuantity(productDto.getQuantity());
        return product;
    }

    /**
     * Agrega un nuevo producto al sistema.
     *
     * @param productDto Los datos del producto a agregar.
     * @param category   La categoría a la que pertenece el producto.
     */
    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    /**
     * Actualiza un producto existente en el sistema.
     *
     * @param productID  El ID del producto que se va a actualizar.
     * @param productDto Los datos actualizados del producto.
     * @param category   La categoría a la que pertenece el producto actualizado.
     */
    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        product.setId(productID);
        productRepository.save(product);
    }


    /**
     * Obtiene los detalles de un producto por su ID.
     *
     * @param productId El ID del producto que se va a buscar.
     * @return El objeto Product que representa el producto encontrado.
     * @throws ProductNotExistException Si no se encuentra el producto con el ID especificado.
     */
    public Product getProductById(Integer productId) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotExistException("Product id is invalid " + productId);
        return optionalProduct.get();
    }


}
