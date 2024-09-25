package com.softuni.client.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "feedback_comments")
@Getter
@Setter
@NoArgsConstructor
public class FeedBackComment extends BaseEntity {

    @Column
    private String opinion;

    @Column
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
