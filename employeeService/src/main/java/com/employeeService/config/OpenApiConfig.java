package com.employeeService.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee API Specification",
                description = "Api documentation for Employee Service",
                version = "1.0"
        ),
        servers = @Server(
                url = "http://localhost:9002",
                description = "Local ENV"
        )
)
public class OpenApiConfig {
}
