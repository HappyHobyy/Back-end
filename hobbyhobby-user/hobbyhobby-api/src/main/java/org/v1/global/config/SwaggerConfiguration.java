package org.v1.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfiguration {
    private static final String API_NAME = "HobbyHobby API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "HobbyHobby API 명세서";

    @Bean
    public OpenAPI openAPI(final ServletContext servletContext) {
        String contextPath = servletContext.getContextPath();
        Server server = new Server().url(contextPath);

        // Security 스키마 설정
        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("Authorization")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        // Security 요청 설정
        SecurityRequirement addSecurityItem = new SecurityRequirement();
        addSecurityItem.addList("Authorization");

        return new OpenAPI()
                // Security 인증 컴포넌트 설정
                .components(new Components().addSecuritySchemes("Authorization", bearerAuth))
                // API 마다 Security 인증 컴포넌트 설정
                .addSecurityItem(addSecurityItem)
                .addServersItem(new Server().url("http://52.79.143.36:8000/user-service"))
                .info(swaggerInfo());
    }

    private Info swaggerInfo() {
        return new Info()
                .version(API_VERSION)
                .title(API_NAME)
                .description(API_DESCRIPTION);
    }
}
