package com.axconstantino.profile.application.profile.service;

import com.axconstantino.profile.application.profile.usecase.DeleteProfile;
import com.axconstantino.profile.domain.repositories.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteUserProfile implements DeleteProfile {
    private final UserProfileRepository repository;

    @Transactional
    @Override
    public void execute(UUID profileId) {
        repository.delete(profileId);
    }
}
