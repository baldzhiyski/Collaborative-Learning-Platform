package com.softuni.universities.domain.entity;

import com.softuni.universities.domain.dto.CourseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course  extends BaseEntity{

    @ManyToOne
    private University university;

    private String name;

    private String description;

    private String degree;

    private String language;

    public static CourseDto toDto(Course course){
        CourseDto courseDto = new CourseDto();
        courseDto.setDegree(course.getDegree());
        courseDto.setName(course.getName());
        courseDto.setDescription(course.getDescription());
        courseDto.setLanguage(course.getLanguage());

        return courseDto;
    }
}
