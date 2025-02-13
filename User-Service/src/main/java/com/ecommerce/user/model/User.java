package com.ecommerce.user.model;


import com.ecommerce.user.validation.NoHtml;
import com.ecommerce.user.validation.View;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Builder
@Getter
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NoHtml
    @NotBlank
    @Size(min = 3, max = 64)
    @Column(name = "name", nullable = false)
    private String name;

    @NoHtml
    @NotBlank
    @Size(min = 3, max = 32)
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @NoHtml
    @NotBlank
    @Size(max = 128)
    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @NoHtml
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonView(View.OnCreate.class)
    private String password;

    @NoHtml
    @NotBlank
    @Size(min = 10, max = 15)
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$")
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "deactivation_date")
    private LocalDateTime deactivationDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public void addAddress(Address address) {
        address.assignUser(this);
        addresses.add(address);
    }

    public void deactivate() {
        if (this.isActive){
            this.isActive = false;
            this.deactivationDate = LocalDateTime.now();
        }
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
