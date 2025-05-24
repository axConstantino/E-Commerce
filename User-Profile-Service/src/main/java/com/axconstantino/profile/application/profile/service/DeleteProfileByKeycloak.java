package com.axconstantino.profile.application.profile.service;

import com.axconstantino.profile.application.profile.usecase.DeleteProfileByKeycloakId;
import com.axconstantino.profile.domain.repositories.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteProfileByKeycloak implements DeleteProfileByKeycloakId {
    private final UserProfileRepository repository;

    @Transactional
    @Override
    public void execute(String keycloakId) {
        repository.deleteByKeycloakId(keycloakId);
    }
}
