package com.softuni.client.repository;

import com.softuni.client.domain.entity.UserActivationLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserActivationLinkRepository extends JpaRepository<UserActivationLinkEntity,Long> {
    Optional<UserActivationLinkEntity> findByActivationCode(String activationCode);
}
