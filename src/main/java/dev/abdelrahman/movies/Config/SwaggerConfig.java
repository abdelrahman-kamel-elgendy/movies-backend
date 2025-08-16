package dev.abdelrahman.movies.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
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
                        .email("abdelrahman.kamel.elgendy@gmail.com")))
                    .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                    .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth",
                            new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
} 