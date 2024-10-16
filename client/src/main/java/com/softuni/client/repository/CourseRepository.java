package com.softuni.client.repository;

import com.softuni.client.domain.entity.Course;
import com.softuni.client.domain.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByUuid(UUID uuid);
}
