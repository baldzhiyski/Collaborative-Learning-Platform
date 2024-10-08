package com.softuni.client.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "blog-posts")
@Getter
@Setter
@NoArgsConstructor
public class BlogPost extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Course course;

    @OneToMany(mappedBy = "blogPost")
    private List<Comment> comments;

    @Column
    private UUID uuid;
}

