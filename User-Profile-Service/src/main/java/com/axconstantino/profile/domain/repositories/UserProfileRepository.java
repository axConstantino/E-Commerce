package com.axconstantino.profile.domain.repositories;

import com.axconstantino.profile.domain.entities.UserProfile;

import java.util.UUID;

public interface UserProfileRepository {
    void save(UserProfile userProfile);
    UserProfile findById(UUID id);
    UserProfile findByKeycloakId(String keycloakId);
    void update(UserProfile userProfile);
    void delete(UUID id);
}
