package com.ecommerce.user.controller;

import com.ecommerce.user.dto.SearchRequestDto;
import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.feign.AuthServiceClient;
import com.ecommerce.user.model.Role;
import com.ecommerce.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AdminController.ADMIN_URL)
@RequiredArgsConstructor
@Validated
@SecurityRequirement(name = "bearerAuth")
public class AdminController {
    protected static final String ADMIN_URL = "/api/v1/admin/users";
    private final UserService service;
    private final AuthServiceClient authService;

    @GetMapping
    @Operation(summary = "Get all users (Admin only)")
    public Page<UserResponseDto> getAllUsers(@PageableDefault(page = 0, size = 10)Pageable pageable) {
        return service.getAllUsers(pageable);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user info")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) {
        UserResponseDto response = service.getUserById(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public Page<UserResponseDto> searchUsers(SearchRequestDto searchRequestDto, @PageableDefault(page = 0, size = 10)Pageable pageable) {
        return service.searchUsers(searchRequestDto, pageable);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Allows the administrator to change user information such as role")
    public void updateUserRole(@PathVariable Long userId, Role newRole) {
        service.updateUserRole(userId, newRole);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        service.softDelete(userId);
        authService.revokeAllTokens(userId);
    }
}
