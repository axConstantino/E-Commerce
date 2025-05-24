package com.axconstantino.profile.application.profile.service;

import com.axconstantino.profile.application.profile.usecase.GetProfileByKeycloakId;
import com.axconstantino.profile.domain.entities.UserProfile;
import com.axconstantino.profile.domain.repositories.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetProfileByKeycloak implements GetProfileByKeycloakId {
    private final UserProfileRepository repository;

    @Transactional
    @Override
    public UserProfile execute(String keycloakId) {
        return repository.findByKeycloakId(keycloakId);
    }
}
