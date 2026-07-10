package com.govind.worksphere.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("WorkSphere Employee Management System API")
                        .version("1.0")
                        .description("REST APIs for Employee & Department Management with JWT Authentication")
                        .contact(new Contact()
                                .name("Govind Kumar")
                                .email("govindkumar.gpj@gmail.com")
                                .url("www.https://github.com/Govind-2401")
                                .url("www.linkedin.com/in/govind-kumar-486555368"))
                        .license(new License()
                                .name("Apache 2.0")));
    }
}