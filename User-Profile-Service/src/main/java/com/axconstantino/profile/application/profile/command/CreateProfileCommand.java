package com.axconstantino.profile.application.profile.command;

import com.axconstantino.profile.domain.entities.UserProfile;

public record CreateProfileCommand(String keycloakId, UserProfile profile) {
}
