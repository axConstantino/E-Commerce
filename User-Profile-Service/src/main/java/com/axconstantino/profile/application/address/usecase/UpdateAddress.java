package com.axconstantino.profile.application.address.usecase;

import com.axconstantino.profile.application.address.command.UpdateAddressCommand;


public interface UpdateAddress {
    void execute(UpdateAddressCommand command);
}
