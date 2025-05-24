package com.axconstantino.profile.application.address.usecase;

import java.util.UUID;

public interface DeleteAddress {
    void execute(UUID addressId);
}
