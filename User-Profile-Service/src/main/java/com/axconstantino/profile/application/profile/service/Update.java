package com.axconstantino.profile.application.profile.service;

import com.axconstantino.profile.application.profile.command.UpdateProfileCommand;
import com.axconstantino.profile.application.profile.usecase.UpdateProfile;
import com.axconstantino.profile.domain.repositories.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class Update implements UpdateProfile {
    private final UserProfileRepository repository;

    @Transactional
    @Override
    public void execute(UpdateProfileCommand command) {
        repository.update(command.existingProfileId(), command.updatedProfile());
    }
}
