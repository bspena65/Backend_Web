package com.ucatolica.ecommerce;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Ecommercce.
 * Esta clase inicia la aplicación Spring Boot y define la configuración básica.
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Documentation Ecommerce", version = "1.0"))
public class EcommerceApplication {

    /**
     * Método principal para iniciar la aplicación Ecommercce.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

}
