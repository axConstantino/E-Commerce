package com.axconstantino.profile.application.profile.usecase;

import com.axconstantino.profile.application.profile.command.CreateProfileCommand;

public interface CreateProfile {
    void execute(CreateProfileCommand command);
}
