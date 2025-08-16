package dev.abdelrahman.movies.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI moviesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Movies Reviews API")
                        .description("A RESTful API for managing movies and reviews")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Abdelrahman")
                                .email("abdelrahman.kamel.elgendy@gmail.com")));
    }
} 