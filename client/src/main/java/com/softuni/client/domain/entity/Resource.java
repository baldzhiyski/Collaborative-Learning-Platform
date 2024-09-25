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
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType; // Type of resource (e.g., document, video)

    @ManyToOne
    private User uploadedBy;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Course course;
}
