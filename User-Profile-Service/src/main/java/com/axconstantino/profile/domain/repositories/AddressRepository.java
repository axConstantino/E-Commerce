package com.axconstantino.profile.domain.repositories;

import com.axconstantino.profile.domain.entities.Address;

import java.util.List;
import java.util.UUID;

public interface AddressRepository {
    void save(Address address);
    Address findById(UUID id);
    List<Address> findByUserProfileId(UUID userProfileId);
    void update(Address address);
    void delete(UUID id);
}
