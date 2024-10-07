package com.softuni.client.domain.dto.course;

import com.softuni.client.domain.dto.university.UniversityDto;
import com.softuni.client.domain.entity.Course;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CourseDto {
    private UUID uuid;
    private String name;

    private String description;

    private String degree;

    private String language;

    public static Course fromDto(CourseDto courseDto){
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setDegree(courseDto.getDegree());
        course.setLanguage(courseDto.getLanguage());
        course.setDescription(courseDto.getDescription());
        course.setUuid((UUID.randomUUID()));
        return course;
    }
}
