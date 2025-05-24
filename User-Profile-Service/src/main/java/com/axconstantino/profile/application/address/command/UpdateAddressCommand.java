package com.axconstantino.profile.application.address.command;

import com.axconstantino.profile.domain.entities.Address;

import java.util.UUID;

public record UpdateAddressCommand(UUID existingAddressId, Address updatedAddress) {
    public UpdateAddressCommand {
        if (existingAddressId == null) {
            throw new IllegalArgumentException("Address ID cannot be null");
        }
        if (updatedAddress == null) {
            throw new IllegalArgumentException("Updated address cannot be null");
        }
    }
}
