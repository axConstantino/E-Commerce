package com.axconstantino.profile.infrastructure.rest.mapper;

import com.axconstantino.profile.domain.entities.UserProfile;
import com.axconstantino.profile.infrastructure.rest.dto.CreateProfileRequest;
import com.axconstantino.profile.infrastructure.rest.dto.UpdateProfileRequest;
import com.axconstantino.profile.infrastructure.rest.dto.UserProfileResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileRestMapper {
    UserProfile toUserProfile(CreateProfileRequest request);
    UserProfile toUserProfile(UpdateProfileRequest request);
    UserProfileResponse toDto(UserProfile userProfile);
}
