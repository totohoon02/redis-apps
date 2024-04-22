package com.redisapps.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        title = "Redis API 명세서",
        description = "Redis App",
        version = "v1"
))
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Components components = new Components();

        return new OpenAPI()
                // http://localhost:8080/swagger-ui/index.html
                .addServersItem(new Server().url("/"))
                .components(components);
    }
}
