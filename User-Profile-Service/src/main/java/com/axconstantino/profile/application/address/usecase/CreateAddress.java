package com.axconstantino.profile.application.address.usecase;

import com.axconstantino.profile.application.address.command.CreateAddressCommand;

public interface CreateAddress {
    void execute(CreateAddressCommand command);
}
