package co.edu.usbcali.santiagoweb.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "santiagoweb",
                version = "2.5.0",
                description = "This is a Crud for santiagoweb"
        )
)

public class SwaggerConfig {
}
