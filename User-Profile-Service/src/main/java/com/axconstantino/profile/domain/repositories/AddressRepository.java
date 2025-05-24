package com.axconstantino.profile.domain.repositories;

import com.axconstantino.profile.domain.entities.Address;

import java.util.List;
import java.util.UUID;

public interface AddressRepository {
    void save(Address address);
    Address findById(UUID id);
    List<Address> findAllByUserProfileId(UUID userProfileId);
    Address findByIdAndUserProfileId(UUID id, UUID userProfileId);
    void deleteByIdAndUserProfileId(UUID id, UUID userProfileId);
    void update(UUID existingAddress, Address newAddress);
    void delete(UUID id);
}
