package org.adaschool.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "REST API",
                version = "1.0.0",
                description = "crud for users and products"
        )
)
public class OpenApiConfig {

}
