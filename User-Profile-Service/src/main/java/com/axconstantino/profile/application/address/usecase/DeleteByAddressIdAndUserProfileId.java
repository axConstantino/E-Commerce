package com.axconstantino.profile.application.address.usecase;

import java.util.UUID;

public interface DeleteByAddressIdAndUserProfileId {
    void execute(UUID addressId, UUID userProfileId);
}
