package com.axconstantino.profile.infrastructure.persistence.adapter;

import com.axconstantino.profile.domain.entities.Address;
import com.axconstantino.profile.domain.repositories.AddressRepository;
import com.axconstantino.profile.exception.NotFoundException;
import com.axconstantino.profile.infrastructure.mapper.AddressJpaMapper;
import com.axconstantino.profile.infrastructure.persistence.jpa.repositories.AddressJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {

    private final AddressJpaRepository addressJpa;
    private final AddressJpaMapper mapper;

    @Override
    public void save(Address address) {
        var addressEntity = mapper.toEntity(address);
        addressJpa.save(addressEntity);
    }

    @Override
    public void saveAll(List<Address> addresses) {
        addressJpa.saveAll(mapper.toEntityList(addresses));
    }

    @Override
    public Address findById(UUID id) {
        var addressEntity = addressJpa.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found address with id: " + id));
        return mapper.toDomain(addressEntity);
    }

    @Override
    public List<Address> findAllByUserKeycloakId(String keycloakId) {
        var addressEntities = addressJpa.findAllByKeycloakId(keycloakId);
        return mapper.toDomainList(addressEntities);
    }

    @Override
    public Address findByIdAndUserProfileId(UUID id, UUID userProfileId) {
        var addressEntity = addressJpa.findByIdAndUserProfileId(id, userProfileId)
                .orElseThrow(() -> new NotFoundException("Not found address with id: " + id + " and user profile id: " + userProfileId));
        return mapper.toDomain(addressEntity);
    }

    @Override
    public int countByUserProfileId(UUID userProfile) {
        return 0;
    }

    @Override
    public void deleteByIdAndUserProfileId(UUID id, UUID userProfileId) {
        addressJpa.deleteByIdAndUserProfileId(id, userProfileId);
    }

    @Override
    public void update(UUID existingAddress, Address newAddress) {
        var addressEntity = addressJpa.findById(existingAddress)
                .orElseThrow(() -> new NotFoundException("Not found address with id: " + existingAddress));
        mapper.updateEntity(addressEntity, newAddress);
    }

    @Override
    public void delete(UUID id) {
        addressJpa.deleteById(id);
    }
}
