package com.axconstantino.profile.application.address.usecase;

import com.axconstantino.profile.domain.entities.Address;

public interface GetDefaultAddressById {

    Address execute(String userKeycloakId);
}
