package br.com.caiobruno.restspring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("RESTFULL API with Java 21 and Spring Boot ")
                                .version("v1")
                                .description("API construída com o fim de aprimorar minhas habilidades.")
                                .termsOfService("https://www.linkedin.com/in/caio-bruno-elias-263249234/")
                                .license(new License()
                                        .name("Apache 2.0")
                                        .url("https://www.apache.org/licenses/LICENSE-2.0"))
                                .contact(new Contact()
                                        .name("Caio Bruno E F de Oliveira")
                                        .url("https://www.linkedin.com/in/caio-bruno-elias-263249234/")
                                        .email("caiobruno90@yahoo.com"))
                );
    }


}
