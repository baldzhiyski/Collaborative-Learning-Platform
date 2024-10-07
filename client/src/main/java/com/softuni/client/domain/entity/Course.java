package com.softuni.client.domain.entity;

import com.softuni.client.domain.dto.course.CourseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course extends BaseEntity {
    @Column
    private String name; // Name of the study group
    @Column
    private String description; // Brief description of the group

    @Column
    private UUID uuid;

    @Column
    private String degree;
    @Column
    private String language;

    @OneToMany(mappedBy = "course")
    private List<Resource> resources; // Resources associated with the group

    @OneToMany(mappedBy = "course")
    private List<BlogPost> blogPosts; // Blog posts associated with the group

    @ManyToMany
    private Set<User> participants;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    public static CourseDto toDto(Course course){
        CourseDto courseDto = new CourseDto();
        courseDto.setUuid(course.getUuid());
        courseDto.setDegree(course.getDegree());
        courseDto.setName(course.getName());
        courseDto.setDescription(course.getDescription());
        courseDto.setLanguage(course.getLanguage());

        return courseDto;
    }
}
