package com.axconstantino.profile.application.profile.usecase;

import java.util.UUID;

public interface DeleteProfile {
    void execute(UUID profileId);
}
