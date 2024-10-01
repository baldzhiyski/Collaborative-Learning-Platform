package com.softuni.client.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "google.recaptcha")
public class ReCaptchaConfig {

    private String site;
    private String secret;

    public ReCaptchaConfig setSite(String site) {
        this.site = site;
        return this;
    }

    public ReCaptchaConfig setSecret(String secret) {
        this.secret = secret;
        return this;
    }
}