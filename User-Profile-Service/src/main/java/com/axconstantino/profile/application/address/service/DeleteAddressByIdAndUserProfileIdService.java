package com.axconstantino.profile.application.address.service;

import com.axconstantino.profile.application.address.usecase.DeleteByAddressIdAndUserProfileId;
import com.axconstantino.profile.domain.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteAddressByIdAndUserProfileIdService implements DeleteByAddressIdAndUserProfileId {
    private final AddressRepository addressRepository;

    @Transactional
    @Override
    public void execute(UUID addressId, UUID userProfileId) {
        addressRepository.deleteByIdAndUserProfileId(addressId, userProfileId);
    }
}
