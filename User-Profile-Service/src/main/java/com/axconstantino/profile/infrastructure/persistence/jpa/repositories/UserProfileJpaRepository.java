package com.axconstantino.profile.infrastructure.persistence.jpa.repositories;

import com.axconstantino.profile.infrastructure.persistence.jpa.entities.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserProfileJpaRepository extends JpaRepository<UserProfileEntity, UUID> {
    UserProfileEntity findByKeycloakId(String keycloakId);

    void deleteByKeycloakId(String keycloakId);

    boolean existsByKeycloakId(String keycloakId);
}
