package com.softuni.universities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class UniversitiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversitiesApplication.class, args);
    }

}
