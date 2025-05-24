package com.axconstantino.profile.application.address.service;

import com.axconstantino.profile.application.address.usecase.GetAddressByIdAndUserProfileId;
import com.axconstantino.profile.domain.entities.Address;
import com.axconstantino.profile.domain.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetSpecificAddress implements GetAddressByIdAndUserProfileId {
    private final AddressRepository addressRepository;

    @Transactional
    @Override
    public Address execute(UUID id, UUID userProfileId) {
        return addressRepository.findByIdAndUserProfileId(id, userProfileId);
    }
}
