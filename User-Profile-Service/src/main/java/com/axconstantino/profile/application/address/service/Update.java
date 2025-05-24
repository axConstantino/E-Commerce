package com.axconstantino.profile.application.address.service;

import com.axconstantino.profile.application.address.command.UpdateAddressCommand;
import com.axconstantino.profile.application.address.usecase.UpdateAddress;
import com.axconstantino.profile.domain.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class Update implements UpdateAddress {
    private final AddressRepository addressRepository;

    @Transactional
    @Override
    public void execute(UpdateAddressCommand command) {
        addressRepository.update(command.existingAddressId(), command.updatedAddress());
    }
}
