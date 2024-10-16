package com.softuni.client.repository;

import com.softuni.client.domain.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UniversityRepository extends JpaRepository<University,Long> {
    Optional<University> findByName(String name);
    Optional<University> findByUuid(UUID uuid);
}
