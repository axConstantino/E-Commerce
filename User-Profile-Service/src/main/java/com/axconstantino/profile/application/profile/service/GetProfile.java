package com.axconstantino.profile.application.profile.service;

import com.axconstantino.profile.application.profile.usecase.GetProfileById;
import com.axconstantino.profile.domain.entities.UserProfile;
import com.axconstantino.profile.domain.repositories.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetProfile implements GetProfileById {
    private final UserProfileRepository repository;

    @Transactional
    @Override
    public UserProfile execute(UUID profileId) {
        return repository.findById(profileId);
    }
}
