package com.example.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com")
public class AppConfig {
    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }
}
