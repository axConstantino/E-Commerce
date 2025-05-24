package com.axconstantino.profile.application.profile.usecase;

import com.axconstantino.profile.application.profile.command.UpdateProfileCommand;

public interface UpdateProfile {
    void execute(UpdateProfileCommand command);
}
