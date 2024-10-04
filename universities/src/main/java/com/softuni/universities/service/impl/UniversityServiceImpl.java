package com.softuni.universities.service.impl;

import com.softuni.universities.domain.dto.UniversityDto;
import com.softuni.universities.domain.entity.Course;
import com.softuni.universities.domain.entity.University;
import com.softuni.universities.exception.ObjectNotFoundException;
import com.softuni.universities.repository.CourseRepository;
import com.softuni.universities.repository.UniversityRepository;
import com.softuni.universities.service.UniversityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniversityServiceImpl implements UniversityService {
    private UniversityRepository universityRepository;

    private CourseRepository courseRepository;

    private ModelMapper mapper;

    public UniversityServiceImpl(UniversityRepository universityRepository, CourseRepository courseRepository, ModelMapper mapper) {
        this.universityRepository = universityRepository;
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void createUniversity(UniversityDto universityDto) {
        // Map courses from DTO
        List<Course> courses = universityDto.getCourses() != null
                ? universityDto.getCourses().stream()
                .map(courseDto -> mapper.map(courseDto, Course.class))
                .collect(Collectors.toList())
                : List.of(); // Handle null course list

        // Check if the university already exists
        Optional<University> existing = this.universityRepository.getUniversitiesByName(universityDto.getName());

        if (existing.isEmpty()) {
            University university = new University();
            university.setName(universityDto.getName());
            university.setDescription(universityDto.getDescription());

            // Save university first to get its ID
            this.universityRepository.saveAndFlush(university);

            if (!courses.isEmpty()) {
                courses.forEach(course -> course.setUniversity(university)); // Link courses to university
                university.setCourses(courses);
                this.courseRepository.saveAllAndFlush(courses);
            }
        }
    }

    @Override
    public List<UniversityDto> findAllUniversities() {
        return this.universityRepository.findAll().stream()
                .map(University::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UniversityDto getUniversityByName(String name) {
        University university = this.universityRepository.getUniversitiesByName(name).orElseThrow(() -> new ObjectNotFoundException("No such university !"));
        return University.toDto(university);
    }
}
