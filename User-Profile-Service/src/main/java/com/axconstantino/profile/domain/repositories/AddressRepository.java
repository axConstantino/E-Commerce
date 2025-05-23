package com.axconstantino.profile.domain.repositories;

import com.axconstantino.profile.domain.entities.Address;

import java.util.List;
import java.util.UUID;

public interface AddressRepository {
    void save(Address address);
    Address findById(UUID id);
    List<Address> findByUserProfileId(UUID userProfileId);
    Address findByIdAndUserProfileId(UUID id, UUID userProfileId);
    void deleteByIdAndUserProfileId(UUID id, UUID userProfileId);
    void update(Address address);
    void delete(UUID id);
}
