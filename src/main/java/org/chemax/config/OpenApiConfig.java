package org.chemax.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Social Media Api",
                description = "Social Media", version = "1.0.0",
                contact = @Contact(
                        name = "Vorobyov Maxim",
                        email = "chemax05@mail.ru"
                )
        )
)
public class OpenApiConfig {
}
