package com.axconstantino.profile.infrastructure.persistence.jpa.repositories;

import com.axconstantino.profile.infrastructure.persistence.jpa.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, UUID> {
    AddressEntity findByIdAndUserProfileId(UUID id, UUID userProfileId);

    void deleteByIdAndUserProfileId(UUID id, UUID userProfileId);
}
