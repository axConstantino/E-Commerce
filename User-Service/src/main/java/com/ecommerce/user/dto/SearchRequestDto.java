package com.ecommerce.user.dto;

import lombok.Data;

@Data
public class SearchRequestDto {
    private String name;
    private String email;
    private String phone;
    private String role;
}
