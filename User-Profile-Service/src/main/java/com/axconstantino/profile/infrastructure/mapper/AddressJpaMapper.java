package com.axconstantino.profile.infrastructure.mapper;

import com.axconstantino.profile.domain.entities.Address;
import com.axconstantino.profile.infrastructure.persistence.jpa.entities.AddressEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressJpaMapper {
    AddressEntity toEntity(Address address);
    Address toDomain(AddressEntity addressEntity);
    List<Address> toDomain(List<AddressEntity> addressEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget AddressEntity addressEntity, Address address);
}
