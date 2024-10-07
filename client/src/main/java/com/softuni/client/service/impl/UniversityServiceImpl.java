package com.softuni.client.service.impl;

import com.softuni.client.config.UniversityConfig;
import com.softuni.client.domain.dto.course.CourseDto;
import com.softuni.client.domain.dto.university.UniversityDto;
import com.softuni.client.service.UniversityService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    private RestClient restClient;

    private UniversityConfig universityConfig;

    public UniversityServiceImpl(@Qualifier("universitiesRestClient") RestClient restClient, UniversityConfig universityConfig) {
        this.restClient = restClient;
        this.universityConfig = universityConfig;
    }

    @Override
    public List<UniversityDto> getAllUniversities() {
        return restClient.get()
                .uri(this.universityConfig.getAllUniversities())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<UniversityDto>>() {
                });
    }

    @Override
    public void publishUniversity(UniversityDto addUniDto) {
        this.restClient.post()
                .uri(this.universityConfig.getPublishUrl())
                .body(addUniDto)
                .retrieve();

    }

    @Override
    public UniversityDto findByName(String universityName) {
        return this.restClient.get()
                .uri(this.universityConfig.getByNameUrl(),universityName)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(UniversityDto.class);

    }
}
