package com.ecommerce.user.controller;

import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AdminController.URL)
@RequiredArgsConstructor
@SecurityRequirement(name = "barerAuth")
public class AdminController {
    protected static final String URL = "/api/v1/admin/users";
    private final UserService service;

    @GetMapping
    @Operation(summary = "Get all users (Admin only)")
    public Page<UserResponseDto> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return service.getAllUsers(page, size);
    }

    @GetMapping("{userId}")
    @Operation(summary = "Get user info")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto response = service.getUserById(id);
        return ResponseEntity.ok(response);
    }
}
