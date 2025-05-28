package com.axconstantino.profile.infrastructure.rest.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdateProfileRequest {
    @Size(min = 2, max = 255, message = "Full name must be between 2 and 255 characters")
    private String fullName;

    @Size(min = 2, max = 255, message = "Nickname must be between 2 and 255 characters")
    private String nickname;

    private String phoneNumber;
}
