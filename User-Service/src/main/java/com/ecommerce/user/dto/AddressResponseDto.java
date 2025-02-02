package com.ecommerce.user.dto;

import com.ecommerce.user.model.AddressType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponseDto {
    private Long id;
    private Long userId;
    private String address;
    private AddressType addressType;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private boolean isDefault;
    private Instant createdAt;
    private Instant updatedAt;
}
