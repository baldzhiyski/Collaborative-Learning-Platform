package com.softuni.universities.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UniversityDto {
    private String name;

    private String description;

    private List<CourseDto> courses;

}
