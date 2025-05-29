package com.axconstantino.profile.application.address.service;

import com.axconstantino.profile.application.address.usecase.GetDefaultAddressById;
import com.axconstantino.profile.domain.entities.Address;
import com.axconstantino.profile.domain.repositories.AddressRepository;
import com.axconstantino.profile.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetDefaultAddress implements GetDefaultAddressById {
    private final AddressRepository addressRepository;

    @Transactional
    @Override
    public Address execute(String userKeycloakId) {
        return addressRepository.findAllByUserKeycloakId(userKeycloakId).stream()
                .filter(Address::isDefault)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No default address found for user with id: " + userKeycloakId));
    }
}
