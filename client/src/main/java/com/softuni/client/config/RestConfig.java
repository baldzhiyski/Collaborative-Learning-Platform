package com.softuni.client.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @ConditionalOnProperty(value="rest.configuration", havingValue="prod")
    @Bean("universitiesRestClient")
    public RestClient eventsRestClient(UniversityConfig universityConfig) {
        return RestClient
                .builder()
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
