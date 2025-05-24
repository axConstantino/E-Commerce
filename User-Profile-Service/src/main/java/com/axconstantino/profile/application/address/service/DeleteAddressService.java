package com.axconstantino.profile.application.address.service;

import com.axconstantino.profile.application.address.usecase.DeleteAddress;
import com.axconstantino.profile.domain.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteAddressService implements DeleteAddress {
    private final AddressRepository addressRepository;

    @Override
    public void execute(UUID addressId) {
        addressRepository.delete(addressId);
    }
}
