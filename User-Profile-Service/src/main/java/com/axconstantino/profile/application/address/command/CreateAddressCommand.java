package com.axconstantino.profile.application.address.command;

import com.axconstantino.profile.domain.entities.Address;

public record CreateAddressCommand(String userKeycloakId, Address newAddress) {
    public CreateAddressCommand {
        if (newAddress == null) {
            throw new IllegalArgumentException("New address cannot be null");
        }
    }
}
