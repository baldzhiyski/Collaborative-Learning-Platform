package com.softuni.client.domain.entity;

import com.softuni.client.domain.entity.enums.ResourceType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resources")
@Getter
@Setter
@NoArgsConstructor
public class Resource  extends BaseEntity{

    private ResourceType resourceType; // Type of resource (e.g., document, video)

    @ManyToOne
    private User uploadedBy;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudyGroup studyGroup;
}
