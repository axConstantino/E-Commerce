package com.axconstantino.profile.application.address.service;

import com.axconstantino.profile.application.address.command.CreateAddressCommand;
import com.axconstantino.profile.application.address.usecase.CreateAddress;
import com.axconstantino.profile.domain.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateAddressService implements CreateAddress {
    private final AddressRepository addressRepository;

    @Transactional
    @Override
    public void execute(CreateAddressCommand command) {;
        addressRepository.save(command.newAddress());
    }
}
