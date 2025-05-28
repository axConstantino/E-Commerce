package com.axconstantino.profile.infrastructure.rest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Setter
@Getter
public class UserProfileResponse {
    private UUID id;
    private String keycloakId;
    private String fullName;
    private String nickname;
    private String phoneNumber;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<AddressResponse> addresses;
}
