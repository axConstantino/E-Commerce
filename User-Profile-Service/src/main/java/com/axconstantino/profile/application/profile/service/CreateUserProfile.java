package com.axconstantino.profile.application.profile.service;

import com.axconstantino.profile.application.profile.command.CreateProfileCommand;
import com.axconstantino.profile.application.profile.usecase.CreateProfile;
import com.axconstantino.profile.domain.repositories.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserProfile implements CreateProfile {
    private final UserProfileRepository repository;

    @Transactional
    @Override
    public void execute(CreateProfileCommand command) {
        repository.save(command.profile());
    }
}
