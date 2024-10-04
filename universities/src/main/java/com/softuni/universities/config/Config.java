package com.softuni.universities.config;

import com.softuni.universities.repository.UniversityRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class Config {
    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();

        openAPI.setInfo(
                new Info()
                        .description("This is a universities  micro service for helping the crossfit community.")
                        .title("universities API")
                        .version("0.0.1")
                        .contact(
                                new Contact()
                                        .name("Baldzhiyski")
                                        .email("e-platform-stuttgart@gmail.com")
                        )
        );

        return openAPI;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource,
                                                       UniversityRepository universityRepository,
                                                       ResourceLoader resourceLoader) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        if (universityRepository.count() == 0) {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(resourceLoader.getResource("classpath:data.sql"));
            initializer.setDatabasePopulator(populator);
        }

        return initializer;
    }


    @Bean
    public ModelMapper modelMapper() {
       return  new ModelMapper();
    }
}
