package com.axconstantino.profile.application.address.usecase;

import com.axconstantino.profile.domain.entities.Address;

import java.util.List;

public interface GetAllAddressesByUserProfileId {
    List<Address> execute(String userId);
}
