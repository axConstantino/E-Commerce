package com.axconstantino.profile.application.address.service;

import com.axconstantino.profile.application.address.usecase.GetAllAddressesByUserProfileId;
import com.axconstantino.profile.domain.entities.Address;
import com.axconstantino.profile.domain.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAllAddresses implements GetAllAddressesByUserProfileId {
    private final AddressRepository addressRepository;

    @Override
    public List<Address> execute(UUID userProfileId) {
        return addressRepository.findAllByUserProfileId(userProfileId);
    }
}
