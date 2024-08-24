package com.marzz.maintenance_management_service.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI maintenanceServicePlatformOpenApi() {
        var openApi = new OpenAPI();

        openApi
                .info(new Info()
                        .title("Maintenance Service Platform API")
                        .description("Maintenance Service Platform application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Maintenance Service Platform wiki Documentation")
                        .url("https://github.com"));

        return openApi;
    }
}
