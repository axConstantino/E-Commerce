package com.axconstantino.profile.application.address.service;

import com.axconstantino.profile.application.address.usecase.GetAddressById;
import com.axconstantino.profile.domain.entities.Address;
import com.axconstantino.profile.domain.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAddress implements GetAddressById {
    private final AddressRepository getAddressById;

    @Transactional
    @Override
    public Address execute(UUID id) {
        return getAddressById.findById(id);
    }
}
