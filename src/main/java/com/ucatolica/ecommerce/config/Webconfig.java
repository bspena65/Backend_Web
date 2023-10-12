package com.ucatolica.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de configuración que habilita la configuración de CORS (Cross-Origin Resource Sharing)
 * en la aplicación Spring.
 */
@Configuration
class Webconfig {

    /**
     * Configura CORS (Cross-Origin Resource Sharing) para permitir solicitudes desde cualquier origen
     * y los métodos HTTP especificados.
     *
     * @return Un objeto WebMvcConfigurer configurado para manejar CORS.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
            }
        };
    }
}
