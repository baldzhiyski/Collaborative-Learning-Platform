package com.softuni.client.domain.entity;

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

    @OneToMany(mappedBy = "course")
    private List<Resource> resources; // Resources associated with the group

    @OneToMany(mappedBy = "course")
    private List<BlogPost> blogPosts; // Blog posts associated with the group

    @ManyToMany
    private Set<User> participants;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;
}
