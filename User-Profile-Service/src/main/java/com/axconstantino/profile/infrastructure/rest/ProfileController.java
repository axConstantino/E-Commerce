package com.axconstantino.profile.infrastructure.rest;

import com.axconstantino.profile.application.profile.command.CreateProfileCommand;
import com.axconstantino.profile.application.profile.command.UpdateProfileCommand;
import com.axconstantino.profile.application.profile.usecase.CreateProfile;
import com.axconstantino.profile.application.profile.usecase.DeleteProfile;
import com.axconstantino.profile.application.profile.usecase.GetProfileById;
import com.axconstantino.profile.application.profile.usecase.UpdateProfile;
import com.axconstantino.profile.domain.entities.UserProfile;
import com.axconstantino.profile.infrastructure.rest.dto.CreateProfileRequest;
import com.axconstantino.profile.infrastructure.rest.dto.UpdateProfileRequest;
import com.axconstantino.profile.infrastructure.rest.dto.UserProfileResponse;
import com.axconstantino.profile.infrastructure.rest.mapper.UserProfileRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final GetProfileById getProfile;
    private final CreateProfile createProfile;
    private final UpdateProfile updateProfile;
    private final DeleteProfile deleteProfile;
    private final UserProfileRestMapper mapper;


    @PostMapping("/create")
    public ResponseEntity<Void> createProfile(@RequestBody CreateProfileRequest request, @AuthenticationPrincipal Jwt jwt) {
        CreateProfileCommand command = new CreateProfileCommand(jwt.getId(), mapper.toUserProfile(request));
        createProfile.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(@AuthenticationPrincipal Jwt jwt) {
        UserProfile profile = getProfile.execute(jwt.getId());
        return ResponseEntity.ok(mapper.toDto(profile));
    }

    @PutMapping("/update/{profileId}")
    public ResponseEntity<Void> updateProfile(@PathVariable UUID profileId, @Valid @RequestBody UpdateProfileRequest updateRequest) {
        UpdateProfileCommand command = new UpdateProfileCommand(profileId, mapper.toUserProfile(updateRequest));
        updateProfile.execute(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable UUID profileId) {
        deleteProfile.execute(profileId);
        return ResponseEntity.noContent().build();
    }
}
