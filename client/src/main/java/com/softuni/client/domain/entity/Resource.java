package com.softuni.client.domain.entity;

import com.softuni.client.domain.entity.enums.ResourceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "resources")
@Getter
@Setter
@NoArgsConstructor
public class Resource  extends BaseEntity{

    @Column
    private UUID uuid;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType; // Type of resource (e.g., document, video)

    @ManyToOne
    private User uploadedBy;

    @Column(columnDefinition = "TEXT")
    private String youtubeUrlPath;

    @Column
    private String pathToFile;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
