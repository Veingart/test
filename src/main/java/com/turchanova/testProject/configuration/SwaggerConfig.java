package com.turchanova.testProject.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Test project")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("arrrxyz@gmail.com")
                                                .name("Turchanova Anastasia")
                                )
                );
    }

}
