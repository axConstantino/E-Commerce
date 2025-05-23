package com.axconstantino.profile.infrastructure.persistence.adapter;

import com.axconstantino.profile.domain.entities.UserProfile;
import com.axconstantino.profile.domain.repositories.UserProfileRepository;
import com.axconstantino.profile.exception.NotFoundException;
import com.axconstantino.profile.infrastructure.mapper.UserProfileJpaMapper;
import com.axconstantino.profile.infrastructure.persistence.jpa.repositories.UserProfileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserProfileRepositoryImpl implements UserProfileRepository {
    private final UserProfileJpaRepository userProfileJpa;
    private final UserProfileJpaMapper mapper;

    @Override
    public void save(UserProfile userProfile) {
        var userProfileEntity = mapper.toEntity(userProfile);
        userProfileJpa.save(userProfileEntity);
    }

    @Override
    public UserProfile findById(UUID id) {
        var userProfileEntity = userProfileJpa.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user profile with id: " + id));
        return mapper.toDomain(userProfileEntity);
    }

    @Override
    public UserProfile findByKeycloakId(String keycloakId) {
        var userProfileEntity = userProfileJpa.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new NotFoundException("Not found user profile with keycloak id: " + keycloakId));
        return mapper.toDomain(userProfileEntity);
    }

    @Override
    public boolean existsByKeycloakId(String keycloakId) {
        return userProfileJpa.existsByKeycloakId(keycloakId);
    }

    @Override
    public void deleteByKeycloakId(String keycloakId) {
        userProfileJpa.deleteByKeycloakId(keycloakId);
    }

    @Override
    public void update(UUID existingProfileId, UserProfile userProfile) {
        var userProfileEntity = userProfileJpa.findById(existingProfileId)
                .orElseThrow(() -> new NotFoundException("Not found user profile with id: " + existingProfileId));
        mapper.updateEntity(userProfileEntity, userProfile);
    }

    @Override
    public void delete(UUID id) {
        userProfileJpa.deleteById(id);
    }
}
