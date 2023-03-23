package com.employee.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SprindDocConfiguration {

    @Bean
    public OpenAPI baseOpenAPI() {

        return new OpenAPI().info(new Info().title("Employee Service").version("1.0.0").description("Employee Service is built in Spring Boot 3 with CRUD APIs"));
    }

//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        return new MethodValidationPostProcessor();
//    }
}