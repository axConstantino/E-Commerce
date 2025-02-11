package com.ecommerce.user.model;

import com.ecommerce.user.validation.NoHtml;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @NoHtml
    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false)
    private AddressType addressType;

    @NoHtml
    @NotBlank
    @Column(name = "city", nullable = false)
    private String city;

    @NoHtml
    @NotBlank
    @Column(name = "state", nullable = false)
    private String state;

    @NoHtml
    @NotBlank
    @Column(name = "country", nullable = false)
    private String country;

    @NoHtml
    @NotBlank
    @Size(min = 5, max = 10)
    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$")
    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    private boolean isDefault;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;


    public void assignUser(User user) {
        this.user = user;
    }

    public void markAsDefault() {
        this.isDefault = true;
    }

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

}
