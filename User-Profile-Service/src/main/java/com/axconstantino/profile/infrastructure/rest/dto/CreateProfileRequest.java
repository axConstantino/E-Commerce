package com.axconstantino.profile.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateProfileRequest {
    @NotBlank(message = "Full name must not be blank")
    @Size(min = 2, max = 255, message = "Full name must be between 2 and 255 characters")
    private String fullName;

    @NotBlank(message = "Nickname must not be blank")
    @Size(min = 2, max = 255, message = "Nickname must be between 2 and 255 characters")
    private String nickname;

    @NotBlank(message = "Phone number must not be blank")
    private String phoneNumber;
}
