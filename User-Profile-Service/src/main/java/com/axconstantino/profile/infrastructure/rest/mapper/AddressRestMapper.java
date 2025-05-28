package com.axconstantino.profile.infrastructure.rest.mapper;

import com.axconstantino.profile.application.address.command.CreateAddressCommand;
import com.axconstantino.profile.domain.entities.Address;
import com.axconstantino.profile.domain.entities.AddressType;
import com.axconstantino.profile.exception.InvalidAddressTypeException;
import com.axconstantino.profile.infrastructure.rest.dto.AddressCreateRequest;
import com.axconstantino.profile.infrastructure.rest.dto.AddressResponse;
import com.axconstantino.profile.infrastructure.rest.dto.UpdateAddressRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressRestMapper {
    @Mapping(target = "addressType", expression = "java(AddressType.valueOf(request.addressType()))")
    Address toAddress(AddressCreateRequest request);

    @Mapping(target = "addressType", expression = "java(AddressType.valueOf(request.addressType()))")
    Address toAddress(UpdateAddressRequest request);

    AddressResponse toDto(Address address);

    List<AddressResponse> toDtoList(List<Address> addresses);

    default AddressType mapAddressType(String type) {
        try {
            return AddressType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new InvalidAddressTypeException(type);
        }
    }
}
