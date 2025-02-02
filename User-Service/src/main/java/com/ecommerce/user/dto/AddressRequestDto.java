package com.ecommerce.user.dto;

import com.ecommerce.user.model.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto implements Serializable {
    @NotBlank
    private String address;

    @NotNull
    private AddressType addressType;

    @NotBlank
    @Size(min = 3, max = 100)
    private String city;

    @NotBlank
    @Size(min = 3, max = 100)
    private String state;

    @NotBlank
    @Size(min = 2, max = 100)
    private String country;

    @NotBlank
    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$")
    private String zipCode;
    private boolean isDefault;
}
