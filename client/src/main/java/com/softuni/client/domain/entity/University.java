package com.softuni.client.domain.entity;

import com.softuni.client.domain.dto.course.CourseDto;
import com.softuni.client.domain.dto.university.UniversityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "universities")
@Getter
@Setter
@NoArgsConstructor
public class University extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Column
    private String description;
    @Column
    private UUID uuid;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
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
