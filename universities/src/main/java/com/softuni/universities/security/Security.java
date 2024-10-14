package com.softuni.universities.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class Security {
    public final CustomCrosConfig customCorsConfiguration;

    public Security(CustomCrosConfig customCorsConfiguration) {
        this.customCorsConfiguration = customCorsConfiguration;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(c -> c.configurationSource(customCorsConfiguration))
                .authorizeHttpRequests(
                        authorize ->
                                authorize.requestMatchers(HttpMethod.GET, "/universities/**","/swagger-ui/**", "swagger-ui.html", "/v3/api-docs/**").permitAll()
                                        .requestMatchers(HttpMethod.POST,"/universities/publish").permitAll()
                                        .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()

                )
                .build();

    }
}
