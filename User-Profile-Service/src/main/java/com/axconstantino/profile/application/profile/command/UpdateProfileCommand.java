package com.axconstantino.profile.application.profile.command;

import com.axconstantino.profile.domain.entities.UserProfile;

import java.util.UUID;

public record UpdateProfileCommand(UUID existingProfileId, UserProfile updatedProfile) {
    public UpdateProfileCommand {
        if (existingProfileId == null || updatedProfile == null) {
            throw new IllegalArgumentException("Existing profile ID and updated profile cannot be null");
        }
    }
}
