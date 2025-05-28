package com.axconstantino.profile.infrastructure.rest.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UpdateAddressRequest {

    @Size(max = 255, message = "Street must not exceed 255 characters")
    private String street;

    @Size(max = 20, message = "Street number must not exceed 20 characters")
    private String streetNumber;

    @Size(max = 50, message = "Apartment must not exceed 50 characters")
    private String apartment;

    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;

    @Size(max = 100, message = "State must not exceed 100 characters")
    private String state;

    @Size(max = 100, message = "Country must not exceed 100 characters")
    private String country;

    @Pattern(regexp = "\\d{4,10}", message = "Zip code must contain between 4 and 10 digits")
    private String zipCode;

    @Size(max = 100, message = "Address type must not exceed 100 characters")
    private String addressType;

    private Boolean isDefault;
}
