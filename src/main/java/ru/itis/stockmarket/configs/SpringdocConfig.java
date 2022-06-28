package ru.itis.stockmarket.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Date: 17.05.2022
 * Time: 11:30 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@Configuration
public class SpringdocConfig {
    @Bean
    public OpenAPI centralStockApi() {
        return new OpenAPI()
                .info(new Info().title("Central Stock API")
                        .description("API documentation for Central Stock API"))
                .addServersItem(new Server().url("http://188.93.211.195/central").description("Hosted server"))
                .addServersItem(new Server().url("http://localhost:8091/central").description("Local server"));
    }
}
