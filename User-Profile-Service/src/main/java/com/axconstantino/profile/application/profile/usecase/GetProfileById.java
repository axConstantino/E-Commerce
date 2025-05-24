package com.axconstantino.profile.application.profile.usecase;

import com.axconstantino.profile.domain.entities.UserProfile;

import java.util.UUID;

public interface GetProfileById {
    UserProfile execute(UUID profileId);
}
