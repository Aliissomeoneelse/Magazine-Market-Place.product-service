package com.company.productservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(
                title = "Market Place",
                description = "Fuking shit",
                contact = @Contact(
                        name = "Yunusov Behzod",
                        url = "https://drive.google.com/file/d/1CB9br0jPPMQwT49YUOc0CZ83ZEf9b2TO/view?usp=drive_link",
                        email = "beka99@gmail.com"
                )
        ),
        tags = { @Tag(
                name = "Product"
        ),  @Tag(
                name = "Product base"
        )}

)
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
