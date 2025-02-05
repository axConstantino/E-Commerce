package com.ecommerce.user.dto;

import com.ecommerce.user.validation.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank
    @Size(min = 3, max = 64)
    private String name;

    @NotBlank
    @Size(min = 3, max = 32)
    private String userName;

    @NotBlank
    @Size(max = 128)
    @Email
    @Pattern(regexp = ".+@.+\\..+")
    private String email;

    @NotBlank
    @Size(min = 10, max = 15)
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$")
    private String phone;

    @NotBlank
    @JsonView(View.OnCreate.class)
    private String password;

    @NotBlank
    private String role;
}
