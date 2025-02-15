package com.ecommerce.user.mapper;

import com.ecommerce.user.dto.UserRequestDto;
import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface UserMapper{                                                                                                        
    User toEntity(UserRequestDto requestDto);

    UserResponseDto toDto(User user);

    List<UserResponseDto> toDtoList(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User updateFromDto(@MappingTarget  User existingUser, UserRequestDto newUser);

    User updateFromAdminDto(@MappingTarget User existingUser, AdminRequestDto adminRequest);
}
