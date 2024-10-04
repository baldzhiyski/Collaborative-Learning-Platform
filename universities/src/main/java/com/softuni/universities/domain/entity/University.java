package com.softuni.universities.domain.entity;

import com.softuni.universities.domain.dto.CourseDto;
import com.softuni.universities.domain.dto.UniversityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "universities")
@Getter
@Setter
public class University extends BaseEntity{
    private String name;

    private String description;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Course> courses;


    public static UniversityDto toDto(University university){
        // Map courses if they exist (null-safety)
        List<CourseDto> courseDtos = university.getCourses() != null
                ? university.getCourses().stream()
                .map(Course::toDto)
                .toList()
                : List.of(); // Return empty list if no courses

        UniversityDto universityDto = new UniversityDto();
        universityDto.setCourses(courseDtos);
        universityDto.setName(university.getName());
        universityDto.setDescription(university.getDescription());

        return universityDto;
    }
}
