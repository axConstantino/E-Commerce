package com.ecommerce.user.controller;

import com.ecommerce.user.dto.UserJwtResponse;
import com.ecommerce.user.dto.UserRequestDto;
import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.URL)
@Validated
@RequiredArgsConstructor
public class UserController {
    protected static final String URL = "/api/v1/users";
    private final UserService service;

    @PostMapping("/register")
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

    @PutMapping("/{userId}")
    @Operation(summary = "Update an existing user")
    public UserResponseDto update(@PathVariable Long userId, @Valid @RequestBody UserRequestDto request) {
        return service.updateUser(userId, request);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by id.")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        return service.getUserById(userId);
    }
}
