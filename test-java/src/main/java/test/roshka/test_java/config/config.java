package test.roshka.test_java.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.*;

@Configuration
public class config {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info().title("API de Noticias de ABC")
                                            .description("Ejercicio de Apificación")
                                            .version("1.0"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("noticias")
                .pathsToMatch("/consulta/**")
                .build();
    }
}
