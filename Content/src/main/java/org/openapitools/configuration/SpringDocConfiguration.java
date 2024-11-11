package org.openapitools.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {

    @Bean(name = "org.openapitools.configuration.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("API de Contenido")
                                .description("Esta API REST se encarga de manejar la información de la base de datos del microservicio de Contenidos del servicio de Netflix. Proporciona varios endpoints para gestionar películas, series y actores, permitiendo realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los recursos relacionados. Esta API facilita la interacción entre el frontend y el backend del sistema, asegurando que los datos sobre el catálogo de contenido estén siempre actualizados y accesibles para los usuarios y administradores del servicio.")
                                .version("1.0")
                )
        ;
    }
}