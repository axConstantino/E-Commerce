package com.axconstantino.profile.infrastructure.rest.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressCreateRequest {

    @NotNull(message = "Address type must not be null")
    private String addressType;

    @NotBlank(message = "Street must not be blank")
    @Size(max = 255, message = "Street must not exceed 255 characters")
    private String street;

    @NotBlank(message = "Street number must not be blank")
    @Size(max = 20, message = "Street number must not exceed 20 characters")
    private String streetNumber;

    @Size(max = 50, message = "Apartment must not exceed 50 characters")
    private String apartment;

    @NotBlank(message = "City must not be blank")
    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;

    @NotBlank(message = "State must not be blank")
    @Size(max = 100, message = "State must not exceed 100 characters")
    private String state;

    @NotBlank(message = "Country must not be blank")
    @Size(max = 100, message = "Country must not exceed 100 characters")
    private String country;

    @NotBlank(message = "Zip code must not be blank")
    @Pattern(regexp = "\\d{4,10}", message = "Zip code must contain between 4 and 10 digits")
    private String zipCode;

    private boolean isDefault;
}

