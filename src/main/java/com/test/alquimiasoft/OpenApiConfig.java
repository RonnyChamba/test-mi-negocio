package com.test.alquimiasoft;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {


    @Value("${openapi.dev-url}")
    private String devUrl;


    @Bean
    public OpenAPI openApi() {

        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("ronnychamba96@gmail.com");
        contact.setName("Ronny Chamba");
        contact.setUrl("https://www.linkedin.com/in/ronny-chamba-8273911b7/");
        License mitLicense = new License().name("MIT License").url("https://examplealicense.com/licenses/mit/");


        Info info = new Info()
                .title("Example Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage customers and subsidiary.").termsOfService("https://www.example.com/terms")
                .license(mitLicense);
        return new OpenAPI().info(info).servers(List.of(devServer));

    }
}
