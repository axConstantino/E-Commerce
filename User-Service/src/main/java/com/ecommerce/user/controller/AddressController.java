package com.ecommerce.user.controller;

import com.ecommerce.user.dto.AddressRequestDto;
import com.ecommerce.user.dto.AddressResponseDto;
import com.ecommerce.user.dto.UserRequestDto;
import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.mapper.AddressMapper;
import com.ecommerce.user.mapper.UserMapper;
import com.ecommerce.user.model.User;
import com.ecommerce.user.service.AddressService;
import com.ecommerce.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AddressController.URL)
@Validated
@RequiredArgsConstructor
public class AddressController {
    protected static final String URL = "/api/v1/users/{userId}/addresses";

    private final AddressService addressService;
    private final UserService userService;
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new address for a user")
    public AddressResponseDto createNewAddress(@PathVariable Long userId, @Valid @RequestBody AddressRequestDto requestDto) {
        return addressService.saveAddress(userId, requestDto);
    }


}
