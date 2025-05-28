package com.axconstantino.profile.application.profile.usecase;

import com.axconstantino.profile.domain.entities.UserProfile;

public interface GetProfileById {
    UserProfile execute(String keycloakId);
}
