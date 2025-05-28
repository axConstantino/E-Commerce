package com.axconstantino.profile.application.address.service;

import com.axconstantino.profile.application.address.usecase.GetAddressForUser;
import com.axconstantino.profile.domain.entities.Address;
import com.axconstantino.profile.domain.repositories.AddressRepository;
import com.axconstantino.profile.domain.repositories.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetSpecificAddress implements GetAddressForUser {
    private final AddressRepository addressRepository;
    private final UserProfileRepository userProfileRepository;

    @Transactional(readOnly = true)
    @Override
    public Address execute(UUID id, String userId) {
        var userProfile = userProfileRepository.findByKeycloakId(userId);
        return addressRepository.findByIdAndUserProfileId(id, userProfile.getId());
    }
}
