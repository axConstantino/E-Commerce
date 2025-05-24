package com.axconstantino.profile.application.profile.usecase;

import com.axconstantino.profile.domain.entities.UserProfile;

public interface GetProfileByKeycloakId {
    UserProfile execute(String keycloakId);
}
