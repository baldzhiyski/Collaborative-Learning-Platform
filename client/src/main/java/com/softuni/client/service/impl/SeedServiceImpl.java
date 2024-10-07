package com.softuni.client.service.impl;

import com.softuni.client.domain.dto.course.CourseDto;
import com.softuni.client.domain.dto.university.UniversityDto;
import com.softuni.client.repository.CourseRepository;
import com.softuni.client.repository.UniversityRepository;
import com.softuni.client.service.SeedService;
import com.softuni.client.service.UniversityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private UniversityRepository universityRepository;
    private UniversityService universityService;

    private CourseRepository courseRepository;

    public SeedServiceImpl(UniversityRepository universityRepository, UniversityService universityService, CourseRepository courseRepository) {
        this.universityRepository = universityRepository;
        this.universityService = universityService;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public void seedUniversitiesAndCourses() {
        if(this.universityRepository.count()==0){

            List<UniversityDto> allUniversities = this.universityService.getAllUniversities();

            allUniversities.stream()
                    .map(UniversityDto::toEntity)
                    .forEach(university -> {
                        this.universityRepository.saveAndFlush(university);
                        this.courseRepository.saveAllAndFlush(university.getCourses());
                    });
        }
    }
}
