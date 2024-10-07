package com.softuni.universities.domain.dto;

import com.softuni.universities.domain.entity.Course;
import com.softuni.universities.domain.entity.University;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UniversityDto {
    private String name;

    private String description;

    private List<CourseDto> courses;
}
