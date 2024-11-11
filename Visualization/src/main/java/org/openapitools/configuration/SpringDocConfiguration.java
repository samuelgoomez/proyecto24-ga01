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
                                .title("API de Visualizaciones y Recomendaciones")
                                .description("Este API REST se encarga de gestionar la información relacionada con las visualizaciones y las recomendaciones dentro del microservicio de Visualizaciones y Recomendaciones del sistema de Netflix. Proporciona diversos endpoints para rastrear el historial de visualización de los usuarios, analizar patrones de comportamiento y ofrecer recomendaciones personalizadas basadas en sus preferencias y actividades. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los datos de visualización y preferencias, asegurando que las recomendaciones sean precisas y se adapten a los gustos de cada usuario. Esta API actúa como un puente entre el sistema de análisis y el frontend, garantizando que los datos sobre el consumo de contenido se mantengan actualizados y que las recomendaciones se presenten en tiempo real para mejorar la experiencia del usuario en la plataforma.")
                                .contact(
                                        new Contact()
                                                .name("Equipo de Desarrollo")
                                                .email("desarrollo@ejemplo.com")
                                )
                                .version("1.0.0")
                )
        ;
    }
}