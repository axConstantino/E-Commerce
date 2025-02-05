package com.ecommerce.user.mapper;

import com.ecommerce.user.dto.UserRequestDto;
import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.model.Role;
import com.ecommerce.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = Role.class)
public interface UserMapper{
    User toEntity(UserResponseDto requestDto);

    UserResponseDto toDto(User user);
}
