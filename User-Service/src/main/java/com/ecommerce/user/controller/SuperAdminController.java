package com.ecommerce.user.controller;

import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SuperAdminController.ADMIN_URL)
@RequiredArgsConstructor
public class SuperAdminController {
    protected static final String ADMIN_URL = "/api/v1/super-admin/users";
    private final UserService service;

    @GetMapping("/deleted")
    public Page<UserResponseDto> getDeletedUsers(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return service.getInactiveUsersList(pageable);
    }

    @DeleteMapping("/{userId}/permanent")
    public ResponseEntity<String> deleteUsersPermanent(@PathVariable Long userId) {
        service.hardDelete(userId);
        return ResponseEntity.ok("Delete the user with ID: " + userId);
    }
}
