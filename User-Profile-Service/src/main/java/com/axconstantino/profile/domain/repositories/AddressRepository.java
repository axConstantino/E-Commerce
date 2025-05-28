package com.axconstantino.profile.domain.repositories;

import com.axconstantino.profile.domain.entities.Address;

import java.util.List;
import java.util.UUID;

public interface AddressRepository {
    void save(Address address);
    void saveAll(List<Address> addresses);
    Address findById(UUID id);
    List<Address> findAllByUserKeycloakId(String userId);
    Address findByIdAndUserProfileId(UUID id, UUID userProfileId);
    int  countByUserProfileId(UUID userProfile);
    void deleteByIdAndUserProfileId(UUID id, UUID userProfileId);
    void update(UUID existingAddress, Address newAddress);
    void delete(UUID id);
}
