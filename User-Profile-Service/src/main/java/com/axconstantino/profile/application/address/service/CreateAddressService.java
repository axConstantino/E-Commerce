package com.axconstantino.profile.application.address.service;

import com.axconstantino.profile.application.address.command.CreateAddressCommand;
import com.axconstantino.profile.application.address.usecase.CreateAddress;
import com.axconstantino.profile.domain.entities.Address;
import com.axconstantino.profile.domain.entities.UserProfile;
import com.axconstantino.profile.domain.repositories.AddressRepository;
import com.axconstantino.profile.domain.repositories.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateAddressService implements CreateAddress {
    private final AddressRepository addressRepository;
    private final UserProfileRepository userProfileRepository;

    @Transactional
    @Override
    public void execute(CreateAddressCommand command) {
        UserProfile userProfile = userProfileRepository.findByKeycloakId(command.userKeycloakId());

        Address address = command.newAddress();
        address.setUserProfile(userProfile);
        addressRepository.save(address);
    }
}