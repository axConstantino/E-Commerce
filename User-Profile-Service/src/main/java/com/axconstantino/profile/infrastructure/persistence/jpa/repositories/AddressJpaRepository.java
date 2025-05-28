package com.axconstantino.profile.infrastructure.persistence.jpa.repositories;

import com.axconstantino.profile.infrastructure.persistence.jpa.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, UUID> {
    List<AddressEntity> findAllByKeycloakId(String  keycloakId);
    void deleteByIdAndUserProfileId(UUID id, UUID userProfileId);

    @Query("SELECT a FROM AddressEntity a WHERE a.id = :id AND a.userProfile.id = :userProfileId")
    Optional<AddressEntity> findByIdAndUserProfileId(@Param("id") UUID id, @Param("userProfileId") UUID userProfileId);
}
