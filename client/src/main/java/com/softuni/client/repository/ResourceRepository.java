package com.softuni.client.repository;

import com.softuni.client.domain.entity.Resource;
import com.softuni.client.domain.entity.enums.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource,Long> {
    Optional<Resource> findByResourceType(ResourceType resourceType);
}
