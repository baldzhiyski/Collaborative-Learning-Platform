package com.softuni.universities.domain.dto;

import com.softuni.universities.domain.entity.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
    private String name;

    private String description;

    private String degree;

    private String language;
}
