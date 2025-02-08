package com.ecommerce.user.dto;


import com.ecommerce.user.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String phone;
    private Set<AddressResponseDto> addresses;
    private Role role;
    private Instant createdDate;
    private Instant updatedDate;
}
