package com.ecommerce.user.mapper;

import com.ecommerce.user.dto.AddressRequestDto;
import com.ecommerce.user.dto.AddressResponseDto;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.AddressType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    /**
     * Convierte una entidad Address a un AddressResponseDto.
     *
     * @param address La entidad Address a convertir.
     * @return El DTO con los datos de la dirección.
     */
    @Mapping(target = "userId", source = "user.id")
    AddressResponseDto toDto(Address address);

    /**
     * Convierte una lista de entidades Address a una lista de AddressResponseDto
     *
     * @param userAddresses La lista de entidades Address a convertir
     * @return Una lista de AddressResponseDto's con los datos de la primera lista
     */
    List<AddressResponseDto> toDtoList(List<Address> userAddresses);

    /**
     * Convierte un AddressRequestDto a una entidad Address.
     *
     * @param requestDto El DTO con los datos de la dirección.
     * @return La entidad Address creada.
     */
    Address toEntity(AddressRequestDto requestDto);


    /**
     * Actualiza una entidad Address con los datos de un AddressRequestDto.
     *
     * @param requestDto      El DTO con los nuevos datos.
     * @param existingAddress La entidad Address a actualizar.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    Address updateFromDto(@MappingTarget Address existingAddress, AddressRequestDto requestDto);
}