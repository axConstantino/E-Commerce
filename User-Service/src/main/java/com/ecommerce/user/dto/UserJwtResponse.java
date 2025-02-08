package com.ecommerce.user.dto;

import lombok.Data;

@Data
public class UserJwtResponse {
    private Long id;
    private String email;
    private String role;
}
