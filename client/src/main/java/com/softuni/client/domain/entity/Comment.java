package com.softuni.client.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment  extends BaseEntity{

    @Column
    private String content; // Content of the comment

    @ManyToOne
    @JoinColumn(name = "post_id")
    private BlogPost blogPost; // The post to which the comment belongs

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author; // User who wrote the comment

    @Column
    private UUID uuid;

    @Column
    private LocalDateTime createdAt; // Date and time of comment creation

}
