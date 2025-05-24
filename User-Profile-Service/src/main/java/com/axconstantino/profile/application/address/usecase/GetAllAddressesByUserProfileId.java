package com.axconstantino.profile.application.address.usecase;

import com.axconstantino.profile.domain.entities.Address;

import java.util.List;
import java.util.UUID;

public interface GetAllAddressesByUserProfileId {
    List<Address> execute(UUID userProfileId);
}
