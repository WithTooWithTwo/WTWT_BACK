package wtwt.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String AUTHENTICATION_SCHEME = "Access Token";

    @Bean
    public OpenAPI openAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(
            AUTHENTICATION_SCHEME);
        Components components = new Components().addSecuritySchemes(AUTHENTICATION_SCHEME,
            createAPIKeyScheme());

        return new OpenAPI()
            .info(apiInfo())
            .addSecurityItem(securityRequirement)
            .components(components);
    }

    private Info apiInfo() {
        return new Info()
            .title("WTWT API ✈\uFE0F")
            .description("윗투윗투 API 명세서")
            .version("v1");
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer")
            .in(SecurityScheme.In.HEADER)
            .name("Authorization");
    }
}

