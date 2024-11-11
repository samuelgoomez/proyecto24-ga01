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
                                .title("API de Usuario")
                                .description("Este API REST se encarga de gestionar la información y operaciones relacionadas con los usuarios dentro del microservicio de Usuarios del sistema de Netflix. Proporciona diversos endpoints para manejar el ciclo de vida completo de los usuarios, incluyendo la creación de cuentas, autenticación, actualización de perfiles, y la gestión de preferencias y configuraciones de cada usuario. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los datos de usuario, asegurando que la información esté siempre segura y actualizada. Esta API facilita la interacción entre el frontend y el backend del sistema, garantizando que los usuarios puedan gestionar fácilmente sus cuentas y preferencias, mejorando la experiencia personalizada y segura dentro de la plataforma. ")
                                .version("1.0")
                )
        ;
    }
}