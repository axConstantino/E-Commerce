package com.axconstantino.profile.application.address.service;

import com.axconstantino.profile.application.address.usecase.SetDefaultAddress;
import com.axconstantino.profile.domain.entities.Address;
import com.axconstantino.profile.domain.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SetDefaultAddressService implements SetDefaultAddress {
    private final AddressRepository addressRepository;

    @Transactional
    @Override
    public void execute(String userKeycloakId, UUID addressId) {
        List<Address> addresses = addressRepository.findAllByUserKeycloakId(userKeycloakId);

        for (Address address : addresses) {
            address.setDefault(address.getId().equals(addressId));
        }

        addressRepository.saveAll(addresses);
    }
}
