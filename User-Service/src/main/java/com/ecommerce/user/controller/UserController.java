package com.ecommerce.user.controller;

import com.ecommerce.user.dto.UserJwtResponse;
import com.ecommerce.user.dto.UserRequestDto;
import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.URL)
@Validated
@RequiredArgsConstructor
public class UserController {
    protected static final String URL = "/api/v1/users";
    protected static final String userHeaderId = "X-User-Id";
    private final UserService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a user in the database")
    public UserJwtResponse create(@Valid @RequestBody UserRequestDto requestDto) {
       UserResponseDto responseDto = service.createUser(requestDto);

       UserJwtResponse response = new UserJwtResponse();
       response.setId(responseDto.getId());
       response.setEmail(responseDto.getEmail());
       response.setRole(responseDto.getRole().name());

       return response;
    }

    @PatchMapping("/me")
    @Operation(summary = "Update an existing user")
    public ResponseEntity<UserResponseDto> update(@RequestHeader(userHeaderId) String userId, @Valid @RequestBody UserRequestDto request) {
        Long id = Long.parseLong(userId);
        UserResponseDto updatedUser = service.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/me")
    @Operation(summary = "Get user profile")
    public ResponseEntity<UserResponseDto> getUserProfile(@RequestHeader(userHeaderId) String id) {
        Long userId = Long.parseLong(id);
        UserResponseDto response = service.getUserById(userId);
        return ResponseEntity.ok(response);
    }
}
