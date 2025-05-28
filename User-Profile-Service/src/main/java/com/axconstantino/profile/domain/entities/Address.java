package com.axconstantino.profile.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    private UUID id;
    private UserProfile userProfile;
    private AddressType addressType;
    private String street;
    private String streetNumber;
    private String apartment;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private boolean isDefault;
}
