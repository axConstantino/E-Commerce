package com.axconstantino.profile.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserProfile {
    private UUID id;
    private String keycloakId;
    private String fullName;
    private String nickname;
    private String phoneNumber;
    private String avatarUrl;
    private List<Address> addresses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
