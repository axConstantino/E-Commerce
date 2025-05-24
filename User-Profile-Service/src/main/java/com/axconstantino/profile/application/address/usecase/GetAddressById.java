package com.axconstantino.profile.application.address.usecase;

import com.axconstantino.profile.domain.entities.Address;

import java.util.UUID;

public interface GetAddressById {
    Address execute(UUID id);
}
