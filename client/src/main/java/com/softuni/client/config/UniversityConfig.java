package com.softuni.client.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "universities.api")
@Getter
@Setter
public class UniversityConfig {

    private String allUniversities;
    private String publishUrl;
    private String byNameUrl;
}
