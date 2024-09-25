package com.softuni.client.repository;

import com.softuni.client.domain.entity.FeedBackComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBackComment,Long> {
}
