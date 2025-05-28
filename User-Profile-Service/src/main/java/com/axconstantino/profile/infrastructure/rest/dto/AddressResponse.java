package com.axconstantino.profile.infrastructure.rest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressResponse {
    private String addressType;
    private String street;
    private String streetNumber;
    private String apartment;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private boolean isDefault;
}
