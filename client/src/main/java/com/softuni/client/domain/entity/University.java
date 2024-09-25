package com.softuni.client.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "study-groups")
@Getter
@Setter
@NoArgsConstructor
public class University extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Column
    private UUID uuid;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Course> courses;

}
