package com.ecommerce.user.controller;

import com.ecommerce.user.dto.AddressRequestDto;
import com.ecommerce.user.dto.AddressResponseDto;
import com.ecommerce.user.dto.UserRequestDto;
import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.mapper.AddressMapper;
import com.ecommerce.user.mapper.UserMapper;
import com.ecommerce.user.model.Address;
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

import java.util.List;

@RestController
@RequestMapping(AddressController.URL)
@Validated
@RequiredArgsConstructor
public class AddressController {
    protected static final String URL = "/api/v1/users/{userId}/addresses";
    private final AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new address for a user.")
    public AddressResponseDto createNewAddress(@PathVariable Long userId, @Valid @RequestBody AddressRequestDto requestDto) {
        return addressService.saveAddress(userId, requestDto);
    }

    @GetMapping
    @Operation(summary = "Get a list of user addresses.")
    public List<AddressResponseDto> getAllUserAddresses(@PathVariable Long userId) {
        return addressService.getAddressesByUserId(userId);
    }

    @GetMapping("/{addressId}")
    @Operation(summary = "Get specific user address.")
    public AddressResponseDto getSpecificUserAddress(@PathVariable Long userId, @PathVariable Long addressId) {
        return addressService.getSpecificUserAddress(userId, addressId);
    }

    @PutMapping("/{addressId}")
    @Operation(summary = "Update an existing address")
    public AddressResponseDto updateAddress(@PathVariable Long userId, @PathVariable Long addressId, @Valid @RequestBody AddressRequestDto requestDto) {
        return addressService.update(userId, addressId, requestDto);
    }

    @PutMapping("/{addressId}/set-default")
    @Operation(summary = "Set address as default")
    public AddressResponseDto setDefault(@PathVariable Long userId, @PathVariable Long addressId) {
        return addressService.setDefaultAddress(userId, addressId);
    }

    @DeleteMapping("/{addressId}")
    @Operation(summary = "Delete an address")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable Long userId, @PathVariable Long addressId) {
        addressService.delete(userId, addressId);
    }
}
