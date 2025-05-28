package com.axconstantino.profile.infrastructure.persistence.jpa.entities;

import com.axconstantino.profile.domain.entities.AddressType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", nullable = false)
    @NotNull(message = "User profile must not be null")
    private UserProfileEntity userProfile;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;

    @Column(nullable = false)
    @NotBlank(message = "Street must not be blank")
    @Size(max = 255, message = "Street must not exceed 255 characters")
    private String street;

    @Column(name = "street_number", nullable = false)
    @NotBlank(message = "Street number must not be blank")
    @Size(max = 20, message = "Street number must not exceed 20 characters")
    private String streetNumber;

    @Size(max = 50, message = "Apartment must not exceed 50 characters")
    private String apartment;

    @Column(nullable = false)
    @NotBlank(message = "City must not be blank")
    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;

    @Column(nullable = false)
    @NotBlank(message = "State must not be blank")
    @Size(max = 100, message = "State must not exceed 100 characters")
    private String state;

    @Column(nullable = false)
    @NotBlank(message = "Country must not be blank")
    @Size(max = 100, message = "Country must not exceed 100 characters")
    private String country;

    @Column(name = "zip_code", nullable = false)
    @NotBlank(message = "Zip code must not be blank")
    @Pattern(regexp = "\\d{4,10}", message = "Zip code must contain between 4 and 10 digits")
    private String zipCode;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;
}