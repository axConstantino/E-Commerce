package com.axconstantino.profile.application.address.usecase;

import java.util.UUID;

public interface SetDefaultAddress {
    void execute(String userKeycloakId, UUID addressId);
}
