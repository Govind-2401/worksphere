package com.govind.worksphere.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()

                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local Development"),

                        new Server()
                                .url("https://worksphere-production-59a1.up.railway.app")
                                .description("Production Server")
                ))

                .info(new Info()
                        .title("WorkSphere API")
                        .description("Employee Management System REST API with JWT Authentication")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Govind Kumar")
                                .email("govindkumar.gpj@gmail.com")
                                .url("https://github.com/Govind-2401"))
                        .license(new License()
                                .name("Apache 2.0")))

                .addSecurityItem(new SecurityRequirement()
                        .addList(SECURITY_SCHEME_NAME))

                .components(new Components()
                        .addSecuritySchemes(
                                SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        ));
    }
}