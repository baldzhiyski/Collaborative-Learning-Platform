package com.softuni.client.domain.dto.university;

import com.softuni.client.domain.dto.course.CourseDto;
import com.softuni.client.domain.entity.Course;
import com.softuni.client.domain.entity.University;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public class UniversityDto {

    private String name;

    private String description;

    private List<CourseDto> courses;

    public static University toEntity(UniversityDto universityDto){
        University university = new University();
        university.setName(universityDto.getName());
        university.setDescription(universityDto.getDescription());
        university.setUuid(UUID.randomUUID());

        List<Course> courses = universityDto.getCourses()
                .stream()
                .map(CourseDto::fromDto)
                .collect(Collectors.toList());

        university.setCourses(courses);
        courses.forEach(course -> course.setUniversity(university));

        return university;
    }
}
